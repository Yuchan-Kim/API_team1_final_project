package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.JM_SearchService;
import com.javaex.util.JsonResult;
import com.javaex.vo.ChallengeVo;

@RestController
public class JM_SearchController {
	
	@Autowired
	private JM_SearchService service;
	
	// 메인페이지 방리스트 가져오기
	@GetMapping("api/roomList")
	public JsonResult roomList() {
		
		List<ChallengeVo> roomList = service.exeRoomList();
		
		return JsonResult.success(roomList);
	}

}
