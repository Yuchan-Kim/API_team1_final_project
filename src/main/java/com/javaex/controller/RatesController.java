package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.RatesService;
import com.javaex.util.JsonResult;
import com.javaex.vo.RatesVo;
import com.javaex.vo.UserProfileVo;

@RestController
@RequestMapping("api/rates")
public class RatesController {
	
	@Autowired
    private RatesService ratesservice;

	// 새로운 엔드포인트 추가: 특정 roomNum의 미션 달성률 조회
    @GetMapping("/achievement/{roomNum}")
    public JsonResult getMissionAchievement(@PathVariable int roomNum) {
        try {
            List<RatesVo> missionAchievements = ratesservice.getMissionAchievement(roomNum);
            return JsonResult.success(missionAchievements);
        } catch (Exception e) {
            return JsonResult.fail("미션 달성률을 불러오는 데 실패했습니다.");
        }
    }
	
	//Top 5 달성률을 가진 유저 정보 가져오기
    @GetMapping("/topusers/{roomNum}")
    public JsonResult getTopUsers(@PathVariable int roomNum) {
    	try {
        	List<RatesVo> rates = ratesservice.getTopUsers(roomNum);
            return JsonResult.success(rates);
        } catch (Exception e) {
            return JsonResult.fail("Top 5 User를 불러오는 데 실패했습니다.");
        }
    }
    
    // 전체 달성률 통계 가져오기
    @GetMapping("/overall/{roomNum}")
    public JsonResult getOverallStats(@PathVariable int roomNum) {
        try {
            List<RatesVo> stats = ratesservice.getOverallStats(roomNum);
            return JsonResult.success(stats);
        } catch (Exception e) {
            return JsonResult.fail("전체 달성률 통계를 불러오는 데 실패했습니다.");
        }
    }
    
    // 전체 유저 목록 가져오기 (달성률 순)
    @GetMapping("/users/{roomNum}")
    public JsonResult getAllUsers(@PathVariable int roomNum) {
        try {
            List<RatesVo> users = ratesservice.getAllUsers(roomNum);
            return JsonResult.success(users);
        } catch (Exception e) {
            return JsonResult.fail("유저 목록을 불러오는 데 실패했습니다.");
        }
    }
    
    // 전체 유저의 미션 승인 횟수 가져오기 
    @GetMapping("/approvals/{roomNum}")
    public JsonResult getMissionApprovals(@PathVariable int roomNum) {
        try {
            List<Map<String, Object>> approvals = ratesservice.getMissionApprovals(roomNum);
            return JsonResult.success(approvals);
        } catch (Exception e) {
            return JsonResult.fail("미션 승인 횟수를 불러오는 데 실패했습니다.");
        }
    }
    
    
    // 사용자 프로필 정보 가져오기 (프로필 모달 전용)
    @GetMapping("/profile/{userNum}")
    public JsonResult getUserProfile(@PathVariable int userNum) {
        try {
            UserProfileVo profileDetails = ratesservice.getUserProfileDetails(userNum);
            return JsonResult.success(profileDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("사용자 프로필 정보를 불러오는 데 실패했습니다.");
        }
    }
    
    @GetMapping("/userDetails/{roomNum}/{userNum}")
    public JsonResult getUserDetails(@PathVariable int roomNum, @PathVariable int userNum) {
        try {
            Map<String, Object> data = new HashMap<>();
            // 미션 상세 정보 가져오기
            List<Map<String, Object>> missionDetails = ratesservice.getUserMissionDetails(roomNum, userNum);
            data.put("missionDetails", missionDetails);

            // 전체 미션 수 가져오기
            Map<String, Object> totalMissions = ratesservice.getUserTotalMissions(roomNum, userNum);
            data.put("totalMissions", totalMissions);

            // 그룹 챌린지 정보 가져오기
            List<Map<String, Object>> groupChallenges = ratesservice.getGroupChallengeAchievement(roomNum);
            data.put("groupChallenges", groupChallenges);

            // roomEnterPoint 가져오기
            Integer roomEnterPoint = ratesservice.getRoomEnterPoint(roomNum);
            data.put("roomEnterPoint", roomEnterPoint != null ? roomEnterPoint : 0);

            // 도전 보상 자격 확인
            boolean challengeRewardEligible = ratesservice.isEligibleForChallengeReward(roomNum, userNum);
            data.put("challengeRewardEligible", challengeRewardEligible);

            // 사용자 달성률 가져오기
            RatesVo userAchievement = ratesservice.getUserAchievementDetails(roomNum, userNum);
            data.put("userAchievementRate", userAchievement != null ? userAchievement.getAchievementRate() : 0);

            return JsonResult.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("사용자 정보를 불러오는 데 실패했습니다.");
        }
    }


   
}