package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JM_RoomGenerationDao {
	
	@Autowired
	private SqlSession session;
	
	// 유저넘버 받아서 방 생성 -> 방 번호 가져오기
	public int roomGeneration(int userNum) {
		int newRoomNum = session.selectOne("JM-challenge.getRoomEvalType", userNum);
		System.out.println("@@@@@@@@@@"+newRoomNum);
				
		return newRoomNum;
	}

}
