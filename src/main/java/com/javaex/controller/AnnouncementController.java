package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.AnnouncementService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.AnnouncementVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/challenge/announcement")
public class AnnouncementController {
    
    @Autowired
    private AnnouncementService announcementService;

 // 방 참여 인원 체크
    @GetMapping("/{roomNum}")
    public JsonResult userChecker(@PathVariable int roomNum, HttpServletRequest request) {
        System.out.println("AnnouncementController.userChecker()");
        
        int userNum = JwtUtil.getNoFromHeader(request);
        
        List<Integer> enteredUserList = announcementService.getEnteredUserList(roomNum);
        
        if (enteredUserList.contains(userNum)) {
            return JsonResult.success(true);
        } else {
            return JsonResult.success(false);
        }
    }
    
    @GetMapping("/user/{roomNum}")
    public JsonResult checkUserAuthority(@PathVariable int roomNum, HttpServletRequest request) {
        System.out.println("AnnouncementController.checkUserAuthority()");
        
        int userNum = JwtUtil.getNoFromHeader(request);
        
        int userAuth = announcementService.getEnteredUserauth(roomNum, userNum);
        
        if (userAuth == 1 || userAuth == 2) {
            return JsonResult.success(userAuth);
        } else {
            return JsonResult.fail("오류 발생");
        }
        
    }
    
 // 공지사항 가져오기
    @GetMapping("/get/{roomNum}")
    public JsonResult getRoomAnnouncement(@PathVariable int roomNum) {
        System.out.println("AnnouncementController.getRoomAnnouncement()");
        List<AnnouncementVo> announcements = announcementService.getRoomAnnouncements(roomNum);
        return JsonResult.success(announcements);
    }
    
    // 공지사항 삭제
    @DeleteMapping("/delete/{noticeId}")
    public JsonResult deleteAnnouncement(@PathVariable int noticeId) {
        System.out.println("AnnouncementController.deleteAnnouncement()");
        announcementService.deleteAnnouncement(noticeId);
        return JsonResult.success("공지사항이 삭제되었습니다.");
    }
    
    @PutMapping("/edit/{editingNoticeId}")
    public JsonResult editAnnouncement(@PathVariable int editingNoticeId, @RequestBody AnnouncementVo announcementVo, HttpServletRequest request) {
        System.out.println("AnnouncementController.editAnnouncement()");
        int userNum = JwtUtil.getNoFromHeader(request);
        announcementVo.setAnnounceAddedBy(userNum);
        announcementVo.setAnnounceNum(editingNoticeId);
        announcementService.editAnnouncement(announcementVo);
        return JsonResult.success("공지사항이 수정되었습니다.");
    }

    
    // 공지사항 추가
    @PostMapping("/addannounce")
    public JsonResult addAnnouncement(@RequestBody AnnouncementVo announcementVo, HttpServletRequest request) {
        System.out.println("AnnouncementController.addAnnouncement()");
        
        // JWT 토큰에서 사용자 번호 추출
        int userNum = JwtUtil.getNoFromHeader(request);
        announcementVo.setAnnounceAddedBy(userNum);
        // roomNum은 URL 파라미터나 announcementVo에서 이미 설정되었는지 확인
        announcementService.addAnnouncement(announcementVo);
        return JsonResult.success(announcementVo);
    }


    
}

