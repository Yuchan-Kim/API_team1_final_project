 // ChallengeController.java
package com.javaex.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// 추가된 import 문
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.ChallengeService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.ChallengeVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;

	// 기존 방 정보 조회 엔드포인트
	@GetMapping("/{roomNum}")
	public JsonResult getRoomInfo(@PathVariable int roomNum) {
		System.out.println("ChallengeController.getRoomInfo()");
		List<ChallengeVo> roomInfoList = challengeService.exeGetRoomInfo(roomNum);
		
		if (roomInfoList != null && !roomInfoList.isEmpty()) {
			System.out.println("아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ" +roomInfoList.get(0).getRoomMaxNum());
			return JsonResult.success(roomInfoList);
			
		} else {
			return JsonResult.fail("데이터를 불러오는데 실패했습니다");
		}
	}

	// 방 헤더 정보 조회 엔드포인트 with userNum
	@GetMapping("/header/{roomNum}")
	public JsonResult getRoomHeaderInfo(@PathVariable int roomNum, HttpServletRequest request) {
		System.out.println("ChallengeController.getRoomHeaderInfo()");
		int userNum = JwtUtil.getNoFromHeader(request); // JWT에서 userNum 추출

		ChallengeVo headerInfo = challengeService.getRoomHeaderInfo(roomNum, userNum);
		if (headerInfo != null) {
			return JsonResult.success(headerInfo);
		} else {
			return JsonResult.fail("헤더 정보를 불러오는데 실패했습니다");
		}
	}

	// 기존 사용자 인증 상태 조회 엔드포인트
	@GetMapping("/user/{roomNum}")
	public JsonResult getUserAuth(@PathVariable int roomNum,  HttpServletRequest request) {
		System.out.println("ChallengeController.getUserAuth()");

		int userNum = JwtUtil.getNoFromHeader(request);
		if (userNum != 0) {
			int auth = challengeService.getUserAuth(roomNum, userNum);
			return JsonResult.success(auth);
		} else {
			return JsonResult.fail("사용자 정보를 찾을 수 없습니다.");
		}
	}

	// ChallengeController.java

	@PutMapping("/start-challenge/{roomNum}")
	public JsonResult startChallenge(
	        @PathVariable int roomNum, 
	        HttpServletRequest request) {
	    System.out.println("ChallengeController.startChallenge()");

	    // JWT를 통해 사용자 인증
	    int userNum = JwtUtil.getNoFromHeader(request);
	    if (userNum <= 0) {
	        return JsonResult.fail("인증되지 않은 사용자입니다.");
	    }

	    // 사용자 권한 확인 (예: 방장인지 확인)
	    int auth = challengeService.getUserAuth(roomNum, userNum);
	    if (auth < 1) { // 필요한 권한 수준에 따라 조정 (예: 1 이상은 방장)
	        return JsonResult.fail("권한이 없습니다.");
	    }

	    // 챌린지 시작 로직 호출
	    boolean updateSuccess = challengeService.startChallenge(roomNum);
	    if (updateSuccess) {
	        // 방 정보 조회
	        ChallengeVo roomInfo = challengeService.getRoomHeaderInfo(roomNum, userNum);
	        String roomTitle = roomInfo != null ? roomInfo.getRoomTitle() : "챌린지";

	        // 알림 생성 (msgSender는 서비스 계층에서 처리됨)
	        challengeService.createNotices(roomNum, roomTitle, "챌린지 시작 알림", roomTitle + "의 챌린지가 시작되었습니다.");

	        return JsonResult.success("챌린지가 시작되었습니다.");
	    } else {
	        return JsonResult.fail("챌린지 시작에 실패했습니다.");
	    }
	}

 // **챌린지 종료 엔드포인트 (roomStatusNum을 3으로 변경)**
	// ChallengeController.java

	@PutMapping("/end-challenge/{roomNum}")
	public JsonResult endChallenge(
	        @PathVariable int roomNum, 
	        HttpServletRequest request) {
	    System.out.println("ChallengeController.endChallenge()");

	    // JWT를 통해 사용자 인증
	    int userNum = JwtUtil.getNoFromHeader(request);
	    if (userNum <= 0) {
	        return JsonResult.fail("인증되지 않은 사용자입니다.");
	    }

	    // 사용자 권한 확인
	    int auth = challengeService.getUserAuth(roomNum, userNum);
	    if (auth < 1) {
	        return JsonResult.fail("권한이 없습니다.");
	    }

	    // 챌린지 종료 업데이트
	    boolean updateSuccess = challengeService.endChallenge(roomNum);
	    if (!updateSuccess) {
	        return JsonResult.fail("챌린지 종료에 실패했습니다.");
	    }

	    // 포인트 계산 및 저장 로직...

	    // 방 정보 조회
	    ChallengeVo roomInfo = challengeService.getRoomHeaderInfo(roomNum, userNum);
	    String roomTitle = roomInfo != null ? roomInfo.getRoomTitle() : "챌린지";

	    // 알림 생성 (msgSender는 서비스 계층에서 처리됨)
	    challengeService.createNotices(roomNum, roomTitle, "챌린지 종료 알림", roomTitle + roomInfo.getRoomNum() + " 번방"+ "의 챌린지가 종료되었습니다.");

	    return JsonResult.success("챌린지가 성공적으로 종료되었습니다.");
	}

	@PutMapping("/complete-period/{roomNum}")
	public JsonResult completePeriod(
	        @PathVariable int roomNum, 
	        HttpServletRequest request) { 
	    System.out.println("ChallengeController.completePeriod()");

	    // JWT를 통해 사용자 인증
	    int userNum = JwtUtil.getNoFromHeader(request);
	    if (userNum <= 0) {
	        return JsonResult.fail("인증되지 않은 사용자입니다.");
	    }

	    // 사용자 권한 확인 (예: 방장인지 확인)
	    int auth = challengeService.getUserAuth(roomNum, userNum);
	    if (auth < 1) { // 방장 권한 확인
	        return JsonResult.fail("권한이 없습니다.");
	    }

	    // roomStatusNum을 4로 업데이트
	    boolean updateSuccess = challengeService.completePeriod(roomNum);
	    if (updateSuccess) {
	        // 방 정보 조회
	        ChallengeVo roomInfo = challengeService.getRoomHeaderInfo(roomNum, userNum);
	        String roomTitle = roomInfo != null ? roomInfo.getRoomTitle() : "챌린지";

	        // 알림 생성 (msgSender는 서비스 계층에서 처리됨)
	        challengeService.createNotices(roomNum, roomTitle, "챌린지 기간 완료 알림", roomTitle + roomInfo.getRoomNum() + " 번방"+ "의 챌린지 기간이 완료되었습니다.");

	        return JsonResult.success("챌린지 기간이 완료되었습니다.");
	    } else {
	        return JsonResult.fail("챌린지 기간 완료에 실패했습니다.");
	    }
	}


	@PostMapping("/join/{roomNum}")
	public JsonResult joinRoom(@PathVariable int roomNum, HttpServletRequest request) {
	    System.out.println("ChallengeController.joinRoom() - Attempting to join room: " + roomNum);

	    // JWT를 통해 사용자 인증
	    int userNum = JwtUtil.getNoFromHeader(request);
	    System.out.println("Authenticated userNum: " + userNum);
	    if (userNum <= 0) {
	        System.out.println("Authentication failed for userNum: " + userNum);
	        return JsonResult.fail("인증되지 않은 사용자입니다.");
	    }

	    // 방 정보 조회
	    List<ChallengeVo> roomInfo = challengeService.exeGetRoomInfo(roomNum);
	    System.out.println("Room info retrieved: " + roomInfo);
	    if (roomInfo == null || roomInfo.isEmpty()) {
	        System.out.println("Room not found: " + roomNum);
	        return JsonResult.fail("존재하지 않는 방입니다.");
	    }

	    // 이미 참가했는지 확인
	    int alreadyJoined = challengeService.checkUserJoined(roomNum, userNum);
	    System.out.println("User already joined: " + alreadyJoined);
	    if (alreadyJoined > 0) {
	        System.out.println("User already participated in room: " + roomNum);
	        return JsonResult.fail("이미 참가한 챌린지입니다.");
	    }

	    // 현재 방의 최대 인원과 현재 참여자 수 조회
	    int enteredUser = challengeService.getEnteredUserNum(roomNum);
	    System.out.println("Current entered user count: " + enteredUser + " / Max: " + roomInfo.get(0).getRoomMaxNum());
	    if (enteredUser >= roomInfo.get(0).getRoomMaxNum()) {
	        System.out.println("Room is full: " + roomNum);
	        return JsonResult.fail("인원이 다 차서 참여할 수 없습니다.");
	    }

	    // 유저 보유 포인트 확인
	    int remainingPT = challengeService.checkPoint(userNum);
	    System.out.println("User remaining points: " + remainingPT);
	    if (remainingPT < roomInfo.get(0).getRoomPoint()) {
	        System.out.println("Insufficient points for user: " + userNum);
	        return JsonResult.fail("포인트가 부족합니다.");
	    }

	    // 유저 상태 확인
	    ChallengeVo existingUser = challengeService.getUserStatus(roomNum, userNum);
	    System.out.println("Existing user status: " + existingUser);

	    // 방 유형에 따른 처리
	    if (roomInfo.get(0).getRoomTypeName().equals("일반")) {
	        System.out.println("Room type: 일반");
	        // 일반 방 처리
	        if (existingUser != null && existingUser.getEnteredUserStatusNum() == 2) {
	            System.out.println("Reactivating user participation");
	            boolean updateSuccess = challengeService.reactivateUser(roomNum, userNum);
	            System.out.println("Reactivation success: " + updateSuccess);
	            if (updateSuccess) {
	                return JsonResult.success("참여 상태로 변경되었습니다.");
	            } else {
	                System.out.println("Failed to reactivate user participation");
	                return JsonResult.fail("참여 상태를 변경하는 데 실패했습니다.");
	            }
	        } else{
	            System.out.println("Adding new participant");
	            // 새로운 참가자 추가
	            boolean joinSuccess = challengeService.joinRoom(roomNum, userNum);
	            System.out.println("Join room success: " + joinSuccess);
	            if (joinSuccess) {
	                int enteredUserAuth = challengeService.getUserAuth(roomNum, userNum);
	                System.out.println("Entered user auth: " + enteredUserAuth);
	               
	                return JsonResult.success(enteredUserAuth);
	            } else {
	                System.out.println("Failed to join room");
	                return JsonResult.fail("참가에 실패했습니다.");
	            }
	        }
	    } else {
	        // 챌린지 방 처리
	        if (roomInfo.get(0).getRoomPoint() >= 0 && roomInfo.get(0).getRoomRate() < 0) {
	            System.out.println("Handling 챌린지 방 with roomPoint > 0 and roomRate >= 0");
	            if (existingUser != null && existingUser.getEnteredUserStatusNum() == 2) {
	                System.out.println("Reactivating user participation and deducting roomEnterPoint");
	                challengeService.roomEnterPoint(userNum, roomInfo.get(0).getRoomPoint());
	                boolean updateSuccess = challengeService.reactivateUser(roomNum, userNum);
	                System.out.println("Reactivation success: " + updateSuccess);
	                if (updateSuccess) {
	                    return JsonResult.success("참여 상태로 변경되었습니다.");
	                } else {
	                    System.out.println("Failed to reactivate user participation");
	                    return JsonResult.fail("참여 상태를 변경하는 데 실패했습니다.");
	                }
	            } else{
	                System.out.println("Deducting roomEnterPoint and adding new participant");
	                challengeService.roomEnterPoint(userNum, roomInfo.get(0).getRoomPoint());
	                boolean joinSuccess = challengeService.joinRoom(roomNum, userNum);
	                System.out.println("Join room success: " + joinSuccess);
	                if (joinSuccess) {
	                    int enteredUserAuth = challengeService.getUserAuth(roomNum, userNum);
	                    System.out.println("Entered user auth: " + enteredUserAuth);
	                    
	                    return JsonResult.success(enteredUserAuth);
	                } else {
	                    System.out.println("Failed to join room");
	                    return JsonResult.fail("참가에 실패했습니다.");
	                }
	            }
	        } else if (roomInfo.get(0).getRoomPoint() >= 0 && roomInfo.get(0).getRoomRate() >= 0) {
	            System.out.println("Handling 챌린지 방 with roomPoint > 0 and roomRate > 0");
	            double checkMyRate = challengeService.checkmyRate(userNum);
	            
	            if (existingUser != null && existingUser.getEnteredUserStatusNum() == 2) {
	                System.out.println("Reactivating user participation and deducting roomEnterPoint");
	                if (roomInfo.get(0).getRoomRate() > checkMyRate) {
	                	 System.out.println("Failed to reactivate user participation");
		                    return JsonResult.fail("입장 성실도가 낮습니다.");
	                }else {
	                	 challengeService.roomEnterPoint(userNum, roomInfo.get(0).getRoomPoint());
	 	                boolean updateSuccess = challengeService.reactivateUser(roomNum, userNum);
	 	                System.out.println("Reactivation success: " + updateSuccess);
	 	                if (updateSuccess) {
	 	                    return JsonResult.success("참여 상태로 변경되었습니다.");
	 	                } else {
	 	                    System.out.println("Failed to reactivate user participation");
	 	                    return JsonResult.fail("참여 상태를 변경하는 데 실패했습니다.");
	 	                }
	                }
	               
	            } else if (existingUser == null) {
	                System.out.println("Deducting roomEnterPoint and adding new participant");
	                if (roomInfo.get(0).getRoomRate() > checkMyRate) {
	                	 System.out.println("Failed to reactivate user participation");
		                 return JsonResult.fail("입장 성실도가 낮습니다.");
	                }else {
	                	 challengeService.roomEnterPoint(userNum, roomInfo.get(0).getRoomPoint());
	 	                boolean joinSuccess = challengeService.joinRoom(roomNum, userNum);
	 	                System.out.println("Join room success: " + joinSuccess);
	 	                if (joinSuccess) {
	 	                    int enteredUserAuth = challengeService.getUserAuth(roomNum, userNum);
	 	                    System.out.println("Entered user auth: " + enteredUserAuth);
	 	                   
	 	                    return JsonResult.success(enteredUserAuth);
	 	                } else {
	 	                    System.out.println("Failed to join room");
	 	                    return JsonResult.fail("참가에 실패했습니다.");
	 	                }
	                }
	            }
	        }
	    }

	    // 모든 조건을 만족하지 못하는 경우 실패 응답 반환
	    System.out.println("All conditions failed, returning failure");
	    return JsonResult.fail("참가에 실패했습니다.");
	}





	// **모집 시작 엔드포인트**
	@PutMapping("/start-recruit/{roomNum}")
	public JsonResult startRecruit(@PathVariable int roomNum, HttpServletRequest request) {
		System.out.println("ChallengeController.startRecruit()");

		// JWT를 통해 사용자 인증
		int userNum = JwtUtil.getNoFromHeader(request);
		if (userNum <= 0) {
			return JsonResult.fail("인증되지 않은 사용자입니다.");
		}

		// 사용자 권한 확인 (예: 방장인지 확인)
		int auth = challengeService.getUserAuth(roomNum, userNum);
		if (auth < 1) { // 방장 권한 확인
			return JsonResult.fail("권한이 없습니다.");
		}

		// roomStatusNum을 2로 업데이트 (모집 시작 상태)
		boolean updateSuccess = challengeService.startRecruit(roomNum);
		if (updateSuccess) {
			return JsonResult.success("모집이 시작되었습니다.");
		} else {
			return JsonResult.fail("모집 시작에 실패했습니다.");
		}
	}

	// **방 시작 시간 연장 엔드포인트**
	@PutMapping("/header/{roomNum}")
	public JsonResult updateRoomStartDate(@PathVariable int roomNum, @RequestBody Map<String, String> payload, HttpServletRequest request) {
		System.out.println("ChallengeController.updateRoomStartDate()");

		// JWT를 통해 사용자 인증
		int userNum = JwtUtil.getNoFromHeader(request);
		if (userNum <= 0) {
			return JsonResult.fail("인증되지 않은 사용자입니다.");
		}

		// 사용자 권한 확인 (예: 방장인지 확인)
		int auth = challengeService.getUserAuth(roomNum, userNum);
		if (auth < 1) { // 방장 권한 확인
			return JsonResult.fail("권한이 없습니다.");
		}

		String newStartDate = payload.get("roomStartDate");
		if (newStartDate == null || newStartDate.isEmpty()) {
			return JsonResult.fail("새로운 시작 시간이 필요합니다.");
		}

		// roomStartDate 업데이트
		boolean updateSuccess = challengeService.updateRoomStartDate(roomNum, newStartDate);
		if (updateSuccess) {
			// 챌린지가 시작되지 않았으므로 상태 초기화
			
			return JsonResult.success("시작 시간이 업데이트되었습니다.");
		} else {
				return JsonResult.fail("챌린지 상태 초기화에 실패했습니다.");
		}
	}
	

	
	// 방 삭제 엔드포인트 수정 (이미 존재)
    @DeleteMapping("/delete-room/{roomNum}")
    public JsonResult deleteRoom(
            @PathVariable int roomNum,
            HttpServletRequest request) {
        System.out.println("ChallengeController.deleteRoom() - roomNum: " + roomNum);

        // JWT를 통해 사용자 인증
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        // 사용자 권한 확인 (예: 방장인지 확인)
        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) { // 방장 권한 확인
            return JsonResult.fail("권한이 없습니다.");
        }

        // 방 삭제 서비스 호출
        boolean deleteSuccess = challengeService.deleteRoom(roomNum, userNum);
        if (deleteSuccess) {
            return JsonResult.success("방이 성공적으로 삭제되었습니다.");
        } else {
            return JsonResult.fail("방 삭제에 실패했습니다.");
        }
    }
	 // 방 나가기 엔드포인트 추가
    @PutMapping("/leave-room/{roomNum}")
    public JsonResult leaveRoom(
            @PathVariable int roomNum,
            HttpServletRequest request) {
        System.out.println("ChallengeController.leaveRoom() - roomNum: " + roomNum);

        // JWT를 통해 사용자 인증
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        // 방 나가기 서비스 호출
        boolean leaveSuccess = challengeService.leaveRoom(roomNum, userNum);
        if (leaveSuccess) {
            return JsonResult.success("챌린지를 나갔습니다.");
        } else {
            return JsonResult.fail("챌린지 나가기에 실패했습니다.");
        }
    }
    
    @PutMapping("/leave-room-no-refund/{roomNum}")
    public JsonResult leaveRoom_noRefund(
            @PathVariable int roomNum,
            HttpServletRequest request) {
        System.out.println("ChallengeController.leaveRoom() - roomNum: " + roomNum);

        // JWT를 통해 사용자 인증
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        // 방 나가기 서비스 호출
        boolean leaveSuccess = challengeService.leaveRoom_noRefund(roomNum, userNum);
        if (leaveSuccess) {
            return JsonResult.success("챌린지를 나갔습니다.");
        } else {
            return JsonResult.fail("챌린지 나가기에 실패했습니다.");
        }
    }
    
    
    
 // 1. 지역 수정
    @PutMapping("/update-region/{roomNum}")
    public JsonResult updateRegion(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        System.out.println("ChallengeController.updateRegion()");
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        String regionNum = (String) payload.get("regionNum");
        Integer regionId = Integer.parseInt(regionNum);

        if (regionNum == null) {
            return JsonResult.fail("지역 번호가 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateRegion(roomNum, regionId);
        if (updateSuccess) {
            return JsonResult.success("지역이 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("지역 업데이트에 실패했습니다.");
        }
    }

    // 2. 방 키워드 수정
    @PutMapping("/update-keyword/{roomNum}")
    public JsonResult updateKeyword(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        System.out.println("ChallengeController.updateKeyword()");
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        String roomKeyword = (String) payload.get("roomKeyword");
        if (roomKeyword == null || roomKeyword.trim().isEmpty()) {
            return JsonResult.fail("방 키워드가 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateRoomKeyword(roomNum, roomKeyword);
        if (updateSuccess) {
            return JsonResult.success("방 키워드가 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("방 키워드 업데이트에 실패했습니다.");
        }
    }

    // 3. 방 제목 수정
    @PutMapping("/update-title/{roomNum}")
    public JsonResult updateTitle(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        System.out.println("ChallengeController.updateTitle()");
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        String roomTitle = (String) payload.get("roomTitle");
        if (roomTitle == null || roomTitle.trim().isEmpty()) {
            return JsonResult.fail("방 제목이 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateRoomTitle(roomNum, roomTitle);
        if (updateSuccess) {
            return JsonResult.success("방 제목이 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("방 제목 업데이트에 실패했습니다.");
        }
    }

    // 4. 방 썸네일 수정
    @PutMapping("/update-thumbnail/{roomNum}")
    public JsonResult updateThumbnail(@PathVariable int roomNum, @RequestParam("roomThumbnail") MultipartFile roomThumbnail, HttpServletRequest request) {
        System.out.println("ChallengeController.updateThumbnail()");
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        if (roomThumbnail.isEmpty()) {
            return JsonResult.fail("방 썸네일 파일을 업로드해주세요.");
        }
        
        String fileName ="";
        String uploadDir = "/app/upload/"; 
        File dir = new File(uploadDir); 
        fileName = System.currentTimeMillis() + "_" + roomThumbnail.getOriginalFilename();
        String filePath = uploadDir + fileName;

        try {
			byte[] fileData = roomThumbnail.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileData);
			bos.close();
		} catch (Exception e) {
			System.out.println("파일 저장 중 오류: " + e.getMessage());
			return null;
		}
        boolean updateSuccess = challengeService.updateRoomThumbnail(roomNum, fileName);
        if (updateSuccess) {
            return JsonResult.success("방 썸네일이 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("방 썸네일 업데이트에 실패했습니다.");
        }
    }

 // 최소 참가 인원 수정
    @PutMapping("/update-minnum/{roomNum}")
    public JsonResult updateMinNum(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        Object roomMinNumObj = payload.get("roomMinNum");
        Integer roomMinNum = null; // 초기값 설정

        if (roomMinNumObj instanceof Integer) {
            roomMinNum = (Integer) roomMinNumObj;
        } else if (roomMinNumObj instanceof Number) {
            roomMinNum = ((Number) roomMinNumObj).intValue();
        } else if (roomMinNumObj instanceof String) {
            try {
                roomMinNum = Integer.parseInt((String) roomMinNumObj);
            } catch (NumberFormatException e) {
                return JsonResult.fail("유효한 최소 참가 인원이 필요합니다.");
            }
        } else {
            return JsonResult.fail("유효한 최소 참가 인원이 필요합니다.");
        }

        if (roomMinNum < 1) {
            return JsonResult.fail("유효한 최소 참가 인원이 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateRoomMinNum(roomNum, roomMinNum);
        if (updateSuccess) {
            return JsonResult.success("최소 참가 인원이 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("최소 참가 인원 업데이트에 실패했습니다.");
        }
    }



    // 최대 참가 인원 수정
    @PutMapping("/update-maxnum/{roomNum}")
    public JsonResult updateMaxNum(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        Object roomMaxNumObj = payload.get("roomMaxNum");
        Integer roomMaxNum = null;

        if (roomMaxNumObj instanceof Integer) {
            roomMaxNum = (Integer) roomMaxNumObj;
        } else if (roomMaxNumObj instanceof String) {
            try {
                roomMaxNum = Integer.parseInt((String) roomMaxNumObj);
            } catch (NumberFormatException e) {
                return JsonResult.fail("유효한 최대 참가 인원이 필요합니다.");
            }
        } else {
            return JsonResult.fail("유효한 최대 참가 인원이 필요합니다.");
        }

        if (roomMaxNum < 1) {
            return JsonResult.fail("유효한 최대 참가 인원이 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateRoomMaxNum(roomNum, roomMaxNum, userNum);
        if (updateSuccess) {
            return JsonResult.success("최대 참가 인원이 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("최대 참가 인원 업데이트에 실패했습니다.");
        }
    }

    // 방 참가 포인트 수정
    @PutMapping("/update-enterpoint/{roomNum}")
    public JsonResult updateEnterPoint(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth != 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        Object roomEnterPointObj = payload.get("roomEnterPoint");
        Integer roomEnterPoint = null;

        if (roomEnterPointObj instanceof Integer) {
            roomEnterPoint = (Integer) roomEnterPointObj;
        } else if (roomEnterPointObj instanceof String) {
            try {
                roomEnterPoint = Integer.parseInt((String) roomEnterPointObj);
            } catch (NumberFormatException e) {
                return JsonResult.fail("유효한 방 참가 포인트가 필요합니다.");
            }
        } else {
            return JsonResult.fail("유효한 방 참가 포인트가 필요합니다.");
        }

        if (roomEnterPoint < 0) {
            return JsonResult.fail("유효한 방 참가 포인트가 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateRoomEnterPoint(roomEnterPoint,roomNum );
        if (updateSuccess) {
            return JsonResult.success("방 참가 포인트가 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("방 참가 포인트 업데이트에 실패했습니다.");
        }
    }
    // 8. 방 참가 비율 수정
    @PutMapping("/update-enterrate/{roomNum}")
    public JsonResult updateEnterRate(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        System.out.println("ChallengeController.updateEnterRate()");
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        Object roomEnterRateObj = payload.get("roomEnterRate");
        Integer roomEnterRate = null;

        if (roomEnterRateObj instanceof Number) {
            roomEnterRate = ((Number) roomEnterRateObj).intValue();
        } else if (roomEnterRateObj instanceof String) {
            try {
                roomEnterRate = Integer.parseInt((String) roomEnterRateObj);
            } catch (NumberFormatException e) {
                return JsonResult.fail("유효한 방 참가 비율(0-100%)이 필요합니다.");
            }
        } else {
            return JsonResult.fail("유효한 방 참가 비율(0-100%)이 필요합니다.");
        }

        if (roomEnterRate < 0 || roomEnterRate > 100) {
            return JsonResult.fail("유효한 방 참가 비율(0-100%)이 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateRoomEnterRate(roomNum, roomEnterRate);
        if (updateSuccess) {
            return JsonResult.success("방 참가 비율이 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("방 참가 비율 업데이트에 실패했습니다.");
        }
    }


    // 9. 평가 유형 수정
    @PutMapping("/update-evaluationtype/{roomNum}")
    public JsonResult updateEvaluationType(@PathVariable int roomNum, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
        System.out.println("ChallengeController.updateEvaluationType()");
        int userNum = JwtUtil.getNoFromHeader(request);
        if (userNum <= 0) {
            return JsonResult.fail("인증되지 않은 사용자입니다.");
        }

        int auth = challengeService.getUserAuth(roomNum, userNum);
        if (auth < 1) {
            return JsonResult.fail("권한이 없습니다.");
        }

        Integer evaluationType = (Integer) payload.get("evaluationType");
        if (evaluationType == null || evaluationType < 1) {
            return JsonResult.fail("유효한 평가 유형이 필요합니다.");
        }

        boolean updateSuccess = challengeService.updateEvaluationType(roomNum, evaluationType);
        if (updateSuccess) {
            return JsonResult.success("평가 유형이 성공적으로 업데이트되었습니다.");
        } else {
            return JsonResult.fail("평가 유형 업데이트에 실패했습니다.");
        }
    }
    
 // 지역 목록 가져오기
    @GetMapping("/regions")
    public JsonResult getRegions() {
        List<ChallengeVo> regions = challengeService.getRegions();
        return JsonResult.success(regions);
    }
    
    
 // 현재 참가자 수 가져오기
    @GetMapping("/participant-count/{roomNum}")
    public JsonResult getParticipantCount(@PathVariable int roomNum) {
        int count = challengeService.getParticipantCount(roomNum);
        return JsonResult.success(count);
    }


}