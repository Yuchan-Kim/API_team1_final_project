package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.HmkRankService;
import com.javaex.util.JsonResult;

@RestController
@RequestMapping("/api/rank")
public class HmkRankController {
    
    @Autowired
    private HmkRankService rankService;

    @GetMapping("/top10")
    public JsonResult getTop10Ranks() {
        System.out.println("[Controller] getTop10Ranks 호출됨");
        JsonResult result = JsonResult.success(rankService.getTop10Ranks());
        System.out.println("[Controller] getTop10Ranks 결과: " + result);
        return result;
    }

    @GetMapping("/user/{userNum}")
    public JsonResult getUserRank(@PathVariable int userNum) {
        System.out.println("[Controller] getUserRank 호출됨 - userNum: " + userNum);
        JsonResult result = JsonResult.success(rankService.getUserRank(userNum));
        System.out.println("[Controller] getUserRank 결과: " + result);
        return result;
    }
}