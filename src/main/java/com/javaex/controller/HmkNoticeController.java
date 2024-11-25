package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.HmkNoticeService;
import com.javaex.util.JsonResult;

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
}
