package com.javaex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.OpenAiDao;
import com.javaex.vo.ChallengeVo;

@Service
public class OpenAiService {

   @Value("${openai.api.key}")
   private String apiKey;

   @Value("${openai.model}")
   private String model;

   @Value("${openai.temperature}")
   private double temperature;

   @Value("${openai.max-tokens}")
   private int maxTokens;

   private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

   @Autowired
   private OpenAiDao openAiDao;

   // 챌린지 추천 생성 메서드
   public List<ChallengeVo> generateChallenges(int roomNum) {
	   ChallengeVo keyword = openAiDao.getKeywords(roomNum);
	   ChallengeVo maxNum = openAiDao.getMaxNum(roomNum);
	   ChallengeVo period = openAiDao.getPeriodType(roomNum);
	   List<ChallengeVo> missionList = openAiDao.getMissions(roomNum);
	   System.out.println(missionList);
	   
	   // map에 데이터 추가
	   Map<String, Object> roomDetails = new HashMap<>();
	   roomDetails.put("keyword", keyword);
	   roomDetails.put("maxNum", maxNum);
	   roomDetails.put("period", period);
	   roomDetails.put("missionList", missionList);
       
	   System.out.println("맵 데이터"+roomDetails.get("missionList"));
	   
       // 프롬프트 생성
       String prompt = generatePrompt(roomDetails);
       
       // OpenAI API 호출
       return callOpenAiApi(prompt);
   }

	// 프롬프트 생성 메서드
	private String generatePrompt(Map<String, Object> roomDetails) {
	    StringBuilder prompt = new StringBuilder();
	    
	    // 키워드, 최대인원, 기간 추가
	    prompt.append("키워드: ").append(roomDetails.get("keyword")).append(", ");
	    prompt.append("최대 인원: ").append(roomDetails.get("maxNum")).append("명, ");
	    prompt.append("기간: ").append(roomDetails.get("period")).append("주\n");
	    prompt.append("미션 리스트: \n");
	    
	    // 미션 리스트 가져오기
	    @SuppressWarnings("unchecked")
	    List<ChallengeVo> missions = (List<ChallengeVo>) roomDetails.get("missionList");
	
	    // 미션 리스트를 프롬프트에 추가
	    for (ChallengeVo mission : missions) {
	        prompt.append("- 미션 이름: ").append(mission.getMissionName()).append(", ");
	        prompt.append("미션 방법: ").append(mission.getMissionMethod()).append("\n");
	    }
	
	    prompt.append("키워드와 가장 관련된 일일 목표 하나를 선택해 주세요.\n"
	    		+ "횟수를 기준으로 목표를 상, 중, 하 3개만 만들어주세요.\n"
	    		+ "예시). 골프 모임일때 인원은 10명이고, 기간은 2주, 키워드: 골프, "
	    		+ "일일목표가 스윙 20번 연습하기, 출석체크하기, "
	    		+ "스트레칭 하기 일때 가장 관련있는 일일 목표는 스윙 20번 연습하기 입니다. "
	    		+ "이때 그룹 챌린지를 난이도별로 설정하게 되면, "
	    		+ "상: \"스윙 20번 연습하기\" 140번 달성 (달성률 100퍼센트), "
	    		+ "중: \"스윙 20번 연습하기\" 100번 달성, "
	    		+ "하: \"스윙 20번 연습하기\" 50번 달성"
	    		+ "챌린지 제목과 횟수, 첼린지 제목만,횟수 숫자로만, 선택된 일반미션의 제목을 따로 보내주세요.");
	
	    return prompt.toString();
	}

	private List<ChallengeVo> callOpenAiApi(String prompt) {
	    RestTemplate restTemplate = new RestTemplate();

	    // HTTP 헤더 설정
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Bearer " + apiKey);
	    headers.add("Content-Type", "application/json");

	    // 요청 바디 생성
	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("model", model);
	    requestBody.put("messages", List.of(
	            Map.of("role", "system", "content", "You are an assistant that helps generate group challenges."),
	            Map.of("role", "user", "content", prompt)
	    ));
	    requestBody.put("max_tokens", maxTokens);
	    requestBody.put("temperature", temperature);

	    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

	    try {
	        // API 호출
	        ResponseEntity<Map> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, Map.class);

	        // 응답 처리
	        Map<String, Object> responseBody = response.getBody();
	        if (responseBody != null && responseBody.containsKey("choices")) {
	            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
	            if (!choices.isEmpty()) {
	                Map<String, Object> choice = choices.get(0);
	                String content = (String) ((Map<String, Object>) choice.get("message")).get("content");

	                // 응답을 콘솔에 출력
	                System.out.println("AI 응답 내용: \n" + content);

	                // 응답을 기반으로 ChallengeVo 리스트 생성
	                return parseAiResponse(content);
	            }
	        }
	    } catch (HttpClientErrorException e) {
	        System.err.println("HTTP Error: " + e.getStatusCode());
	        System.err.println("Error Response: " + e.getResponseBodyAsString());
	    } catch (Exception e) {
	        System.err.println("Unexpected Error: " + e.getMessage());
	    }

	    return List.of(); // 실패 시 빈 리스트 반환
	}

   // AI 응답 파싱 메서드
   private List<ChallengeVo> parseAiResponse(String content) {
       // AI 응답을 ChallengeVo 리스트로 변환하는 로직 작성
       // 예시로 간단하게 응답을 한 줄씩 나누어 ChallengeVo 객체를 생성
       List<ChallengeVo> challenges = new ArrayList<>();
       String[] lines = content.split("\n");

       for (String line : lines) {
           if (line.trim().isEmpty()) continue;

           ChallengeVo challenge = new ChallengeVo();
           challenge.setMissionName(line); // 예시로 응답 내용 전체를 미션 이름으로 설정
           challenges.add(challenge);
       }

       return challenges;
   }
   
   // 미션 및 이미지 생성 서비스
	public int saveSelectedChallenge(ChallengeVo challengevo) {
        // 미션 등록
        int conut = openAiDao.insertMission(challengevo);
        return conut;
    }
}
