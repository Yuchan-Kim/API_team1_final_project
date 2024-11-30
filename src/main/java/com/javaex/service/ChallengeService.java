// ChallengeService.java
package com.javaex.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

	public boolean deleteRoom(int roomNum, int userNum) {
        System.out.println("ChallengeService.deleteRoom() - roomNum: " + roomNum);
        List<ChallengeVo> returnPoint = challengeDao.getRoomInfo(roomNum);
        challengeDao.returnEnterPoint(userNum,returnPoint.get(0).getRoomPoint());
        
        
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
		return challengeDao.updateRoomStatus(roomNum, 4) > 0;
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


	public boolean leaveRoom(int roomNum, int userNum) {
		System.out.println("ChallengeService.leaveRoom()");

		// 현재 방의 참여자 수 조회
		int participantCount = challengeDao.getParticipantCount(roomNum);

		// 현재 유저의 enteredUserAuth 조회
		Integer enteredUserAuth = challengeDao.getUserAuth(roomNum, userNum);
		
		

		if (enteredUserAuth == null) {
			return false; // 유저가 방에 참여하지 않았음
		}
		
		 List<ChallengeVo> returnPoint = challengeDao.getRoomInfo(roomNum);
	        challengeDao.returnEnterPoint(userNum,returnPoint.get(0).getRoomPoint());

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
		} 

		return true;
	}
	
	public boolean leaveRoom_noRefund(int roomNum, int userNum) {
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


	public boolean reactivateUser(int roomNum, int userNum) {
	    System.out.println("ChallengeService.reactivateUser() - roomNum: " + roomNum + ", userNum: " + userNum);
	    boolean result = challengeDao.updateEnteredUserStatus(roomNum, userNum, 1) > 0;
	    int result02 = challengeDao.updateEnteredUserAuth(roomNum, userNum, 2);
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
	
	// ChallengeService.java

	@Transactional
	public void createNotices(int roomNum, String roomTitle, String statusTitle, String statusMessage) {
	    System.out.println("ChallengeService.createNotices()");

	    // 방 소유자(userNum) 조회
	    Integer roomOwnerNum = challengeDao.getRoomOwner(roomNum);
	    if (roomOwnerNum == null) {
	        System.err.println("방 소유자를 찾을 수 없습니다. roomNum: " + roomNum);
	        return;
	    }

	    // 해당 방에 참여한 모든 사용자 조회
	    List<ChallengeVo> participants = challengeDao.getParticipants(roomNum);

	    if (participants != null && !participants.isEmpty()) {
	        for (ChallengeVo participant : participants) {
	            int userNum = participant.getUserNum();

	            // 방 소유자에게는 알림을 보내지 않도록 예외 처리
	            if (userNum == roomOwnerNum) {
	                continue;
	            }

	            // Notice 객체 생성
	            ChallengeVo notice = new ChallengeVo();
	            notice.setUserNum(userNum);
	            notice.setMsgSender(roomOwnerNum);
	            notice.setNoticeTitle(statusTitle);
	            notice.setNoticeMsg(statusMessage);
	            notice.setIsCheck(0); // 읽지 않음

	            // 알림 삽입
	            boolean inserted = challengeDao.insertNotice(notice);
	            if (!inserted) {
	                System.err.println("사용자에게 알림을 삽입하지 못했습니다. userNum: " + userNum);
	            }
	        }
	    } else {
	        System.out.println("참여자가 없습니다. roomNum: " + roomNum);
	    }
	}

	// 1. 지역 수정
    public boolean updateRegion(int roomNum, int regionNum) {
        System.out.println("ChallengeService.updateRegion()");
        return challengeDao.updateRegion(roomNum, regionNum) > 0;
    }

    // 2. 방 키워드 수정
    public boolean updateRoomKeyword(int roomNum, String roomKeyword) {
        System.out.println("ChallengeService.updateRoomKeyword()");
        return challengeDao.updateRoomKeyword(roomNum, roomKeyword) > 0;
    }

    // 3. 방 제목 수정
    public boolean updateRoomTitle(int roomNum, String roomTitle) {
        System.out.println("ChallengeService.updateRoomTitle()");
        return challengeDao.updateRoomTitle(roomNum, roomTitle) > 0;
    }

    // 4. 방 썸네일 수정
    @Transactional
    public boolean updateRoomThumbnail(int roomNum, MultipartFile roomThumbnail) {
        System.out.println("ChallengeService.updateRoomThumbnail()");
        try {
            // 파일 저장 로직 (예: 로컬 파일 시스템)
            String uploadDir = "/path/to/your/uploads/room_thumbnails/"; // 실제 경로로 변경
            String originalFilename = roomThumbnail.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String newFilename = "room_" + roomNum + "_" + System.currentTimeMillis() + fileExtension;
            Path filePath = Paths.get(uploadDir, newFilename);
            Files.createDirectories(filePath.getParent());
            roomThumbnail.transferTo(filePath.toFile());

            // 기존 썸네일 파일 삭제 (선택 사항)
            ChallengeVo roomInfo = challengeDao.getRoomInfoByRoomNum(roomNum);
            if (roomInfo != null && roomInfo.getRoomThumbNail() != null) {
                Path oldFilePath = Paths.get(uploadDir, roomInfo.getRoomThumbNail());
                Files.deleteIfExists(oldFilePath);
            }

            // DB 업데이트
            return challengeDao.updateRoomThumbnail(roomNum, newFilename) > 0;
        } catch (IOException e) {
            System.err.println("방 썸네일 업데이트 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    // 5. 최소 참가 인원 수정
    public boolean updateRoomMinNum(int roomNum, int roomMinNum) {
        System.out.println("ChallengeService.updateRoomMinNum()");
        return challengeDao.updateRoomMinNum(roomNum, roomMinNum) > 0;
    }

    // 6. 최대 참가 인원 수정
    public boolean updateRoomMaxNum(int roomNum, int roomMaxNum) {
        System.out.println("ChallengeService.updateRoomMaxNum()");
        return challengeDao.updateRoomMaxNum(roomNum, roomMaxNum) > 0;
    }

    // 7. 방 참가 포인트 수정
    public boolean updateRoomEnterPoint(int roomEnterPoint,int roomNum) {
        System.out.println("ChallengeService.updateRoomEnterPoint()");
        return challengeDao.updateRoomEnterPoint( roomEnterPoint,roomNum) > 0;
    }

    // 8. 방 참가 비율 수정
    public boolean updateRoomEnterRate(int roomNum, int roomEnterRate) {
        System.out.println("ChallengeService.updateRoomEnterRate()");
        return challengeDao.updateRoomEnterRate(roomNum, roomEnterRate) > 0;
    }

    // 9. 평가 유형 수정
    public boolean updateEvaluationType(int roomNum, int evaluationType) {
        System.out.println("ChallengeService.updateEvaluationType()");
        return challengeDao.updateEvaluationType(roomNum, evaluationType) > 0;
    }
    
    // 지역 목록 가져오기
    public List<ChallengeVo> getRegions() {
        return challengeDao.getRegions();
    }

    // 현재 참가자 수 가져오기
    public int getParticipantCount(int roomNum) {
        return challengeDao.getParticipantCount(roomNum);
    }
    

    // 최대 참가 인원 수정
    public boolean updateRoomMaxNum(int roomNum, int roomMaxNum, int userNum) {
        int currentParticipants = challengeDao.getParticipantCount(roomNum);
        ChallengeVo roomInfo = challengeDao.getRoomInfoByRoomNum(roomNum);

        if (roomMaxNum > 20) {
            return false;
        }

        if (roomInfo.getRoomStatusNum() == 1) {
            if (roomMaxNum <= roomInfo.getRoomMinNum()) {
                return false;
            }
        } else if (roomInfo.getRoomStatusNum() == 2) {
            if (roomMaxNum < currentParticipants) {
                return false;
            }
        } else {
            return false;
        }

        return challengeDao.updateRoomMaxNum(roomNum, roomMaxNum) > 0;
    }

   
    // 유저 포인트 조회
    public int getUserPoints(int userNum) {
        return challengeDao.getUserPoints(userNum);
    }
    

	public boolean roomEnterPoint(int userNum, int roomEnterPoint) {
	    System.out.println("ChallengeService.roomEnterPoint() - userNum: " + userNum + ", roomEnterPoint: " + roomEnterPoint);
	    boolean result = challengeDao.roomEnterPoint(userNum, roomEnterPoint) > 0;
	    System.out.println("ChallengeService.roomEnterPoint() - roomEnterPoint result: " + result);
	    return result;
	}
	
	public int checkmyRate (int userNum) {
		return challengeDao.checkmyRate(userNum);
	}


}