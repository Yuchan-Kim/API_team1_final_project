// MypageController.java
package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.HmkMypageService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.HmkChallengeVo;
import com.javaex.vo.HmkChartDataVo;
import com.javaex.vo.HmkGiftVo;
import com.javaex.vo.HmkNoticeSummaryVo;
import com.javaex.vo.HmkNoticeVo;
import com.javaex.vo.HmkPointSummaryVo;
import com.javaex.vo.HmkUserVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/my")
public class HmkMypageController {
	private static final Logger logger = LoggerFactory.getLogger(HmkMypageService.class);

	@Autowired
	private HmkMypageService mypageService;

	// 사용자 정보 조회
	@GetMapping("/{userNum}")
	public JsonResult getUserInfo(@PathVariable int userNum) {
		HmkUserVo userInfo = mypageService.getUserInfo(userNum);
		HmkUserVo statsVo = mypageService.getUserChallengeStats(userNum);
		// 챌린지 상세 정보 가져오기
		List<HmkChallengeVo> createdChallenges = mypageService.getMyCreatedRooms(userNum); // 추가
		List<HmkChallengeVo> ongoingChallenges = mypageService.getOngoingChallenges(userNum);
		List<HmkChallengeVo> upcomingChallenges = mypageService.getUpcomingChallenges(userNum);
		List<HmkChallengeVo> completedChallenges = mypageService.getCompletedChallenges(userNum);
		
		System.out.println("방장인 방의 상태값: " + createdChallenges);
		
		// 응답 데이터 구조화
		Map<String, Object> responseData = new HashMap<>();
		// 사용자 기본 정보
		Map<String, Object> userInfoMap = new HashMap<>();
		userInfoMap.put("nickname", userInfo.getNickname());
		userInfoMap.put("region", userInfo.getRegion());
		userInfoMap.put("profileImage", userInfo.getProfileImage());
		// ownedProfileImages가 null인 경우를 체크하여 기본값을 설정하는 코드
		userInfoMap.put("ownedProfileImages",
				userInfo.getOwnedProfileImages() != null ? userInfo.getOwnedProfileImages() : "[]");
		// statsVo가 null이 아닐 때만 데이터 추가
		if (statsVo != null) {
			// 통계 정보 추가(count)
			userInfoMap.put("ongoingChallenges", statsVo.getOngoingChallenges());
			userInfoMap.put("upcomingChallenges", statsVo.getUpcomingChallenges());
			userInfoMap.put("completedChallenges", statsVo.getCompletedChallenges());
			userInfoMap.put("participationScore", statsVo.getParticipationScore());
		} else {
			// 기본값 설정
			userInfoMap.put("ongoingChallenges", 0);
			userInfoMap.put("upcomingChallenges", 0);
			userInfoMap.put("completedChallenges", 0);
			userInfoMap.put("participationScore", 0);
		}
		// 챌린지 상세 데이터 추가
		Map<String, Object> challengesMap = new HashMap<>();
		challengesMap.put("created", createdChallenges);
		challengesMap.put("ongoing", ongoingChallenges);
		challengesMap.put("upcoming", upcomingChallenges);
		challengesMap.put("completed", completedChallenges);
		responseData.put("userInfo", userInfoMap);
		responseData.put("challenges", challengesMap);

		return JsonResult.success(responseData);
	}
	
	// 오늘의 미션이 있는 방 목록 조회
	@GetMapping("/{userNum}/todayMissions")
	public JsonResult getTodayMissionRooms(@PathVariable int userNum, HttpServletRequest request) {
	    // JWT 토큰에서 사용자 번호 추출 (보안 강화)
	    Integer jwtUserNum = JwtUtil.getNoFromHeader(request);
	    if (jwtUserNum == null || jwtUserNum != userNum) {
	        return JsonResult.fail("권한이 없는 사용자입니다.");
	    }

	    try {
	        List<HmkChallengeVo> todayMissionRooms = mypageService.getTodayMissionRooms(userNum);
	        System.out.println("오늘 미션 있는 방: " + todayMissionRooms);
	        return JsonResult.success(todayMissionRooms);
	        
	    } catch (Exception e) {
	        logger.error("오늘의 미션 조회 중 오류 발생 - userNum: {}", userNum, e);
	        return JsonResult.fail("오늘의 미션 조회 중 오류가 발생했습니다.");
	    }
	    
	}

	// 차트 데이터 조회
	@GetMapping("/{userNum}/charts")
	public JsonResult getCharts(@PathVariable int userNum) {
		List<HmkChartDataVo> chartData = mypageService.getCharts(userNum);
		Map<String, Object> responseData = new HashMap<>();
		Map<String, Object> performanceData = new HashMap<>();
		Map<String, Object> achievementData = new HashMap<>();
		// 데이터 분류
		for (HmkChartDataVo data : chartData) {
			if (data.getChartTitle().contains("수행률")) {
				performanceData.put(data.getChartTitle(), data);
			} else if (data.getChartTitle().contains("달성률")) {
				achievementData.put(data.getChartTitle(), data);
			}
		}
		responseData.put("performance", performanceData);
		responseData.put("achievement", achievementData);
		return JsonResult.success(responseData);
	}

	/* 포인트 요약 정보 */
	@GetMapping("/{userNum}/pointSummary")
	public JsonResult getPointSummary(@PathVariable int userNum, HttpServletRequest request) {
		// JWT 토큰에서 사용자 번호 추출 (보안 강화)
		Integer jwtUserNum = JwtUtil.getNoFromHeader(request);
		if (jwtUserNum == null || jwtUserNum != userNum) {
			return JsonResult.fail("권한이 없는 사용자입니다.");
		}

		HmkPointSummaryVo summary = mypageService.getPointSummary(userNum);
		System.out.println("이 회원의 포인트 요약: " + summary);
		return JsonResult.success(summary);
	}

	/* 포인트 상세 내역 리스트 */
	@GetMapping("/{userNum}/pointHistory")
	public JsonResult getPointHistory(@PathVariable int userNum, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request) {

		Integer jwtUserNum = JwtUtil.getNoFromHeader(request);
		if (jwtUserNum == null || jwtUserNum != userNum) {
			return JsonResult.fail("권한이 없는 사용자입니다.");
		}

		Map<String, Object> params = new HashMap<>();
		params.put("userNum", userNum);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("page", page * size); // OFFSET 계산
		params.put("size", size);

		Map<String, Object> response = mypageService.getPointHistory(userNum, params);
		return JsonResult.success(response);
	}

	// 프로필 이미지 업데이트
	@PutMapping("/{userNum}/update-profile")
	public ResponseEntity<JsonResult> updateProfileImage(@PathVariable("userNum") Integer userNum,
			@RequestBody HmkUserVo userVo) {

		// 사용자 번호 유효성 검사
		if (userNum == null || userNum <= 0) {
			return ResponseEntity.badRequest().body(JsonResult.fail("유효하지 않은 사용자 번호입니다."));
		}

		userVo.setUserNum(userNum);

		try {
			boolean success = mypageService.updateProfileImage(userVo);
			if (success) {
				// 업데이트된 사용자 정보를 다시 조회하여 반환 (보안 고려)
				HmkUserVo updatedUser = mypageService.getUserInfo(userNum);
				System.out.println("업데이트된거가 머?: " + updatedUser);
				return ResponseEntity.ok(JsonResult.success(updatedUser));
			} else {
				return ResponseEntity.status(500).body(JsonResult.fail("프로필 이미지 업데이트에 실패했습니다."));
			}
		} catch (Exception e) {
			// 로깅 및 구체적인 에러 메시지 반환
			e.printStackTrace(); // 실제 서비스에서는 로깅 프레임워크 사용 권장
			return ResponseEntity.status(500).body(JsonResult.fail("서버 오류로 인해 프로필 이미지 업데이트에 실패했습니다."));
		}
	}

	// 닉네임 변경
	@PutMapping("/{userNum}/updateNickname")
	public JsonResult updateNickname(@PathVariable int userNum, @RequestBody HmkUserVo userVo) {
		userVo.setUserNum(userNum);
		boolean success = mypageService.updateNickname(userVo);
		return success ? JsonResult.success(userVo) : JsonResult.fail("닉네임 업데이트 실패");
	}

	// 닉네임 중복 확인 API
	@GetMapping("/checkNickname")
	public JsonResult checkNickname(@RequestParam String nickname) {
		boolean isAvailable = mypageService.isNicknameAvailable(nickname);
		return JsonResult.success(isAvailable);
	}

	// 주소 자동완성 및 주소 업데이트를 통합한 메서드
	@GetMapping("/updateAddress")
	public JsonResult getRegionSuggestions(@RequestParam String query) {
		List<String> regionNames = (List<String>) mypageService.updateOrAutocompleteAddress(null, query);
		return JsonResult.success(regionNames);
	}

	@PutMapping("/{userNum}/updateAddress")
	public JsonResult updateAddress(@PathVariable int userNum, @RequestBody HmkUserVo userVo) {
		userVo.setUserNum(userNum);
		boolean success = (boolean) mypageService.updateOrAutocompleteAddress(userVo, null);
		return success ? JsonResult.success("주소가 업데이트되었습니다.") : JsonResult.fail("지역 업데이트 실패");
	}

	// 비밀번호 존재 확인(socialLogin 확인용)
	@GetMapping("/{userNum}/checkPassword")
	public JsonResult checkPasswordExists(@PathVariable int userNum) {
		try {
			String userPw = mypageService.getUserPassword(userNum);
			// data 객체 안에 passwordExists 값을 넣어서 반환
			Map<String, Boolean> data = new HashMap<>();
			data.put("passwordExists", userPw != null && !userPw.isEmpty());
			return JsonResult.success(data);
		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}
	}

	// 비밀번호 업데이트
	@PutMapping("/{userNum}/updatePassword")
	public JsonResult updatePassword(@PathVariable int userNum, @RequestBody HmkUserVo userVo) {
		userVo.setUserNum(userNum);
		boolean hasPassword = mypageService.getUserPassword(userNum) != null;

		if (!hasPassword) {
			boolean success = mypageService.updatePassword(userVo);
			return success ? JsonResult.success("비밀번호가 성공적으로 설정되었습니다.") : JsonResult.fail("비밀번호 설정에 실패했습니다.");
		} else {
			boolean success = mypageService.updatePassword(userVo);
			return success ? JsonResult.success("비밀번호가 성공적으로 변경되었습니다.") : JsonResult.fail("비밀번호 변경에 실패했습니다.");
		}
	}

	// 회원 보관함 기프티콘 리스트 조회
	@GetMapping("/{userNum}/giftcards")
	public JsonResult getUserGiftCards(@PathVariable int userNum) {
		List<HmkGiftVo> giftCards = mypageService.getUserGiftCards(userNum);
		return JsonResult.success(giftCards);
	}

	// 기프티콘 사용하기 버튼 클릭하면 발생하는 이벤트
	@PutMapping("/giftcards/use/{purchaseNum}")
	public JsonResult useGiftcard(@PathVariable int purchaseNum, HttpServletRequest request) {

		// JWT 토큰에서 사용자 번호 추출
		Integer userNum = JwtUtil.getNoFromHeader(request);
		if (userNum == null) {
			return JsonResult.fail("인증되지 않은 사용자입니다.");
		}

		try {
			// 기프티콘 소유권 확인
			boolean isOwner = mypageService.checkGiftcardOwnership(purchaseNum, userNum);
			if (!isOwner) {
				return JsonResult.fail("해당 기프티콘에 대한 권한이 없습니다.");
			}

			// 기프티콘 사용 처리
			boolean success = mypageService.useGiftcard(purchaseNum);

			if (success) {
				return JsonResult.success("기프티콘이 성공적으로 사용 처리되었습니다.");
			} else {
				return JsonResult.fail("기프티콘 사용 처리에 실패했습니다.");
			}

		} catch (Exception e) {
			logger.error("기프티콘 사용 처리 중 오류 발생", e);
			return JsonResult.fail("서버 오류로 인해 기프티콘 사용 처리에 실패했습니다.");
		}
	}

	// 알림 요약 정보 조회 엔드포인트
	@GetMapping("/{userNum}/noticeSummary")
	public JsonResult getNoticeSummary(@PathVariable int userNum, HttpServletRequest request) {
		// JWT 토큰에서 사용자 번호 추출 (보안 강화)
		Integer jwtUserNum = JwtUtil.getNoFromHeader(request);
		if (jwtUserNum == null || jwtUserNum != userNum) {
			return JsonResult.fail("권한이 없는 사용자입니다.");
		}

		HmkNoticeSummaryVo summary = mypageService.getNoticeSummary(userNum);
		System.out.println("알림 요약: " + summary);
		return JsonResult.success(summary);
	}

	// 알림 리스트 조회 엔드포인트
	@GetMapping("/{userNum}/notices")
	public JsonResult getNotices(@PathVariable int userNum, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, HttpServletRequest request) {
		// JWT 토큰에서 사용자 번호 추출 (보안 강화)
		Integer jwtUserNum = JwtUtil.getNoFromHeader(request);
		if (jwtUserNum == null || jwtUserNum != userNum) {
			return JsonResult.fail("권한이 없는 사용자입니다.");
		}

		List<HmkNoticeVo> notices = mypageService.getNotices(userNum, startDate, endDate);
		System.out.println("알림들이 머가 있니?: " + notices);
		return JsonResult.success(notices);
	}
}