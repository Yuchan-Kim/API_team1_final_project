package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.JM_SearchDao;
import com.javaex.vo.ChallengeVo;

@Service
public class JM_SearchService {

	@Autowired
	private JM_SearchDao dao;
	
	// 메인페이지 방리스트 가져오기
	public List<ChallengeVo> exeRoomList( ) {
		
		List<ChallengeVo> roomList = dao.roomList();
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
	
	//카테고리별 방리스트 가져오기
	public List<ChallengeVo> exeCategoryRoomList( String category) {
		
		List<ChallengeVo> roomList = dao.categoryRoomList(category);
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
	
	//검색어로 필터링 리스트 가져오기
	public List<ChallengeVo> exeRoomFilterList( String query) {
			
		List<ChallengeVo> roomList = dao.roomFilterList(query);
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
	
	// 방 타입 가져오기
	public List<ChallengeVo> exeRoomtypeList( ) {
			
		List<ChallengeVo> RoomtypeList = dao.RoomtypeList();

		return RoomtypeList;
	}
	
	// 카테고리 가져오기
	public List<ChallengeVo> exeCategoryList( ) {
			
		List<ChallengeVo> CategoryList = dao.CategoryList();

		return CategoryList;
	}
	
	// 기간 가져오기
	public List<ChallengeVo> exePeriodList( ) {
			
		List<ChallengeVo> PeriodList = dao.PeriodList();

		return PeriodList;
	}
	
	// 지역 가져오기
	public List<ChallengeVo> exeRegionsList( ) {
			
		List<ChallengeVo> RegionsList = dao.RegionsList();

		return RegionsList;
	}
}
