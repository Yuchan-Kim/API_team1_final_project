package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.JM_ChallengeService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.ChallengeVo;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class JM_ChallengeController {
	
	@Autowired
	private JM_ChallengeService service;
	
	// 방번호로 참여유저, 미션, 설명, 공지 리스트 가져오기
	@GetMapping("api/roomMain/{roomNum}")
	public JsonResult roomMain(@PathVariable int roomNum) {
		System.out.println("ChallengeController-userList");
		System.out.println(roomNum);

		Map<String, Object> roomDetails = service.exeRoomMain(roomNum);
		System.out.println("방메인 정보" + roomDetails);
		return JsonResult.success(roomDetails);
	}
	
	
	// 방번호로 미션 리스트 가져오기
	@GetMapping("api/missionList/{roomNum}")
	public JsonResult missionList(
			@PathVariable int roomNum,
			HttpServletRequest request) {
		int userNum = JwtUtil.getNoFromHeader(request);
		System.out.println("ChallengeController-missionList");
		
		ChallengeVo challengevo = new ChallengeVo();
		challengevo.setRoomNum(roomNum);
		challengevo.setUserNum(userNum);

		List<ChallengeVo> missionList = service.exeMissionList(challengevo);
		System.out.println("미션 리스트" + missionList);
		return JsonResult.success(missionList);
	}
	
	// 방 평가타입 가져오기
	@GetMapping("api/roomEvalType/{roomNum}")
	public JsonResult getRoomEvalType(@PathVariable int roomNum) {
		int roomType = service.exeGetRoomEvalType(roomNum);
		System.out.println("평가 타입"+roomType);
		
		return JsonResult.success(roomType);
	}
	
	// 유저 권한 가져오기
	@GetMapping("api/UserAuth/{roomNum}")
	public JsonResult getUserAuth(
	        @PathVariable int roomNum,
	        HttpServletRequest request) {
	    try {
	        // 디버깅용 메시지 출력
	        System.out.println("UserAuth API 호출됨. RoomNum: " + roomNum);

	        // JWT에서 유저 번호 추출
	        int userNum = JwtUtil.getNoFromHeader(request);
	        System.out.println("추출된 UserNum: " + userNum);
	        System.out.println("추출된 방 넘버"+ roomNum );

	        // 요청 객체 생성 및 값 설정
	        ChallengeVo challengeVo = new ChallengeVo();
	        challengeVo.setRoomNum(roomNum);
	        challengeVo.setUserNum(userNum);

	        // 서비스 호출을 통해 유저 권한 조회
	        int userAuth = service.exeGetUserAuth(challengeVo);

	        // 디버깅용 메시지 출력
	        System.out.println("조회된 UserAuth 값: " + userAuth);

	        // 성공 응답 반환
	        return JsonResult.success(userAuth);

	    } catch (Exception e) {
	        // 예외 발생 시 에러 메시지 및 스택 추적 출력
	        System.err.println("UserAuth 조회 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();

	        // 클라이언트에게 실패 응답 반환
	        return JsonResult.fail("유저 권한 조회에 실패했습니다.");
	    }
	}

	
	
	// 유의사항 수정, 등록
	@PostMapping("api/rules/{roomNum}")
	public JsonResult postMethodName(
			@PathVariable int roomNum,
			@RequestBody Map<String, Object> requestData) {
		String ruleText = (String)requestData.get("ruleText");
		System.out.println("유의사항 컨트롤러"+ruleText);
		
		ChallengeVo challengevo = new ChallengeVo();
		challengevo.setRoomNum(roomNum);
		challengevo.setMissionInstruction(ruleText);
		
		int  newRules = service.exeRuleUpdate(challengevo);
		
		return JsonResult.success(newRules);
	}
	
	// 방번호로 유의사항 1개 가져오기
	@GetMapping("api/getRule/{roomNum}")
	public JsonResult getRule(@PathVariable int roomNum) {
		
		ChallengeVo roomRule = service.exeGetRule(roomNum);
		
		return JsonResult.success(roomRule);
	}
	
	// 방번호로 히스토리 리스트 가져오기
	@GetMapping("api/historyList/{roomNum}")
	public JsonResult historyList(
			@PathVariable int roomNum,
			@RequestParam(value = "order", defaultValue = "DESC") String order) 
	{
		System.out.println("ChallengeController-historyList");
		System.out.println(order);
		
		ChallengeVo challengevo = new ChallengeVo();
		challengevo.setRoomNum(roomNum);
		challengevo.setOrder(order);
		
		List<ChallengeVo> historyList = service.exeUserHistoryList(challengevo);
		System.out.println("히스토리 리스트" + historyList);
		return JsonResult.success(historyList);
	}
	
	
	// 유저 미션 제출
	@PostMapping("/api/submitMissionWithFiles")
	public JsonResult submitMissionWithFiles(
	    @RequestParam("missionNumber") int missionNum,
	    @RequestParam("comment") String comment,
	    @RequestParam("files") List<MultipartFile> files,
	    HttpServletRequest request) 
	{
		int userNum = JwtUtil.getNoFromHeader(request);
		System.out.println(request);
		
		// unionVo 객체 생성
		ChallengeVo challengevo = new ChallengeVo();
		challengevo.setSubmitUser(userNum);
		challengevo.setMissionNum(missionNum);
		challengevo.setSubmitComment(comment);
		System.out.println("@@@@@@"+challengevo);
		int count = service.exeAddMissionWithImages(challengevo, files);

	    // 파일 저장 로직 및 DB 저장 로직 추가
		if (count > 0) {
			return JsonResult.success("미션 이미지가 성공적으로 등록되었습니다.");
		} else {
			return JsonResult.fail("미션 이미지 등록 실패");
		}
	}
	
	// 평가 업데이트
	@PostMapping("/api/updateEvaluation")
	public JsonResult updateEvaluation(
			@RequestParam(value = "evalNum") int evalNum,
            @RequestParam("evalType") String evalType,
            HttpServletRequest request) {
     
        int userNum = JwtUtil.getNoFromHeader(request);
        
        ChallengeVo challengevo = new ChallengeVo();
        challengevo.setUserNum(userNum);
        challengevo.setEvalType(evalType);
        challengevo.setEvalNum(evalNum);
        System.out.println("평가자 상태확인"+challengevo);
        service.exeUpdateEval(challengevo);
        return JsonResult.success("평가 등록 성공.");
    }
	
	// (이벤트) 미션 제출
		@PostMapping("/api/eventMissionWithFiles")
		public JsonResult eventMissionWithFiles(
		    @RequestParam("missionNumber") int missionNum,
		    @RequestParam("comment") String comment,
		    @RequestParam("files") List<MultipartFile> files,
		    HttpServletRequest request) 
		{
			int userNum = JwtUtil.getNoFromHeader(request);
			System.out.println(request);
			
			// unionVo 객체 생성
			ChallengeVo challengevo = new ChallengeVo();
			challengevo.setSubmitUser(userNum);
			challengevo.setMissionNum(missionNum);
			challengevo.setSubmitComment(comment);
			System.out.println("@@@@@@"+challengevo);
			int count = service.exeAddEventMissionWithImages(challengevo, files);

		    // 파일 저장 로직 및 DB 저장 로직 추가
			if (count > 0) {
				return JsonResult.success("미션 이미지가 성공적으로 등록되었습니다.");
			} else {
				return JsonResult.fail("미션 이미지 등록 실패");
			}
		}

}
