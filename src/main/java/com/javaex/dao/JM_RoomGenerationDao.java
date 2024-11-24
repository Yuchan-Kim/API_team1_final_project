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
	public int roomUpdateStep1(ChallengeVo challengevo) {
		session.insert("generation.insertStep1", challengevo);
		System.out.println(challengevo.getRoomNum());
				
		return challengevo.getRoomNum();
	}
	
	// 방 카테고리 키워드 업데이트
	public ChallengeVo roomUpdateStep2(ChallengeVo challengevo) {
			
		session.update("generation.updateStep2", challengevo);
			
		return null;
	}
	
	// 방 이미지, 제목, 설명 업데이트
	public ChallengeVo roomUpdateStep3(ChallengeVo challengevo) {
				
		session.update("generation.updateStep3", challengevo);
				
		return null;
	}
	
	// 방 상세설정 업데이트
	public ChallengeVo roomUpdateStep4(ChallengeVo challengevo) {
					
		session.update("generation.updateStep4", challengevo);
					
		return null;
	}
	
	//방 기간+시작날짜 업데이트
	public ChallengeVo roomUpdateStep5(ChallengeVo challengevo) {
						
		session.update("generation.updateStep5", challengevo);
						
		return null;
	}
	
	// 방 평가 방법 업데이트
	public ChallengeVo updateEvaluationType (ChallengeVo challengevo) {
		
		session.update("generation.updateEvaluationType", challengevo);
		
		return null;
	}
	
	// 방요일 인서트하기
	public ChallengeVo insertRoomDay (ChallengeVo challengevo) {
		
		session.insert("generation.insertRoomDay", challengevo);
		
		return null;
	}

}
