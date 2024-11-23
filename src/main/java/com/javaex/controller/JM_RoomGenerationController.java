package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		ChallengeVo challengevo = new ChallengeVo();
		challengevo.setSubmitUser(userNum);
		challengevo.setRoomTypeNum(challengeType);
		
		int newRoomNum = service.exeRoomGeneration(userNum);
		
		return JsonResult.success(newRoomNum);
	}

}
