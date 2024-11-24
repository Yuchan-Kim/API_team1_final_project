package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ChallengeVo;

@Repository
public class JM_RoomGenerationDao {
	
	@Autowired
	private SqlSession session;
	
	// 유저넘버 받아서 방 생성 -> 방 번호 가져오기
	public int roomGeneration(ChallengeVo challengevo) {
		session.insert("generation.insertRoomType", challengevo);
		System.out.println(challengevo.getRoomNum());
				
		return challengevo.getRoomNum();
	}
	
	// 방 카테고리 키워드 업데이트
	public ChallengeVo addCategoryToRoom(ChallengeVo challengevo) {
			
		session.update("generation.updateCategory", challengevo);
			
		return null;
	}
	
	// 방 카테고리 키워드 업데이트
	public ChallengeVo roomUpdateInfo(ChallengeVo challengevo) {
				
		session.update("generation.updateITD", challengevo);
				
		return null;
	}

}
