package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.JM_RoomGenerationService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.ChallengeVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class JM_RoomGenerationController {
	
	@Autowired
	private JM_RoomGenerationService service;
	
	// 유저넘버 받아서 방 생성 -> 방 번호 가져오기
	@PostMapping("api/challengeType")
	public JsonResult roomUpdateStep1(
			@RequestBody int challengeType,
			HttpServletRequest request) {
		int userNum = JwtUtil.getNoFromHeader(request);
		System.out.println(userNum);
		System.out.println(challengeType);
		
		ChallengeVo challengevo = new ChallengeVo();
		challengevo.setUserNum(userNum);
		challengevo.setRoomTypeNum(challengeType);
		
		int newRoomNum = service.exeRoomUpdateStep1(challengevo);
		System.out.println("새로생긴 방번호"+newRoomNum);
		
		return JsonResult.success(newRoomNum);
	}
	
	// 방 카테고리 키워드 업데이트
	@PostMapping("api/roomAddCategory")
	public JsonResult roomUpdateStep2(
			@RequestParam int roomNum, // 요청 데이터 객체
			@RequestParam int categoryNum, // 요청 데이터 객체
			@RequestParam String keyword, // 요청 데이터 객체
	        HttpServletRequest request) {
	    // JWT에서 사용자 번호 추출
	    int userNum = JwtUtil.getNoFromHeader(request);
	    System.out.println("UserNum: " + userNum);
	    System.out.println("RoomNum: " + roomNum);
	    System.out.println("CategoryNum: " + categoryNum);
	    System.out.println("Keyword: " + keyword);

	    // 데이터 객체 생성
	    ChallengeVo categoryVo = new ChallengeVo();
	    categoryVo.setRoomNum(roomNum);
	    categoryVo.setCategoryNum(categoryNum);
	    categoryVo.setRoomKeyword(keyword);
	    categoryVo.setUserNum(userNum); // 생성한 사용자 정보 추가

	    // 서비스 호출
	    ChallengeVo isSaved = service.exeRoomUpdateStep2(categoryVo);
	    
	    return JsonResult.success("카테고리가 성공적으로 저장되었습니다.");
	}
	
	// 방 이미지, 제목, 설명 업데이트
	@PostMapping("api/roomUpdateInfo")
	public JsonResult roomUpdateStep3(
			@RequestParam int roomNum, // 요청 데이터 객체
			@RequestParam String roomTitle, // 요청 데이터 객체
			@RequestParam String roomInfo, // 요청 데이터 객체
			@RequestParam String roomThumbNail, // 요청 데이터 객체
		    HttpServletRequest request) {
		// JWT에서 사용자 번호 추출
		int userNum = JwtUtil.getNoFromHeader(request);
		System.out.println("UserNum: " + userNum);
		System.out.println("RoomNum: " + roomNum);
		System.out.println("RoomTitle: " + roomTitle);
		System.out.println("RoomInfo: " + roomInfo);
		System.out.println("RoomThumbNail: " + roomThumbNail);

		// 데이터 객체 생성
		ChallengeVo categoryVo = new ChallengeVo();
		categoryVo.setRoomNum(roomNum);
		categoryVo.setRoomTitle(roomTitle);
		categoryVo.setRoomInfo(roomInfo);
		categoryVo.setRoomThumbNail(roomThumbNail);
		categoryVo.setUserNum(userNum); // 생성한 사용자 정보 추가

		// 서비스 호출
		ChallengeVo isSaved = service.exeRoomUpdateStep3(categoryVo);
		    
		return JsonResult.success("타이틀,설명,이미지가 성공적으로 저장되었습니다.");
	}
	
	// 방 타입 가져오기
	@GetMapping("api/step4/roomType/{roomNum}")
    public JsonResult getRoomType(
    		@PathVariable int roomNum) {
        
    	int roomTypeNum = service.getRoomType(roomNum);
    	System.out.println("컨트롤러 데이터 확인"+roomTypeNum);
        return JsonResult.success(roomTypeNum);
    }
	
	// 보유 포인트 가져오기
	@GetMapping("api/room/points")
	public JsonResult getUserPoint(HttpServletRequest request) {
	    
		int userNum = JwtUtil.getNoFromHeader(request);
	    int userPoint = service.getUserPoint(userNum);
	    System.out.println("보유 포인트 확인"+userPoint);
	    return JsonResult.success(userPoint);
	 }
	
	// 성실도 가져오기
	@GetMapping("api/room/score")
	public JsonResult getUserScore(HttpServletRequest request) {
		    
		int userNum = JwtUtil.getNoFromHeader(request);
		double userScore = service.getUserScore(userNum);
		System.out.println("보유 성실도 확인"+userScore);
		return JsonResult.success(userScore);
	}
	
	// 방 상세설정 업데이트
	@PostMapping("api/genebang/step4")
	public JsonResult roomUpdateStep4(
			@RequestParam int roomNum, // 요청 데이터 객체
			@RequestParam int roomMaxNum, // 요청 데이터 객체
			@RequestParam int roomMinNum, // 요청 데이터 객체
			@RequestParam int roomEnterPoint, // 요청 데이터 객체
			@RequestParam int roomEnterRate, // 요청 데이터 객체
			@RequestParam int regionNum, // 요청 데이터 객체
			HttpServletRequest request) {
		
		int userNum = JwtUtil.getNoFromHeader(request);
		System.out.println("UserNum: " + userNum);
		System.out.println("RoomNum: " + roomNum);
		System.out.println("roomMaxNum: " + roomMaxNum);
		System.out.println("roomMinNum: " + roomMinNum);
		System.out.println("roomEnterPoint: " + roomEnterPoint);
		System.out.println("roomEnterRate: " + roomEnterRate);
		System.out.println("regionNum: " + regionNum);

		// 데이터 객체 생성
		ChallengeVo categoryVo = new ChallengeVo();
		categoryVo.setRoomNum(roomNum);
		categoryVo.setRoomMaxNum(roomMaxNum);
		categoryVo.setRoomMinNum(roomMinNum);
		categoryVo.setRoomPoint(roomEnterPoint);
		categoryVo.setRoomRate(roomEnterRate);
		categoryVo.setRegionNum(regionNum);
		categoryVo.setUserNum(userNum); // 생성한 사용자 정보 추가

		// 서비스 호출
		ChallengeVo isSaved = service.exeRoomUpdateStep4(categoryVo);
			    
		return JsonResult.success("상세정보가 성공적으로 저장되었습니다.");
	}
	
	// 방 시작시간 + 기간 업데이트
	@PostMapping("api/genebang/step5")
	public JsonResult roomUpdateStep5(
		@RequestParam int roomNum, // 요청 데이터 객체
		@RequestParam String roomStartDate, // 요청 데이터 객체
		@RequestParam int challengePeriod, // 요청 데이터 객체
		HttpServletRequest request) {
		
		int userNum = JwtUtil.getNoFromHeader(request);
		System.out.println("UserNum: " + userNum);
		System.out.println("RoomNum: " + roomNum);
		System.out.println("roomStartDate: " + roomStartDate);
		System.out.println("challengePeriod: " + challengePeriod);

		// 데이터 객체 생성
		ChallengeVo categoryVo = new ChallengeVo();
		categoryVo.setRoomNum(roomNum);
		categoryVo.setRoomStartDate(roomStartDate);
		categoryVo.setPeriodType(challengePeriod);
		categoryVo.setUserNum(userNum); // 생성한 사용자 정보 추가

		// 서비스 호출
		ChallengeVo isSaved = service.exeRoomUpdateStep5(categoryVo);
			    
		return JsonResult.success("상세정보가 성공적으로 저장되었습니다.");
	}
	
	// 방 평가방법 + 요일 업데이트
	@PostMapping("api/genebang/step7")
	public JsonResult roomUpdateStep7(
	        @RequestBody Map<String, Object> requestData,
	        HttpServletRequest request) {

	    try {
	        // JSON 데이터에서 각 값을 추출
	        int roomNum = Integer.parseInt(requestData.get("roomNum").toString());
	        int evaluationType = Integer.parseInt(requestData.get("evaluationType").toString());

	        // roomDayNum 배열을 String 리스트에서 Integer 리스트로 변환
	        List<Integer> roomDayNum = ((List<?>) requestData.get("roomDayNum"))
	                .stream()
	                .map(Object::toString) // 객체를 String으로 변환
	                .map(Integer::parseInt) // String을 Integer로 변환
	                .toList();

	        int userNum = JwtUtil.getNoFromHeader(request);

	        System.out.println("UserNum: " + userNum);
	        System.out.println("RoomNum: " + roomNum);
	        System.out.println("EvaluationType: " + evaluationType);
	        System.out.println("RoomDayNum: " + roomDayNum);

	        // 데이터 객체 생성
	        ChallengeVo categoryVo = new ChallengeVo();
	        categoryVo.setRoomNum(roomNum);
	        categoryVo.setEvaluationType(evaluationType);
	        categoryVo.setUserNum(userNum); // 생성한 사용자 정보 추가

	        // 서비스 호출
	        ChallengeVo isSaved = service.exeRoomUpdateStep7(categoryVo, roomDayNum);

	        return JsonResult.success("평가방법이 성공적으로 저장되었습니다.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return JsonResult.fail("데이터 처리 중 오류가 발생했습니다.");
	    }
	}
	
	// 유의사항 등록
	@PostMapping("api/genebang/saveInstruction")
    public JsonResult saveInstruction(
    		@RequestParam("roomNum") int roomNum,
            @RequestParam("missionInstruction") String missionInstruction) {
		
		System.out.println("유의사항");
		System.out.println(roomNum);
		System.out.println(missionInstruction);
        
        ChallengeVo challengevo = new ChallengeVo();
        	
        challengevo.setRoomNum(roomNum);
        challengevo.setMissionInstruction(missionInstruction);
        service.saveInstruction(challengevo);
           
        return JsonResult.success("유의사항이 성공적으로 저장되었습니다.");
    }
	
	//미션 등록
	@PostMapping("api/genebang/saveMission")
    public JsonResult saveMission(
            @RequestParam("roomNum") int roomNum,
            @RequestParam("missionName") String missionName,
            @RequestParam("missionMethod") String missionMethod,
            @RequestParam("missionTypeNum") int missionTypeNum,
            @RequestPart("files") List<MultipartFile> files) {
		
		System.out.println(missionName);
		System.out.println(missionMethod);
		System.out.println(missionTypeNum);
        
        ChallengeVo challengevo = new ChallengeVo();
        challengevo.setRoomNum(roomNum);
        challengevo.setMissionName(missionName);
        challengevo.setMissionMethod(missionMethod);
        challengevo.setMissionTypeNum(missionTypeNum);
        
        // 서비스 호출로 미션 저장
        service.registerMissions(challengevo, files);
        
        return JsonResult.success("미션이 성공적으로 저장되었습니다.");
    }




}
