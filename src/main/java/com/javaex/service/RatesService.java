package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RatesDao;
import com.javaex.vo.ChallengeVo;
import com.javaex.vo.RatesVo;
import com.javaex.vo.UserProfileVo;

@Service
public class RatesService {
	@Autowired
    private RatesDao ratesdao;
	
	// 새로운 서비스 메서드 추가
    public List<RatesVo> getMissionAchievement(int roomNum) {
        return ratesdao.getMissionAchievement(roomNum);
    }

    public List<RatesVo> getTopUsers(int roomNum) {
        return ratesdao.getTopUsers(roomNum);
    }
    
    // 전체 달성률 통계 가져오기
    public List<ChallengeVo> getOverallStats(int roomNum) { 
        return ratesdao.getOverallStats(roomNum);
    }

    // 전체 유저 목록 가져오기 
    public List<RatesVo> getAllUsers(int roomNum) {
        return ratesdao.getAllUsers(roomNum);
    }
    // 유저 정보 가져오기 
    public RatesVo getMyInfo(int roomNum, int userNum) {
        return ratesdao.getMyInfo(roomNum,userNum);
    }
    // 미션 승인 횟수 가져오기 
    public List<Map<String, Object>> getMissionApprovals(int roomNum) {
        return ratesdao.getMissionApprovals(roomNum);
    }
    
    // 미션 승인 횟수 가져오기 
    public List<Map<String, Object>> getmyMissionApprovals(int roomNum, int userNum) {
        return ratesdao.getmyMissionApprovals(roomNum,userNum);
    }
    
   public List<RatesVo> getRoomAchievement(int roomNum) {
	   return ratesdao.getRoomAchievement(roomNum);
   }
    
    // Get User Achievement Details
    public RatesVo getUserAchievementDetails(int roomNum, int userNum) {
        return ratesdao.getUserAchievementDetails(roomNum, userNum);
    }
    
    
    // 프로필 모달 전용 
    public UserProfileVo getUserProfileDetails(int userNum) {
        UserProfileVo profile = ratesdao.getUserBasicInfo(userNum);
        if (profile != null) {
            profile.setAverageAchievementRate(ratesdao.getAverageAchievementRate(userNum));
            profile.setPointRanking(ratesdao.getPointRanking(userNum));
            profile.setActiveChallenges(ratesdao.getActiveChallenges(userNum));
            profile.setCompletedChallenges(ratesdao.getCompletedChallenges(userNum));
        }
        return profile;
    }
 // RatesService.java
    public List<Map<String, Object>> getUserMissionDetails(int roomNum, int userNum) {
        return ratesdao.getUserMissionDetails(roomNum, userNum);
    }

    public Map<String, Object> getUserTotalMissions(int roomNum, int userNum) {
        return ratesdao.getUserTotalMissions(roomNum, userNum);
    }
 // RatesService.java
    public List<Map<String, Object>> getGroupChallengeAchievement(int roomNum) {
        return ratesdao.getGroupChallengeAchievement(roomNum);
    }
 // RatesService.java
    public Integer getRoomEnterPoint(int roomNum) {
        return ratesdao.getRoomEnterPoint(roomNum);
    }

    public boolean isEligibleForChallengeReward(int roomNum, int userNum) {
        return ratesdao.isEligibleForChallengeReward(roomNum, userNum);
    }
    
 // 포인트 기록 삽입 메서드
    public void insertPointHistory(int userNum, double historyPoint, int pointPurposeNum, String historyInfo) {
        ratesdao.insertPointHistory(userNum, historyPoint, pointPurposeNum, historyInfo);
    }

}
