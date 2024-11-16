package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RatesDao;
import com.javaex.vo.RatesVo;
import com.javaex.vo.UserProfileVo;

@Service
public class RatesService {
	@Autowired
    private RatesDao ratesdao;

    public List<RatesVo> getTopUsers(int roomNum) {
        return ratesdao.getTopUsers(roomNum);
    }
    
    // 전체 달성률 통계 가져오기
    public List<RatesVo> getOverallStats(int roomNum) { // 반환 타입 수정
        return ratesdao.getOverallStats(roomNum);
    }

    // 전체 유저 목록 가져오기 (추가)
    public List<RatesVo> getAllUsers(int roomNum) {
        return ratesdao.getAllUsers(roomNum);
    }
    
    // 미션 승인 횟수 가져오기 (새로운 메소드)
    public List<Map<String, Object>> getMissionApprovals(int roomNum) {
        return ratesdao.getMissionApprovals(roomNum);
    }
    
    // Get User Achievement Details
    public RatesVo getUserAchievementDetails(int roomNum, int userNum) {
        return ratesdao.getUserAchievementDetails(roomNum, userNum);
    }

    // Get User Mission Details
    public List<RatesVo> getUserMissionDetails(int roomNum, int userNum) {
        return ratesdao.getUserMissionDetails(roomNum, userNum);
    }
    
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
}
