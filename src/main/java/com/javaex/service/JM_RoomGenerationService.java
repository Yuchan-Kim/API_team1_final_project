package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaex.dao.JM_RoomGenerationDao;

@Service
public class JM_RoomGenerationService {
	
	@Autowired
	private JM_RoomGenerationDao dao;
	
	// 유저넘버 받아서 방 생성 -> 방 번호 가져오기
	public int exeRoomGeneration(int  userNum) {
		int newRoomNum = dao.roomGeneration(userNum);
		System.out.println(newRoomNum);
		return newRoomNum;
	}

}
