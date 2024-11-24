// ChallengeController.java
package com.javaex.controller;

import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// 추가된 import 문
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	// **방 시작 시간 업데이트 엔드포인트 (현재 시간으로 설정 및 roomStatusNum 변경)**
	@PutMapping("/start-challenge/{roomNum}")
	public JsonResult startChallenge(
			@PathVariable int roomNum, 
			HttpServletRequest request) {
		System.out.println("ChallengeController.startChallenge()");

		// JWT를 통해 사용자 인증
		int userNum = JwtUtil.getNoFromHeader(request);
		if (userNum <= 0) {
			return  JsonResult.fail("인증되지 않은 사용자입니다.");
		}

		// 사용자 권한 확인 (예: 방장인지 확인)
		int auth = challengeService.getUserAuth(roomNum, userNum);
		if (auth < 1) { // 필요한 권한 수준에 따라 조정 (예: 1 이상은 방장)
			return  JsonResult.fail("권한이 없습니다.");
		}

		// 현재 시간을 roomStartDate로 설정하고 roomStatusNum을 3으로 업데이트
		boolean updateSuccess = challengeService.startChallenge(roomNum);
		if (updateSuccess) {
			return JsonResult.success("챌린지가 시작되었습니다.");
		} else {
			return  JsonResult.fail("챌린지 시작에 실패했습니다.");
		}
	}

	// **방 데이터 삭제 엔드포인트 (남은 시간이 6시간일 때 최소 인원 미달 시)**
	@DeleteMapping("/delete-room/{roomNum}")
	public JsonResult deleteRoom(
			@PathVariable int roomNum,
			HttpServletRequest request) {
		System.out.println("ChallengeController.deleteRoom()");

		// 방 삭제 서비스 호출
		boolean deleteSuccess = challengeService.deleteRoom(roomNum);
		if (deleteSuccess) {
			return  JsonResult.success("방이 성공적으로 삭제되었습니다.");
		} else {
			return  JsonResult.fail("방 삭제에 실패했습니다.");
		}
	}

	// **챌린지 종료 엔드포인트 (roomStatusNum을 3으로 변경)**
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

		// 사용자 권한 확인 (예: 방장인지 확인)
		int auth = challengeService.getUserAuth(roomNum, userNum);
		if (auth < 1) { // 필요한 권한 수준에 따라 조정 (예: 1 이상은 방장)
			return JsonResult.fail("권한이 없습니다.");
		}

		// roomStatusNum을 3으로 업데이트
		boolean updateSuccess = challengeService.endChallenge(roomNum);
		if (updateSuccess) {
			return JsonResult.success("챌린지가 종료되었습니다.");
		} else {
			return JsonResult.fail("챌린지 종료에 실패했습니다.");
		}
	}

	// **기간 완료 엔드포인트 (roomStatusNum을 4로 변경)**
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
		if (auth < 1) { // 필요한 권한 수준에 따라 조정 (예: 1 이상은 방장)
			return JsonResult.fail("권한이 없습니다.");
		}

		// roomStatusNum을 4로 업데이트
		boolean updateSuccess = challengeService.completePeriod(roomNum);
		if (updateSuccess) {
			return JsonResult.success("챌린지 기간이 완료되었습니다.");
		} else {
			return JsonResult.fail("챌린지 기간 완료에 실패했습니다.");
		}
	}

	// **참가 엔드포인트**
	@PostMapping("/join/{roomNum}")
	public JsonResult joinRoom(@PathVariable int roomNum, HttpServletRequest request) {
		System.out.println("ChallengeController.joinRoom()");

		// JWT를 통해 사용자 인증
		int userNum = JwtUtil.getNoFromHeader(request);
		if (userNum <= 0) {
			return JsonResult.fail("인증되지 않은 사용자입니다.");
		}

		// 이미 참가했는지 확인
		boolean alreadyJoined = challengeService.checkUserJoined(roomNum, userNum);
		if (alreadyJoined) {
			return JsonResult.fail("이미 참가한 챌린지입니다.");
		}

		// 참가 서비스 호출
		boolean joinSuccess = challengeService.joinRoom(roomNum, userNum);
		if (joinSuccess) {
			return JsonResult.success("참가가 완료되었습니다.");
		} else {
			return JsonResult.fail("참가에 실패했습니다.");
		}
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
			boolean resetStatus = challengeService.resetChallengeStatus(roomNum);
			if (resetStatus) {
				return JsonResult.success("시작 시간이 업데이트되었습니다.");
			} else {
				return JsonResult.fail("챌린지 상태 초기화에 실패했습니다.");
			}
		} else {
			return JsonResult.fail("시작 시간 업데이트에 실패했습니다.");
		}
	}

	// 방 나가기 엔드포인트 추가
	@PutMapping("/leave-room/{roomNum}")
	public JsonResult leaveRoom(
			@PathVariable int roomNum,
			HttpServletRequest request) {
		System.out.println("ChallengeController.leaveRoom()");

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



}