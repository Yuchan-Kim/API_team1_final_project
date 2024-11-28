package com.javaex.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.javaex.dao.OpenAiDao;
import com.javaex.vo.ChallengeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    
 // 일반 미션 생성 로직
    public List<ChallengeVo> generatePlaceholder(int roomNum) {
        // 키워드 및 기존 미션 리스트 가져오기
        ChallengeVo keyword = openAiDao.getKeywords(roomNum);
        System.out.println(keyword);

        // 프롬프트 생성
        String prompt = generatePromptForDailyMissions2(keyword);

        // OpenAI API 호출 및 응답 처리
        return callOpenAiApi2(prompt);
    }

    // 프롬프트 생성
    private String generatePromptForDailyMissions2(ChallengeVo keyword) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("키워드: ").append(keyword.getRoomKeyword()).append("\n")
              .append("현재까지 생성된 미션: \n");

        prompt.append("\n")
              .append("위의 키워드를 사용해 일일 미션을 '1개만' 만들어 주세요.\n")
              .append("응답 형식은 다음과 같습니다:\n")
              .append("[\n")
              .append("    {\"title\": \"미션 제목\"},\n")
              .append("  ...\n")
              .append("]");

        return prompt.toString();
    }

    // OpenAI API 호출
    private List<ChallengeVo> callOpenAiApi2(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

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
            ResponseEntity<Map> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, Map.class);
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    String content = (String) ((Map<String, Object>) choice.get("message")).get("content");

                    System.out.println("AI 응답 내용:\n" + content);

                    // 응답 JSON을 List<ChallengeVo>로 변환
                    return parseAiResponse2(content);
                }
            }
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Error: " + e.getStatusCode());
            System.err.println("Error Response: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }

        return List.of();
    }

    // AI 응답 파싱
    private List<ChallengeVo> parseAiResponse2(String jsonResponse) {
        List<ChallengeVo> challenges = new ArrayList<>();
        try {
            Gson gson = new Gson();
            ChallengeVo[] parsedChallenges = gson.fromJson(jsonResponse, ChallengeVo[].class);

            for (ChallengeVo challenge : parsedChallenges) {
                ChallengeVo vo = new ChallengeVo();
                vo.setMissionName(challenge.getTitle());
                challenges.add(vo);
            }

            System.out.println("Gson으로 파싱된 챌린지 리스트: " + challenges);
        } catch (JsonSyntaxException e) {
            System.err.println("JSON 파싱 중 오류 발생: " + e.getMessage());
        }

        return challenges;
    }

    
    // ai 챌린지 생성
    public List<ChallengeVo> generateChallenges(int roomNum) {
        ChallengeVo keyword = openAiDao.getKeywords(roomNum);
        ChallengeVo maxNum = openAiDao.getMaxNum(roomNum);
        ChallengeVo period = openAiDao.getPeriodType(roomNum);
        List<ChallengeVo> missionList = openAiDao.getMissions(roomNum);

        // 데이터 준비
        Map<String, Object> roomDetails = new HashMap<>();
        roomDetails.put("keyword", keyword);
        roomDetails.put("maxNum", maxNum);
        roomDetails.put("period", period);
        roomDetails.put("missionList", missionList);

        // 프롬프트 생성
        String prompt = generatePrompt(roomDetails);

        // OpenAI API 호출 및 응답 처리
        return callOpenAiApi(prompt);
    }

    private String generatePrompt(Map<String, Object> roomDetails) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("키워드: ").append(roomDetails.get("keyword")).append(", ");
        prompt.append("참여 인원: ").append(roomDetails.get("maxNum")).append("명, ");
        prompt.append("기간: ").append(roomDetails.get("period")).append("주\n");
        prompt.append("미션 리스트: \n");

        @SuppressWarnings("unchecked")
        List<ChallengeVo> missions = (List<ChallengeVo>) roomDetails.get("missionList");
        for (ChallengeVo mission : missions) {
            prompt.append("- 미션 이름: ").append(mission.getMissionName()).append(", ");
            prompt.append("미션 방법: ").append(mission.getMissionMethod()).append("\n");
        }

        prompt.append("키워드와 가장 관련된 일일 목표 하나를 선택해 주세요.\n")
        		.append("참여 인원과 기간을 고려해서 '상'은 95% '중'은 80% '하'는 70%로\n")
                .append("횟수를 기준으로 목표를 상, 중, 하 각자 1개씩 만들어주세요.\n")
                .append("다음 형식으로 응답해주세요:\n")
                .append("[\n")
                .append("  {\"title\": \"관련된 미션 10회 달성하기 (상)\", \"count\": 10, \"selectedMission\": \"선택된 미션\"},\n")
                .append("  ...\n")
                .append("]");

        return prompt.toString();
    }

    private List<ChallengeVo> callOpenAiApi(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

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
            ResponseEntity<Map> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, Map.class);
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    String content = (String) ((Map<String, Object>) choice.get("message")).get("content");

                    System.out.println("AI 응답 내용:\n" + content);

                    // 응답 JSON을 List<ChallengeVo>로 변환
                    return parseAiResponseWithGson(content);
                }
            }
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Error: " + e.getStatusCode());
            System.err.println("Error Response: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }

        return List.of();
    }

    private List<ChallengeVo> parseAiResponseWithGson(String jsonResponse) {
        List<ChallengeVo> challenges = new ArrayList<>();
        try {
            Gson gson = new Gson();
            ChallengeVo[] parsedChallenges = gson.fromJson(jsonResponse, ChallengeVo[].class);

            for (ChallengeVo challenge : parsedChallenges) {
                ChallengeVo vo = new ChallengeVo();
                vo.setAiMission(challenge.getTitle());
                vo.setCount(challenge.getCount());
                vo.setMissionName(challenge.getSelectedMission());
                challenges.add(vo);
            }

            System.out.println("Gson으로 파싱된 챌린지 리스트: " + challenges);
        } catch (JsonSyntaxException e) {
            System.err.println("JSON 파싱 중 오류 발생: " + e.getMessage());
        }

        return challenges;
    }


    // 미션 저장 메서드
    public int saveSelectedChallenge(ChallengeVo challengeVo) {
    	
    	// 8번 요일 등록
    	int roomDayNum = openAiDao.insertRoomDay(challengeVo);
    	challengeVo.setRoomDayNum(roomDayNum);
    	
    	// 챌린지미션 등록
        openAiDao.insertMission(challengeVo);
        
        // 미션 넘버 리스트가져오기
        List<ChallengeVo> missionList = openAiDao.getMissionNum(challengeVo);
        
        int count = -1;
        for(int i = 0; i < missionList.size(); i++) {
        	
        	int missionNum = missionList.get(i).getMissionNum();
        	challengeVo.setMissionNum(missionNum);
        	
        	count = openAiDao.updateMission(challengeVo);
        }
        
        return count;
    }
}
