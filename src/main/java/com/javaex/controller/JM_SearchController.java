package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// 카테고리별 방리스트 가져오기
	@GetMapping("api/roomFilter/category")
	public JsonResult categoryRoomList(@RequestParam String category) {
			
		List<ChallengeVo> roomList = service.exeCategoryRoomList(category);
			
		return JsonResult.success(roomList);
	}
	
	// 검색어로 필터링 리스트 가져오기
	@GetMapping("api/roomFilter/search")
	public JsonResult roomFilterList(@RequestParam String query) {
		
		List<ChallengeVo> filterList = service.exeRoomFilterList(query);
		
		return JsonResult.success(filterList);
	}
	
	// 방 타입 가져오기
	@GetMapping("api/Roomtype")
	public JsonResult RoomtypeList() {
			
		List<ChallengeVo> RoomtypeList = service.exeRoomtypeList();
			
		return JsonResult.success(RoomtypeList);
	}
	
	// 카테고리 가져오기
	@GetMapping("api/Category")
	public JsonResult CategoryList() {
				
		List<ChallengeVo> CategoryList = service.exeCategoryList();
				
		return JsonResult.success(CategoryList);
	}
	
	// 기간 가져오기
	@GetMapping("api/Period")
	public JsonResult PeriodList() {
				
		List<ChallengeVo> PeriodList = service.exePeriodList();
				
		return JsonResult.success(PeriodList);
	}
	
	// 지역 가져오기
	@GetMapping("api/Regions")
	public JsonResult RegionsList() {
				
		List<ChallengeVo> RegionsList = service.exeRegionsList();
				
		return JsonResult.success(RegionsList);
	}

}
