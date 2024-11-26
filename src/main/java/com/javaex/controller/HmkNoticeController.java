package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.HmkNoticeService;
import com.javaex.util.JsonResult;
import com.javaex.vo.HmkNoticeVo;

@RestController
@RequestMapping("/api/notice")
public class HmkNoticeController {
	
	@Autowired
	private HmkNoticeService noticeService;
	// 방 생성 알림 생성
    @PostMapping("/room/create")
    public JsonResult createRoomNotice(@RequestBody Map<String, Object> params) {
        try {
            boolean result = noticeService.createRoomNotice(
                (Integer) params.get("roomMaker"),
                (String) params.get("roomTitle")
            );
            return JsonResult.success(result);
        } catch (Exception e) {
            return JsonResult.fail("방 생성 알림 생성 실패: " + e.getMessage());
        }
    }

    // 방 참가 알림 생성
    @PostMapping("/room/join")
    public JsonResult createJoinNotice(@RequestBody Map<String, Object> params) {
        try {
            boolean result = noticeService.createJoinNotice(
                (Integer) params.get("roomMaker"),
                (Integer) params.get("newUserNum"),
                (String) params.get("roomTitle"),
                (String) params.get("userName")
            );
            return JsonResult.success(result);
        } catch (Exception e) {
            return JsonResult.fail("방 참가 알림 생성 실패: " + e.getMessage());
        }
    }

    // 방 상태 변경 알림 생성 (시작/종료)
    @PostMapping("/room/status")
    public JsonResult createStatusChangeNotice(@RequestBody Map<String, Object> params) {
        try {
            boolean result = noticeService.createStatusChangeNotice(
                (Integer) params.get("roomNum"),
                (String) params.get("roomTitle"),
                (Integer) params.get("roomMaker"),
                (Integer) params.get("statusNum")
            );
            return JsonResult.success(result);
        } catch (Exception e) {
            return JsonResult.fail("방 상태 변경 알림 생성 실패: " + e.getMessage());
        }
    }

    // 알림 읽음 처리
    @PutMapping("/{noticeNum}/read")
    public JsonResult markNoticeAsRead(@PathVariable int noticeNum) {
        try {
            boolean result = noticeService.markNoticeAsRead(noticeNum);
            return JsonResult.success(result);
        } catch (Exception e) {
            return JsonResult.fail("알림 읽음 처리 실패: " + e.getMessage());
        }
    }
    
 // 알림 목록 조회 추가
    @GetMapping("/user/{userNum}")
    public JsonResult getNotices(
            @PathVariable int userNum,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<HmkNoticeVo> notices = noticeService.getNotices(userNum, startDate, endDate);
            return JsonResult.success(notices);
        } catch (Exception e) {
            return JsonResult.fail("알림 목록 조회 실패: " + e.getMessage());
        }
    }

    // 알림 요약 정보 조회 추가
    @GetMapping("/user/{userNum}/summary")
    public JsonResult getNoticeSummary(@PathVariable int userNum) {
        try {
            Map<String, Integer> summary = noticeService.getNoticeSummary(userNum);
            return JsonResult.success(summary);
        } catch (Exception e) {
            return JsonResult.fail("알림 요약 정보 조회 실패: " + e.getMessage());
        }
    }
}
