// ChallengeService.java
package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // 방 헤더 정보 조회
    public ChallengeVo getRoomHeaderInfo(int roomNum) {
        System.out.println("ChallengeService.getRoomHeaderInfo()");
        return challengeDao.getRoomHeaderInfo(roomNum);
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

    // 방 삭제
    public boolean deleteRoom(int roomNum) {
        System.out.println("ChallengeService.deleteRoom()");
        // 관련된 모든 데이터 삭제 (enteredUser 등)
        return challengeDao.deleteEnteredUsers(roomNum) > 0 && challengeDao.deleteRoom(roomNum) > 0;
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

    // 사용자 참가 여부 확인
    public boolean checkUserJoined(int roomNum, int userNum) {
        System.out.println("ChallengeService.checkUserJoined()");
        return challengeDao.checkUserJoined(roomNum, userNum) > 0;
    }

    // 사용자 참가 처리
    public boolean joinRoom(int roomNum, int userNum) {
        System.out.println("ChallengeService.joinRoom()");
        return challengeDao.joinRoom(roomNum, userNum) > 0;
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

}