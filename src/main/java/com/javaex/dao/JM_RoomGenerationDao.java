package com.javaex.dao;

import java.util.List;

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
	
	// 방타입 넘버 가져오기
	public int getRoomType(int roomNum) {
		int roomTypeNum = session.selectOne("generation.getRoomType",roomNum);
		return roomTypeNum;
	}
	
	// 보유 포인트 가져오기
	public int getUserPoint(int userNum) {
		System.out.println("따자하오"+userNum);
		int userPoint = session.selectOne("generation.getUserPoint",userNum);
		return userPoint;
	}
	
	// 성실도 가져오기
	public double getUserScore(int userNum) {
		System.out.println("따자하오"+userNum);
		double userScore = session.selectOne("generation.getUserScore",userNum);
		return userScore;
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
	
	// 방 번호로 roomDayNum 리스트 가져오기
    public List<ChallengeVo> getRoomDayList(int roomNum) {
        List<ChallengeVo> dayList = session.selectList("generation.getRoomDayNum", roomNum);
        System.out.println("ChallengeDao.getRoomDayList 결과: " + dayList);
        return dayList;
    }

    // 미션 등록
    public int insertMission(ChallengeVo challengevo) {
        session.insert("generation.insertMission", challengevo);
        return challengevo.getMissionNum();
    }

    // 미션별 이미지 등록
    public int insertMissionImage(ChallengeVo challengevo) {
        System.out.println("ChallengeDao.insertImageInfo()");
        return session.insert("generation.missionImageInsert", challengevo);
    }

    // 미션 유의사항 업데이트
    public ChallengeVo updateInstruction(ChallengeVo challengevo) {
        session.update("generation.updateInstruction",challengevo );
        return null;
    }
    
    // 방 참가
    public int joinRoom(ChallengeVo challengevo) {
        int result = session.insert("generation.joinRoom", challengevo);
        System.out.println("joinRoom 실행 결과: " + result);
        return result;
    }

    // 방 입장 포인트 가져오기
    public int getRoomPoint(ChallengeVo challengevo) {
        int roomPoint = session.selectOne("generation.getRoomPoint", challengevo);
        System.out.println("getRoomPoint 실행 결과: " + roomPoint);
        return roomPoint;
    }

    // 포인트 차감
    public int minusPoint(ChallengeVo challengevo) {
        int result = session.update("generation.minusPoint", challengevo);
        System.out.println("minusPoint 실행 결과: " + result);
        return result;
    }


}
