package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ChallengeVo;

@Repository
public class JM_SearchDao {
	
	@Autowired
	private SqlSession session;
	
	
	// 메인페이지 방리스트 가져오기
	public List<ChallengeVo> roomList( ) {
		System.out.println("ChallengeService.exeMissionList");
			
		List<ChallengeVo> roomList = session.selectList("jmsearch.getRoomList");
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
	
	// 메인페이지 종료 된 방 리스트 가져오기
	public List<ChallengeVo> closeRoomList( ) {
		System.out.println("ChallengeService.exeMissionList");
				
		List<ChallengeVo> roomList = session.selectList("jmsearch.getCloseRoomList");
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
	
	// 메인페이지 방리스트 가져오기
	public List<ChallengeVo> categoryRoomList(String category) {
				
		List<ChallengeVo> roomList = session.selectList("jmsearch.getCategoryRoomList",category);
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
	
	// 검색어로 필터링 리스트 가져오기
	public List<ChallengeVo> roomFilterList(String query) {
			
		List<ChallengeVo> roomList = session.selectList("jmsearch.getFilterList",query);
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
	
	// 방 타입 가져오기
	public List<ChallengeVo> RoomtypeList( ) {
				
		List<ChallengeVo> RoomtypeList = session.selectList("jmsearch.getRoomtypeList");

		return RoomtypeList;
	}
		
	// 카테고리 가져오기
	public List<ChallengeVo> CategoryList( ) {
				
		List<ChallengeVo> CategoryList = session.selectList("jmsearch.getCategoryList");

		return CategoryList;
	}
		
	// 기간 가져오기
	public List<ChallengeVo> PeriodList( ) {
				
		List<ChallengeVo> PeriodList = session.selectList("jmsearch.getPeriodList");

		return PeriodList;
	}
		
	// 지역 가져오기
	public List<ChallengeVo> RegionsList( ) {
				
		List<ChallengeVo> RegionsList = session.selectList("jmsearch.getRegionsList");

		return RegionsList;
	}
}
