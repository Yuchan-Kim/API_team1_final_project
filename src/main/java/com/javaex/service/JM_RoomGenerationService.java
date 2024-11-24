package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.JM_RoomGenerationDao;
import com.javaex.vo.ChallengeVo;

@Service
public class JM_RoomGenerationService {
	
	@Autowired
	private JM_RoomGenerationDao dao;
	
	// 유저넘버 받아서 방 생성 -> 방 번호 가져오기
	public int exeRoomGeneration(ChallengeVo  challengevo) {
		int newRoomNum = dao.roomGeneration(challengevo);
		System.out.println("새로생긴 방번호"+ newRoomNum);
		return newRoomNum;
	}
	
	// 방 카테고리 키워드 업데이트
	public ChallengeVo exeAddCategoryToRoom(ChallengeVo  challengevo) {
			
		ChallengeVo newRoomCategory = dao.addCategoryToRoom(challengevo);
		System.out.println("새로운 카테고리+ 키워드"+ newRoomCategory);
			
		return newRoomCategory;
	}
	
	// 방 이미지, 제목, 설명 업데이트
	public ChallengeVo exeRoomUpdateInfo(ChallengeVo  challengevo) {
				
		ChallengeVo newRoomTitle = dao.roomUpdateInfo(challengevo);
		System.out.println("새로운 제목+설명+이미지"+ newRoomTitle);
				
		return newRoomTitle;
	}

}
