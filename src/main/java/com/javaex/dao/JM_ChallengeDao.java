package com.javaex.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.javaex.vo.ChallengeVo;


@Repository
public class JM_ChallengeDao {
	
	@Autowired
	private SqlSession session; 
	
	// 방번호로 참여유저 리스트 가져오기
	public List<ChallengeVo> userList(int roomNum) {
		System.out.println("ChallengeDao.userList");

		List<ChallengeVo> userList = session.selectList("JM-challenge.getUsersByRoomNum", roomNum);
		System.out.println("ChallengeDao" + userList);

		return userList;
	}
	
	// 방번호로 미션 리스트 가져오기
		public List<ChallengeVo> missionList2(int roomNum) {
			System.out.println("ChallengeDao.missionList");

			List<ChallengeVo> missionList = session.selectList("JM-challenge.getMissionByRoomNum2", roomNum);
			System.out.println("ChallengeDao" + missionList);

			return missionList;
		}
	
	// 방번호로 미션리스트 + 유저 제출여부 가져오기
	public List<ChallengeVo> missionList(ChallengeVo challengevo) {
		System.out.println("ChallengeDao.missionList");

		List<ChallengeVo> missionList = session.selectList("JM-challenge.getMissionByRoomNum", challengevo);
		System.out.println("ChallengeDao" + missionList);

		return missionList;
	}
	
	// 방번호로 히스토리 리스트 가져오기
	public List<ChallengeVo> userMissionList(ChallengeVo challengevo) {
		System.out.println("ChallengeDao.userMissionList");

		List<ChallengeVo> mHistoryList = session.selectList("JM-challenge.getMissionByUser", challengevo);
		System.out.println("ChallengeDao" + mHistoryList);

		return mHistoryList;
	}
	
	// 방번호로 방설명 가져오기
	public ChallengeVo roomInfo(int roomNum) {

		ChallengeVo roomInfo = session.selectOne("JM-challenge.getRoomInfo", roomNum);
		System.out.println("방 설명" + roomInfo);
		
		return roomInfo;
	}
	
	// 방번호로 공지 1개 가져오기
	public ChallengeVo roomAnnoun(int roomNum) {
		ChallengeVo roomAnnoun = session.selectOne("JM-challenge.getRoomAnnouncement", roomNum);
		System.out.println("방 메인 공지" + roomAnnoun);
		
		return roomAnnoun;
	}
	
	// 방 평가타입 가져오기
	public int getRoomEvalType(int roomNum) {
		int roomType = session.selectOne("JM-challenge.getRoomEvalType", roomNum);
		System.out.println("@@@@@@@@@@"+roomType);
			
		return roomType;
	}
	
	// 유저권한 가져오기
	public int getUserAuth(ChallengeVo challengevo) {
		int userAuth = session.selectOne("JM-challenge.getUserAuth", challengevo);
		System.out.println("@@@@@@@@@@"+userAuth);
		
		return userAuth;
	}
	
	// 유의사항 업데이트
	public int ruleUpdate(ChallengeVo challengevo) {
		int newRule = session.update("JM-challenge.ruleUpdate", challengevo);
		
		return newRule;
	}
	
	// 방번호로 유의사항 가져오기
	public ChallengeVo getRule(int roomNum) {
		ChallengeVo roomRule = session.selectOne("JM-challenge.getRule", roomNum);
		System.out.println("방 유의사항" + roomRule);
		
		return roomRule;
	}
	
	/* 제출 정보 입력 후 평가번호 반환 */
	public int insertMission(ChallengeVo challengevo) {
		System.out.println("ChallengeDao.insertDetail()");

		// 평가 등록 실행 후, 자동 생성된 evalNum이 challengevo 객체에 설정됨
		session.insert("JM-challenge.evalInsert", challengevo);

		// challengevo에 설정된 evalNum을 반환
		return challengevo.getEvalNum();
	}
	
	/* 이미지 정보 저장(제출) */
	public int insertImageInfo(ChallengeVo challengevo) {
		System.out.println("ChallengeDao.insertImageInfo()");

		// 이미지 정보 삽입 쿼리 실행
		return session.insert("JM-challenge.infoImageInsert", challengevo);
	}
	
	// 평가 업데이트
	public ChallengeVo updateEval(ChallengeVo challengevo) {
		System.out.println("ChallengeDao.updateEval");
		
		session.update("JM-challenge.updateEval", challengevo); 
		return  null;
	}
	
}
