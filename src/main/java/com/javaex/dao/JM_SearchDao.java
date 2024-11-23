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
			
		List<ChallengeVo> roomList = session.selectList("jm-search.getRoomList");
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}

}
