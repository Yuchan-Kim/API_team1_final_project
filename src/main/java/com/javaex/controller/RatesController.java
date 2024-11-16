package com.javaex.controller;

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
    
    // 전체 유저 목록 가져오기 (추가)
    @GetMapping("/users/{roomNum}")
    public JsonResult getAllUsers(@PathVariable int roomNum) {
        try {
            List<RatesVo> users = ratesservice.getAllUsers(roomNum);
            return JsonResult.success(users);
        } catch (Exception e) {
            return JsonResult.fail("유저 목록을 불러오는 데 실패했습니다.");
        }
    }
    
    // 미션 승인 횟수 가져오기 (새로운 엔드포인트)
    @GetMapping("/approvals/{roomNum}")
    public JsonResult getMissionApprovals(@PathVariable int roomNum) {
        try {
            List<Map<String, Object>> approvals = ratesservice.getMissionApprovals(roomNum);
            return JsonResult.success(approvals);
        } catch (Exception e) {
            return JsonResult.fail("미션 승인 횟수를 불러오는 데 실패했습니다.");
        }
    }
    
    
 // 사용자 프로필 정보 가져오기
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
   
}
