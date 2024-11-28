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

	public void returnEnterPoint(int userNum , int returnPoint) {
		Map<String,Object> params = new HashMap<>();
		params.put("userNum", userNum);
		params.put("returnPoint", returnPoint);
		sqlSession.insert(namespace +".returnEnterPoint", params);
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



	public ChallengeVo getUserStatus(int roomNum, int userNum) {
		System.out.println("ChallengeDao.getUserStatus()");
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		return sqlSession.selectOne("challenge.getUserStatus", params);
	}



	public int getEnteredUserNum(int roomNum) {
		return sqlSession.selectOne(namespace + ".getEnteredUserNum", roomNum);
	}

	public int getroomMaxNum(int roomNum) {
		return sqlSession.selectOne("challenge.getRoomMaxNum");
	}

	public Integer checkPoint(int userNum) {
		Integer result = sqlSession.selectOne(namespace + ".getUserPoints", userNum);
		return result != null ? result : 0; // null일 경우 0 반환
	}

	public int joinRoom(int roomNum, int userNum) {
		System.out.println("ChallengeDao.joinRoom() - roomNum: " + roomNum + ", userNum: " + userNum);
		ChallengeVo existingUser = sqlSession.selectOne(namespace + ".selectEnteredUser", Map.of("roomNum", roomNum, "userNum", userNum));

		if (existingUser != null) {
			System.out.println("ChallengeDao.joinRoom() - Existing user found with enteredUserStatusNum: " + existingUser.getEnteredUserStatusNum());
			if (existingUser.getEnteredUserStatusNum() == 2) {
				// 기존 레코드를 업데이트하여 다시 참여 상태로 변경
				Map<String, Object> params = new HashMap<>();
				params.put("roomNum", roomNum);
				params.put("userNum", userNum);
				params.put("enteredUserStatusNum", 1);
				params.put("enteredUserAuth", 2); // 필요에 따라 수정
				int updatedRows = sqlSession.update(namespace + ".updateEnteredUser", params);
				System.out.println("ChallengeDao.joinRoom() - Updated enteredUserStatusNum to 1, rows affected: " + updatedRows);
				return updatedRows;
			} else {
				// 이미 참여 중인 경우
				System.out.println("ChallengeDao.joinRoom() - User is already participating in the room.");
				return 0;
			}
		} else {
			
			int roomMaker = sqlSession.selectOne(namespace +".selectParticipants", roomNum);
			if (roomMaker == 0) {
				// 새로운 참가자 등록
				System.out.println("ChallengeDao.joinRoom() - No existing user found. Inserting new enteredUser record.");
				Map<String, Object> params = new HashMap<>();
				params.put("roomNum", roomNum);
				params.put("userNum", userNum);
				params.put("enteredUserStatusNum", 1);
				params.put("enteredUserAuth", 1);
				int insertedRows = sqlSession.insert(namespace + ".joinRoom", params);
				System.out.println("ChallengeDao.joinRoom() - Inserted new enteredUser record, rows affected: " + insertedRows);
				return insertedRows;
			}else {
				// 새로운 참가자 등록
				System.out.println("ChallengeDao.joinRoom() - No existing user found. Inserting new enteredUser record.");
				Map<String, Object> params = new HashMap<>();
				params.put("roomNum", roomNum);
				params.put("userNum", userNum);
				params.put("enteredUserStatusNum", 1);
				params.put("enteredUserAuth", 2);
				int insertedRows = sqlSession.insert(namespace + ".joinRoom", params);
				System.out.println("ChallengeDao.joinRoom() - Inserted new enteredUser record, rows affected: " + insertedRows);
				return insertedRows;
			}
			
		}
	}

	public int roomEnterPoint(int userNum, int roomEnterPoint) {
		System.out.println("ChallengeDao.roomEnterPoint() - userNum: " + userNum + ", roomEnterPoint: " + roomEnterPoint);
		Map<String, Object> params = new HashMap<>();
		params.put("userNum", userNum);
		params.put("roomEnterPoint", roomEnterPoint);
		int result = sqlSession.insert(namespace + ".roomEnterPoint", params);
		System.out.println("ChallengeDao.roomEnterPoint() - Insert result: " + result);
		return result;
	}

	public int updateEnteredUserStatus(int roomNum, int userNum, int newStatus) {
		System.out.println("ChallengeDao.updateEnteredUserStatus() - roomNum: " + roomNum + ", userNum: " + userNum + ", newStatus: " + newStatus);
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		params.put("enteredUserStatusNum", newStatus);
		int result = sqlSession.update("challenge.updateEnteredUserStatus", params);
		System.out.println("ChallengeDao.updateEnteredUserStatus() - Update result: " + result);
		return result;
	}

	public int checkUserJoined(int roomNum, int userNum) {
		System.out.println("ChallengeDao.checkUserJoined() - roomNum: " + roomNum + ", userNum: " + userNum);
		Map<String, Object> params = new HashMap<>();
		params.put("roomNum", roomNum);
		params.put("userNum", userNum);
		params.put("excludeStatusNum", 2);
		int count = sqlSession.selectOne(namespace + ".checkUserJoined", params);
		System.out.println("ChallengeDao.checkUserJoined() - count: " + count);
		return count;
	}

	public ChallengeVo getUserDetails(int userNum, int roomNum) {
		System.out.println("ChallengeDao.getUserDetails() - userNum: " + userNum + ", roomNum: " + roomNum);
		Map<String, Object> params = new HashMap<>();
		params.put("userNum", userNum);
		params.put("roomNum", roomNum);
		ChallengeVo userDetails = sqlSession.selectOne(namespace + ".getUserDetails", params);
		System.out.println("ChallengeDao.getUserDetails() - userDetails: " + userDetails);
		return userDetails;
	}

	/**
	 * 알림 삽입
	 * @param notice ChallengeVo 객체 (userNum, msgSender, noticeTitle, noticeMsg, isCheck 필드 필요)
	 * @return 삽입 성공 여부
	 */
	public boolean insertNotice(ChallengeVo notice) {
		System.out.println("ChallengeDao.insertNotice()");
		int result = sqlSession.insert(namespace + ".insertNoticeForRoom", notice);
		return result > 0;
	}

	/**
	 * 특정 방에 참여한 모든 사용자 조회
	 * @param roomNum 방 번호
	 * @return 참여자 목록
	 */
	public List<ChallengeVo> getParticipants(int roomNum) {
		System.out.println("ChallengeDao.getParticipants() - roomNum: " + roomNum);
		return sqlSession.selectList(namespace + ".selectParticipants", roomNum);
	}


	// 방 소유자(userNum) 조회 메서드 추가
	public Integer getRoomOwner(int roomNum) {
		return sqlSession.selectOne(namespace + ".selectRoomOwner", roomNum);
	}
	
	
    // 1. 지역 수정
    public int updateRegion(int roomNum, int regionNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("regionNum", regionNum);
        return sqlSession.update(namespace + ".updateRegion", params);
    }

    // 2. 방 키워드 수정
    public int updateRoomKeyword(int roomNum, String roomKeyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomKeyword", roomKeyword);
        return sqlSession.update(namespace + ".updateRoomKeyword", params);
    }

    // 3. 방 제목 수정
    public int updateRoomTitle(int roomNum, String roomTitle) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomTitle", roomTitle);
        return sqlSession.update(namespace + ".updateRoomTitle", params);
    }

    // 4. 방 썸네일 수정
    public int updateRoomThumbnail(int roomNum, String newThumbnailPath) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomThumbnail", newThumbnailPath);
        return sqlSession.update(namespace + ".updateRoomThumbnail", params);
    }

    // 5. 최소 참가 인원 수정
    public int updateRoomMinNum(int roomNum, int roomMinNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomMinNum", roomMinNum);
        return sqlSession.update(namespace + ".updateRoomMinNum", params);
    }

    // 6. 최대 참가 인원 수정
    public int updateRoomMaxNum(int roomNum, int roomMaxNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomMaxNum", roomMaxNum);
        return sqlSession.update(namespace + ".updateRoomMaxNum", params);
    }

    // 7. 방 참가 포인트 수정
    public int updateRoomEnterPoint(int roomNum, int roomEnterPoint) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomEnterPoint", roomEnterPoint);
        return sqlSession.update(namespace + ".updateRoomEnterPoint", params);
    }

    // 8. 방 참가 비율 수정
    public int updateRoomEnterRate(int roomNum, int roomEnterRate) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomEnterRate", roomEnterRate);
        return sqlSession.update(namespace + ".updateRoomEnterRate", params);
    }

    // 9. 평가 유형 수정
    public int updateEvaluationType(int roomNum, int evaluationType) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("evaluationType", evaluationType);
        return sqlSession.update(namespace + ".updateEvaluationType", params);
    }

    // 방 썸네일 조회 (기존 썸네일 삭제를 위해 추가)
    public ChallengeVo getRoomInfoByRoomNum(int roomNum) {
        return sqlSession.selectOne(namespace + ".selectRoomInfoByRoomNum", roomNum);
    }
    
 // 지역 목록 가져오기
    public List<ChallengeVo> getRegions() {
        return sqlSession.selectList(namespace + ".getRegions");
    }


    // 유저 포인트 조회
    public int getUserPoints(int userNum) {
        return sqlSession.selectOne(namespace + ".getUserPoints", userNum);
    }

    // 포인트 히스토리 추가
    public int addPointHistory(int userNum, int point, int pointPurposeNum, String historyInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNum", userNum);
        params.put("point", point);
        params.put("pointPurposeNum", pointPurposeNum);
        params.put("historyInfo", historyInfo);
        return sqlSession.insert(namespace + ".addPointHistory", params);
    }

}