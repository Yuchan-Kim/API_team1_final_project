// MypageService.java
package com.javaex.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.HmkMypageDao;
import com.javaex.vo.HmkChallengeVo;
import com.javaex.vo.HmkChartDataVo;
import com.javaex.vo.HmkGiftVo;
import com.javaex.vo.HmkPointHistoryVo;
import com.javaex.vo.HmkPointSummaryVo;
import com.javaex.vo.HmkUserVo;

@Service
public class HmkMypageService {
	private static final Logger logger = LoggerFactory.getLogger(HmkMypageService.class);

	@Autowired
	private HmkMypageDao mypageDao;

	// 사용자 정보 조회
	public HmkUserVo getUserInfo(int userNum) {
		HmkUserVo userInfo = mypageDao.getUserInfo(userNum);
		return userInfo;
	}

	// 사용자 챌린지 통계 조회(topbar통계용)
	public HmkUserVo getUserChallengeStats(int userNum) {
		return mypageDao.getUserChallengeStats(userNum);
	}

	// 진행중인 챌린지 리스트
	public List<HmkChallengeVo> getOngoingChallenges(int userNum) {
		return mypageDao.getOngoingChallenges(userNum);
	}

	// 진행 예정 챌린지 리스트
	public List<HmkChallengeVo> getUpcomingChallenges(int userNum) {
		return mypageDao.getUpcomingChallenges(userNum);
	}

	// 완료된 챌린지 리스트
	public List<HmkChallengeVo> getCompletedChallenges(int userNum) {
		return mypageDao.getCompletedChallenges(userNum);
	}

	// 차트 데이터 조회
	public List<HmkChartDataVo> getCharts(int userNum) {
		// 사용자 존재 여부 확인
		HmkUserVo user = mypageDao.getUserInfo(userNum);
		if (user == null) {
			logger.warn("존재하지 않는 사용자의 차트 데이터 조회 시도 - userNum: {}", userNum);
			return Collections.emptyList();
		}

		List<HmkChartDataVo> chartData = mypageDao.getCharts(userNum);

		// 데이터 검증
		if (chartData.isEmpty()) {
			logger.info("차트 데이터 없음 - userNum: {}", userNum);
		}

		return chartData;
	}

	// 프로필 이미지 업데이트
	public boolean updateProfileImage(HmkUserVo userVo) {
		// 유효성 검사: 프로필 이미지가 존재하는지 확인
		if (userVo.getProfileImage() == null || userVo.getProfileImage().isEmpty()) {
			return false;
		}

		// 프로필 이미지 업데이트
		int result = mypageDao.updateProfileImage(userVo);
		return result > 0;
	}

	// 닉네임 중복 체크
	public boolean isNicknameAvailable(String nickname) {
		return mypageDao.checkNickname(nickname) == 0; // 0이면 사용 가능, 1 이상이면 중복
	}

	// 닉네임 업데이트
	public boolean updateNickname(HmkUserVo userVo) {
		return mypageDao.updateNickname(userVo) > 0;
	}

	// 주소 자동완성과 주소 업데이트를 하나의 메서드로 처리
	public Object updateOrAutocompleteAddress(HmkUserVo userVo, String query) {
		if (query != null && !query.isEmpty()) {
			// 자동완성 로직
			System.out.println("자동완성 요청을 처리합니다: query = " + query);
			return mypageDao.findRegionsByName(query); // 자동완성 결과 반환
		} else {
			// 주소 업데이트 로직
			System.out.println("서비스가 받은 userVo: " + userVo);
			boolean success = mypageDao.updateAddress(userVo) > 0;
			return success;
		}
	}

	// 비밀번호 업데이트
	public boolean updatePassword(HmkUserVo userVo) {
		// 사용자 정보 조회
		HmkUserVo existingUser = mypageDao.getUserInfo(userVo.getUserNum());
		if (existingUser == null) {
			// 사용자가 존재하지 않음
			return false;
		}

		// 현재 비밀번호 검증
		if (!existingUser.getCurrentPassword().equals(userVo.getCurrentPassword())) {
			// 현재 비밀번호가 일치하지 않음
			return false;
		}

		// 비밀번호 규칙 검증 (선택 사항)
		if (!isValidPassword(userVo.getNewPassword())) {
			// 새 비밀번호가 규칙에 맞지 않음
			return false;
		}

		// 새 비밀번호로 업데이트
		return mypageDao.updatePassword(userVo) > 0;
	}

	// 비밀번호 규칙 검증 메서드 (선택 사항)
	private boolean isValidPassword(String password) {
		if (password == null)
			return false;
		if (password.length() < 10)
			return false;
		boolean hasLetter = password.matches(".*[A-Za-z].*");
		boolean hasNumberOrSpecial = password.matches(".*[0-9#?!&].*");
		return hasLetter && hasNumberOrSpecial;
	}

	// 회원 보관함 기프티콘 리스트 조회
	public List<HmkGiftVo> getUserGiftCards(int userNum) {
		return mypageDao.getUserGiftCards(userNum);
	}
	// 기프티콘 소유권 확인
	public boolean checkGiftcardOwnership(int purchaseNum, int userNum) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("purchaseNum", purchaseNum);
	    params.put("userNum", userNum);
	    return mypageDao.checkGiftcardOwnership(params);
	}
	// 기프티콘 사용 하기
	public boolean useGiftcard(int purchaseNum) {
        try {
            // 기프티콘 상태 업데이트
            int result = mypageDao.updateGiftcardStatus(purchaseNum);
            return result > 0;
        } catch (Exception e) {
            logger.error("기프티콘 사용 처리 중 오류 발생 - purchaseNum: {}", purchaseNum, e);
            return false;
        }
    }

	// ** 포인트 요약 정보 조회**
    public HmkPointSummaryVo getPointSummary(int userNum) {
        return mypageDao.getPointSummary(userNum);
    }

    // ** 포인트 상세 내역 조회**
    public List<HmkPointHistoryVo> getPointHistory(int userNum, String startDate, String endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNum", userNum);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return mypageDao.getPointHistory(userNum, params);
    }
}