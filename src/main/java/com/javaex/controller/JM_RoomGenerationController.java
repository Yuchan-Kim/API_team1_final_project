package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
