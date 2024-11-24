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
	public JsonResult roomGeneration(
			@RequestBody int challengeType,
			HttpServletRequest request) {
		int userNum = JwtUtil.getNoFromHeader(request);
		System.out.println(userNum);
		System.out.println(challengeType);
		
		ChallengeVo challengevo = new ChallengeVo();
		challengevo.setUserNum(userNum);
		challengevo.setRoomTypeNum(challengeType);
		
		int newRoomNum = service.exeRoomGeneration(challengevo);
		System.out.println("새로생긴 방번호"+newRoomNum);
		
		return JsonResult.success(newRoomNum);
	}
	
	// 방 카테고리 키워드 업데이트
	@PostMapping("api/roomAddCategory")
	public JsonResult addCategoryToRoom(
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
	    ChallengeVo isSaved = service.exeAddCategoryToRoom(categoryVo);
	    
	    return JsonResult.success("카테고리가 성공적으로 저장되었습니다.");
	}
	
	// 방 이미지, 제목, 설명 업데이트
	@PostMapping("api/roomUpdateInfo")
	public JsonResult roomUpdateInfo(
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
		ChallengeVo isSaved = service.exeRoomUpdateInfo(categoryVo);
		    
		return JsonResult.success("카테고리가 성공적으로 저장되었습니다.");
	}


}
