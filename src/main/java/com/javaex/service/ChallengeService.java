// ChallengeService.java
package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.ChallengeDao;
import com.javaex.vo.ChallengeVo;

@Service
public class ChallengeService {

	@Autowired
	private ChallengeDao challengeDao;

	// 방 정보 조회
	public List<ChallengeVo> exeGetRoomInfo(int roomNum){
		System.out.println("ChallengeService.exeGetRoomInfo()");
		return challengeDao.getRoomInfo(roomNum);
	}

	// 방 헤더 정보 조회 with userNum
	public ChallengeVo getRoomHeaderInfo(int roomNum, int userNum) {
		System.out.println("ChallengeService.getRoomHeaderInfo()");
		return challengeDao.getRoomHeaderInfo(roomNum, userNum);
	}
	
	


	// 사용자 권한 조회
	public int getUserAuth(int roomNum, int userNum) {
		System.out.println("ChallengeService.getUserAuth()");
		return challengeDao.getUserAuth(roomNum, userNum);
	}

	// 방 시작 시간 업데이트
	public boolean updateRoomStartDate(int roomNum, String newStartDate) {
		System.out.println("ChallengeService.updateRoomStartDate()");
		return challengeDao.updateRoomStartDate(roomNum, newStartDate) > 0;
	}

	// 챌린지 시작: roomStartDate를 현재 시간으로 설정하고 roomStatusNum을 3으로 변경
	public boolean startChallenge(int roomNum) {
		System.out.println("ChallengeService.startChallenge()");
		return challengeDao.startChallenge(roomNum) > 0;
	}

	public boolean deleteRoom(int roomNum) {
        System.out.println("ChallengeService.deleteRoom() - roomNum: " + roomNum);

        boolean allDeleted = true;

        // 삭제 순서: evaluationImg -> evaluations -> missionImg -> challenges -> roomDay -> announcements -> roomChat -> enteredUser -> roomInfo

        // evaluationImg 삭제
        int result = challengeDao.deleteEvaluationImgsByRoomNum(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete evaluationImg for roomNum: " + roomNum);
            allDeleted = false;
        }

        // evaluations 삭제
        result = challengeDao.deleteEvaluationsByRoomNum(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete evaluations for roomNum: " + roomNum);
            allDeleted = false;
        }

        // missionImg 삭제
        result = challengeDao.deleteMissionImgsByRoomNum(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete missionImg for roomNum: " + roomNum);
            allDeleted = false;
        }

        // challenges 삭제
        result = challengeDao.deleteChallengesByRoomNum(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete challenges for roomNum: " + roomNum);
            allDeleted = false;
        }

        // roomDay 삭제
        result = challengeDao.deleteRoomDaysByRoomNum(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete roomDay for roomNum: " + roomNum);
            allDeleted = false;
        }

        // announcements 삭제
        result = challengeDao.deleteAnnouncementsByRoomNum(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete announcements for roomNum: " + roomNum);
            allDeleted = false;
        }

        // roomChat 삭제
        result = challengeDao.deleteRoomChatsByRoomNum(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete roomChats for roomNum: " + roomNum);
            allDeleted = false;
        }

        // enteredUser 삭제
        result = challengeDao.deleteEnteredUsers(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete enteredUsers for roomNum: " + roomNum);
            allDeleted = false;
        }

        // roomInfo 삭제
        result = challengeDao.deleteRoom(roomNum);
        if (result < 0) {
            System.err.println("Failed to delete roomInfo for roomNum: " + roomNum);
            allDeleted = false;
        }

        return allDeleted;
    }

	// 챌린지 종료: roomStatusNum을 3으로 변경
	public boolean endChallenge(int roomNum) {
		System.out.println("ChallengeService.endChallenge()");
		return challengeDao.updateRoomStatus(roomNum, 3) > 0;
	}

	// 기간 완료: roomStatusNum을 4로 변경
	public boolean completePeriod(int roomNum) {
		System.out.println("ChallengeService.completePeriod()");
		return challengeDao.updateRoomStatus(roomNum, 4) > 0;
	}



    public boolean insertPointHistory(int userNum, int points, int purposeNum, String info) {
        System.out.println("ChallengeService.insertPointHistory()");
        ChallengeVo pointHistory = new ChallengeVo();
        pointHistory.setUserNum(userNum);
        pointHistory.setPoint(points);
        pointHistory.setPointPurposeNum(purposeNum);
        pointHistory.setHistoryInfo(info);

        return challengeDao.insertPointHistory(pointHistory) > 0;
    }

	// 챌린지 모집 시작: roomStatusNum을 2로 변경
	public boolean startRecruit(int roomNum) {
		System.out.println("ChallengeService.startRecruit()");
		return challengeDao.updateRoomStatus(roomNum, 2) > 0;
	}

	// 챌린지 상태 초기화: roomStatusNum을 1으로 변경
	public boolean resetChallengeStatus(int roomNum) {
		System.out.println("ChallengeService.resetChallengeStatus()");
		return challengeDao.updateRoomStatus(roomNum, 1) > 0;
	}

	public boolean leaveRoom(int roomNum, int userNum) {
		System.out.println("ChallengeService.leaveRoom()");

		// 현재 방의 참여자 수 조회
		int participantCount = challengeDao.getParticipantCount(roomNum);

		// 현재 유저의 enteredUserAuth 조회
		Integer enteredUserAuth = challengeDao.getUserAuth(roomNum, userNum);

		if (enteredUserAuth == null) {
			return false; // 유저가 방에 참여하지 않았음
		}

		// 나가는 유저의 enteredUserAuth를 0으로 변경
		challengeDao.updateEnteredUserAuth(roomNum, userNum, 0);

		if (participantCount >= 2) {
			if (enteredUserAuth == 1) {
				// 나가는 유저가 방장인 경우, 다음 유저에게 방장 권한 부여
				Integer nextUserNum = challengeDao.getNextUserNum(roomNum, userNum);
				if (nextUserNum != null) {
					challengeDao.updateEnteredUserAuth(roomNum, nextUserNum, 1);
				}
			}
			// 나가는 유저의 enteredUserStatusNum을 2으로 설정하여 비활성화
			challengeDao.updateEnteredUserStatus(roomNum, userNum, 2);
		} else {
			// 참여자가 1명 이하이면 방 삭제
			challengeDao.deleteEnteredUsers(roomNum);
			challengeDao.deleteRoom(roomNum);
		}

		return true;
	}
	
	

	
	public ChallengeVo getUserStatus(int roomNum, int userNum) {
	    System.out.println("ChallengeService.getUserStatus()");
	    return challengeDao.getUserStatus(roomNum, userNum);
	}
	
	
	
	@Transactional
	public boolean joinRoom(int roomNum, int userNum) {
	    System.out.println("ChallengeService.joinRoom() - roomNum: " + roomNum + ", userNum: " + userNum);
	    boolean result = challengeDao.joinRoom(roomNum, userNum) > 0;
	    System.out.println("ChallengeService.joinRoom() - joinRoom result: " + result);
	    return result;
	}

	public boolean roomEnterPoint(int userNum, int roomEnterPoint) {
	    System.out.println("ChallengeService.roomEnterPoint() - userNum: " + userNum + ", roomEnterPoint: " + roomEnterPoint);
	    boolean result = challengeDao.roomEnterPoint(userNum, roomEnterPoint) > 0;
	    System.out.println("ChallengeService.roomEnterPoint() - roomEnterPoint result: " + result);
	    return result;
	}

	public boolean reactivateUser(int roomNum, int userNum) {
	    System.out.println("ChallengeService.reactivateUser() - roomNum: " + roomNum + ", userNum: " + userNum);
	    boolean result = challengeDao.updateEnteredUserStatus(roomNum, userNum, 1) > 0;
	    System.out.println("ChallengeService.reactivateUser() - reactivateUser result: " + result);
	    return result;
	}

	public int checkUserJoined(int roomNum, int userNum) {
	    System.out.println("ChallengeService.checkUserJoined() - roomNum: " + roomNum + ", userNum: " + userNum);
	    int result = challengeDao.checkUserJoined(roomNum, userNum);
	    System.out.println("ChallengeService.checkUserJoined() - result: " + result);
	    return result;
	}

	public ChallengeVo getUserDetails(int userNum, int roomNum) {
	    System.out.println("ChallengeService.getUserDetails() - userNum: " + userNum + ", roomNum: " + roomNum);
	    ChallengeVo userDetails = challengeDao.getUserDetails(userNum, roomNum);
	    System.out.println("ChallengeService.getUserDetails() - userDetails: " + userDetails);
	    return userDetails;
	}

	public int getEnteredUserNum(int roomNum) {
	    System.out.println("ChallengeService.getEnteredUserNum() - roomNum: " + roomNum);
	    int count = challengeDao.getEnteredUserNum(roomNum);
	    System.out.println("ChallengeService.getEnteredUserNum() - participant count: " + count);
	    return count;
	}

	public int checkPoint(int userNum) {
	    System.out.println("ChallengeService.checkPoint() - userNum: " + userNum);
	    int points = challengeDao.checkPoint(userNum);
	    System.out.println("ChallengeService.checkPoint() - points: " + points);
	    return points;
	}


}