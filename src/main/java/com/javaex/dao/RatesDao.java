package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RatesVo;
import com.javaex.vo.UserProfileVo;

@Repository
public class RatesDao {
	@Autowired
    private SqlSessionTemplate sqlSession;

    private String namespace = "Rates"; // MyBatis 매퍼 네임스페이스 설정
    
 // 새로운 DAO 메서드 추가
    public List<RatesVo> getMissionAchievement(int roomNum) {
        return sqlSession.selectList(namespace + ".getMissionAchievement", roomNum);
    }

    public List<RatesVo> getTopUsers(int roomNum) {
        return sqlSession.selectList(namespace + ".getTopUsers", roomNum);
    }
    
    // 전체 달성률 통계 쿼리
    public List<RatesVo> getOverallStats(int roomNum) { // 반환 타입 수정
        return sqlSession.selectList(namespace + ".getOverallStatsbyDates", roomNum);
    }
    
    // 전체 유저 목록 가져오기 
    public List<RatesVo> getAllUsers(int roomNum) {
        return sqlSession.selectList(namespace + ".getAllUsers", roomNum);
    }
    public List<RatesVo> getRoomAchievement(int roomNum) {
    	return sqlSession.selectList(namespace +".getroomAchievementRate",roomNum);
    }
    
 // 전체 유저 목록 가져오기 
    public RatesVo getMyInfo(int roomNum,int userNum) {
    	RatesVo rates = new RatesVo();
    	rates.setRoomNum(roomNum);
    	rates.setUserNum(userNum);
        return sqlSession.selectOne(namespace + ".getMyInfo", rates);
    }
    
    // 미션 승인 횟수 가져오기 
    public List<Map<String, Object>> getMissionApprovals(int roomNum) {
        return sqlSession.selectList(namespace + ".getMissionApprovals", roomNum);
    }
    
 // 미션 승인 횟수 가져오기 
    public List<Map<String, Object>> getmyMissionApprovals(int roomNum, int userNum) {
    	RatesVo rates = new RatesVo();
    	rates.setRoomNum(roomNum);
    	rates.setUserNum(userNum);
        return sqlSession.selectList(namespace + ".getmyMissionApprovals" , rates);
    }
    
    // Get User Achievement Details
    public RatesVo getUserAchievementDetails(int roomNum, int userNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("userNum", userNum);
        return sqlSession.selectOne(namespace + ".getUserAchievementDetails", params);
    }

    
    
    // 사용자 기본 정보 조회
    public UserProfileVo getUserBasicInfo(int userNum) {
        return sqlSession.selectOne(namespace + ".getUserBasicInfo", userNum);
    }

    public Double getAverageAchievementRate(int userNum) {
        Double result = sqlSession.selectOne(namespace + ".getAverageAchievementRate", userNum);
        return result != null ? result : 0.0;
    }

    // 포인트 랭킹 조회
    public Integer getPointRanking(int userNum) {
        return sqlSession.selectOne(namespace + ".getPointRanking", userNum);
    }

    // 참여 중인 챌린지 수 조회
    public Integer getActiveChallenges(int userNum) {
        return sqlSession.selectOne(namespace + ".getActiveChallenges", userNum);
    }

    // 완료된 챌린지 수 조회
    public Integer getCompletedChallenges(int userNum) {
        return sqlSession.selectOne(namespace + ".getCompletedChallenges", userNum);
    }
    
 // RatesDao.java
    public List<Map<String, Object>> getUserMissionDetails(int roomNum, int userNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("userNum", userNum);
        return sqlSession.selectList(namespace + ".getUserMissionDetails", params);
    }

    public Map<String, Object> getUserTotalMissions(int roomNum, int userNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("userNum", userNum);
        return sqlSession.selectOne(namespace + ".getUserTotalMissions", params);
    }
    
 // RatesDao.java
    public List<Map<String, Object>> getGroupChallengeAchievement(int roomNum) {
        return sqlSession.selectList(namespace + ".getGroupChallengeAchievement", roomNum);
    }
 // RatesDao.java
    public Integer getRoomEnterPoint(int roomNum) {
        return sqlSession.selectOne(namespace + ".getRoomEnterPoint", roomNum);
    }

    public boolean isEligibleForChallengeReward(int roomNum, int userNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("userNum", userNum);
        Integer count = sqlSession.selectOne(namespace + ".getChallengeRewardEligibility", params);
        return count != null && count > 0;
    }
    
 // 포인트 기록 삽입 메서드
    public void insertPointHistory(int userNum, double historyPoint, int pointPurposeNum, String historyInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNum", userNum);
        params.put("historyPoint", historyPoint);
        params.put("pointPurposeNum", pointPurposeNum);
        params.put("historyInfo", historyInfo);
        
        sqlSession.insert(namespace + ".insertPointHistory", params);
    }

}
