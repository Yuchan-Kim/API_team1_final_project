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
		System.out.println("ChallengeService.exeMissionList");
		
		List<ChallengeVo> roomList = dao.roomList();
		System.out.println("ChallengeService" + roomList);

		return roomList;
	}
}
