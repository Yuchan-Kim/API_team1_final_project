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
	public ChallengeVo getUserDetails(int userNum, int roomNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNum", userNum);
        params.put("roomNum", roomNum);
        return sqlSession.selectOne(namespace + ".getUserDetails", params);
    }

 

	// 방 삭제: roomInfo 데이터 삭제
	// evaluationImg 삭제
    public int deleteEvaluationImgsByRoomNum(int roomNum) {
        return sqlSession.delete(namespace + ".deleteEvaluationImgsByRoomNum", roomNum);
    }

    // evaluations 삭제
    public int deleteEvaluationsByRoomNum(int roomNum) {
        return sqlSession.delete(namespace + ".deleteEvaluationsByRoomNum", roomNum);
    }

    // missionImg 삭제
    public int deleteMissionImgsByRoomNum(int roomNum) {
        return sqlSession.delete(namespace + ".deleteMissionImgsByRoomNum", roomNum);
    }

    // challenges 삭제
    public int deleteChallengesByRoomNum(int roomNum) {
        return sqlSession.delete(namespace + ".deleteChallengesByRoomNum", roomNum);
    }

    // roomDay 삭제
    public int deleteRoomDaysByRoomNum(int roomNum) {
        return sqlSession.delete(namespace + ".deleteRoomDaysByRoomNum", roomNum);
    }

    // announcements 삭제
    public int deleteAnnouncementsByRoomNum(int roomNum) {
        return sqlSession.delete(namespace + ".deleteAnnouncementsByRoomNum", roomNum);
    }

    // roomChat 삭제
    public int deleteRoomChatsByRoomNum(int roomNum) {
        return sqlSession.delete(namespace + ".deleteRoomChatsByRoomNum", roomNum);
    }

    // enteredUser 삭제
    public int deleteEnteredUsers(int roomNum) {
        return sqlSession.delete(namespace + ".deleteEnteredUsers", roomNum);
    }

    // roomInfo 삭제
    public int deleteRoom(int roomNum) {
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

	// ChallengeDao.java - checkUserJoined 메서드
	public int checkUserJoined(int roomNum, int userNum) {
	    System.out.println("ChallengeDao.checkUserJoined()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("roomNum", roomNum);
	    params.put("userNum", userNum);
	    params.put("excludeStatusNum", 2);
	    return sqlSession.selectOne(namespace + ".checkUserJoined", params);
	}


	// ChallengeDao.java - joinRoom 메서드 수정
	public int joinRoom(int roomNum, int userNum) {
	    System.out.println("ChallengeDao.joinRoom() - roomNum: " + roomNum + ", userNum: " + userNum);
	    ChallengeVo existingUser = sqlSession.selectOne(namespace + ".selectEnteredUser", Map.of("roomNum", roomNum, "userNum", userNum));
	    
	    if (existingUser != null) {
	        System.out.println("Existing user found with enteredUserStatusNum: " + existingUser.getEnteredUserStatusNum());
	        if (existingUser.getEnteredUserStatusNum() == 2) {
	            // 기존 레코드를 업데이트하여 다시 참여 상태로 변경
	            Map<String, Object> params = new HashMap<>();
	            params.put("roomNum", roomNum);
	            params.put("userNum", userNum);
	            params.put("enteredUserStatusNum", 1);
	            params.put("enteredUserAuth", 2); // 필요에 따라 수정
	            int updatedRows = sqlSession.update(namespace + ".updateEnteredUser", params);
	            System.out.println("Updated enteredUserStatusNum to 1, rows affected: " + updatedRows);
	            return updatedRows;
	        } else {
	            // 이미 참여 중인 경우
	            System.out.println("User is already participating in the room.");
	            return 0;
	        }
	    } else {
	        // 새로운 참가자 등록
	        System.out.println("No existing user found. Inserting new enteredUser record.");
	        Map<String, Object> params = new HashMap<>();
	        params.put("roomNum", roomNum);
	        params.put("userNum", userNum);
	        params.put("enteredUserStatusNum", 1);
	        params.put("enteredUserAuth", 2);
	        int insertedRows = sqlSession.insert(namespace + ".joinRoom", params);
	        System.out.println("Inserted new enteredUser record, rows affected: " + insertedRows);
	        return insertedRows;
	    }
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