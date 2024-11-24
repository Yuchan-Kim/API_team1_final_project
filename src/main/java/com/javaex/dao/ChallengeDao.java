// ChallengeDao.java
package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ChallengeVo;

@Repository
public class ChallengeDao {

	@Autowired
	private SqlSession sqlSession;

	private String namespace = "challenge";

	// 특정 roomNum의 모든 방 정보 조회
	public List<ChallengeVo> getRoomInfo(int roomNum){
		System.out.println("ChallengeDao.getRoomInfo()");
		return sqlSession.selectList(namespace + ".selectRoomInfo", roomNum);
	}
	// **사용자 상세 정보 조회 메서드**
    public ChallengeVo getUserDetails(int userNum) {
        return sqlSession.selectOne(namespace + ".selectUserDetails", userNum);
    }

    // **포인트 히스토리 삽입 메서드**
    public int insertPointHistory(ChallengeVo pointHistory) {
        return sqlSession.insert(namespace + ".insertPointHistory", pointHistory);
    }
	// 방 헤더 정보 조회 with userNum
	public ChallengeVo getRoomHeaderInfo(int roomNum, int userNum) {
		System.out.println("ChallengeDao.getRoomHeaderInfo()");
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		return sqlSession.selectOne(namespace + ".selectRoomHeaderInfo", params);
	}

	public Integer getUserAuth(int roomNum, int userNum) {
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		Integer enteredUserAuth = sqlSession.selectOne("challenge.getUserAuth", params);
		if (enteredUserAuth == null) {
			// null인 경우 적절하게 처리
			// 옵션 1: 기본 값 반환
			return 0;
			// 옵션 2: 예외 처리
			// throw new RuntimeException("User auth not found for roomNum: " + roomNum + ", userNum: " + userNum);
		}
		return enteredUserAuth;
	}



	// 방 시작 시간 업데이트
	public int updateRoomStartDate(int roomNum, String newStartDate) {
		System.out.println("ChallengeDao.updateRoomStartDate()");
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("roomStartDate", newStartDate);
		return sqlSession.update(namespace + ".updateRoomStartDate", params);
	}

	// 챌린지 시작: roomStartDate를 현재 시간으로 설정하고 roomStatusNum을 3으로 변경
	public int startChallenge(int roomNum) {
		System.out.println("ChallengeDao.startChallenge()");
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		return sqlSession.update(namespace + ".startChallenge", params);
	}

	// 방 삭제: enteredUser 데이터 삭제
	public int deleteEnteredUsers(int roomNum) {
		System.out.println("ChallengeDao.deleteEnteredUsers()");
		return sqlSession.delete(namespace + ".deleteEnteredUsers", roomNum);
	}

	// 방 삭제: roomInfo 데이터 삭제
	public int deleteRoom(int roomNum) {
		System.out.println("ChallengeDao.deleteRoom()");
		return sqlSession.delete(namespace + ".deleteRoom", roomNum);
	}

	// 방 상태 업데이트 (roomStatusNum)
	public int updateRoomStatus(int roomNum, int newStatusNum) {
		System.out.println("ChallengeDao.updateRoomStatus()");
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("roomStatusNum", newStatusNum);
		return sqlSession.update(namespace + ".updateRoomStatus", params);
	}

	// 사용자 참가 여부 확인
	public int checkUserJoined(int roomNum, int userNum) {
		System.out.println("ChallengeDao.checkUserJoined()");
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		return sqlSession.selectOne(namespace + ".checkUserJoined", params);
	}

	// 사용자 참가 처리
	public int joinRoom(int roomNum, int userNum) {
		System.out.println("ChallengeDao.joinRoom()");
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		params.put("enteredUserStatusNum", 1);
		params.put("enteredUserAuth", 2);
		return sqlSession.insert(namespace + ".joinRoom", params);
	}

	// 참여자 수 조회
	public int getParticipantCount(int roomNum) {
		return sqlSession.selectOne(namespace + ".getParticipantCount", roomNum);
	}

	// 다음 유저의 userNum 조회
	public Integer getNextUserNum(int roomNum, int currentUserNum) {
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("currentUserNum", currentUserNum);
		return sqlSession.selectOne(namespace + ".getNextUserNum", params);
	}

	// enteredUserAuth 업데이트
	public int updateEnteredUserAuth(int roomNum, int userNum, int newAuth) {
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		params.put("enteredUserAuth", newAuth);
		return sqlSession.update(namespace + ".updateEnteredUserAuth", params);
	}

	// enteredUserStatusNum 업데이트
	public int updateEnteredUserStatus(int roomNum, int userNum, int newStatus) {
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		params.put("enteredUserStatusNum", newStatus);
		return sqlSession.update(namespace + ".updateEnteredUserStatus", params);
	}

}