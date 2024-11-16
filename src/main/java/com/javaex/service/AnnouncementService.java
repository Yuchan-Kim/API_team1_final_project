package com.javaex.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.AnnouncementDAO;
import com.javaex.vo.AnnouncementVo;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementDAO announcementDAO;

    /* 모든 공지사항 조회 */
    public List<AnnouncementVo> getEnteredUserList(int roomNum) {
        System.out.println("AnnouncementService.getEnteredUserList()");
        return announcementDAO.getEnteredUserList(roomNum);
    }
    
    public int getEnteredUserauth(int roomNum, int userNum) {
        System.out.println("AnnouncementService.getEnteredUserauth()");
        return announcementDAO.getEnteredUserAuth(roomNum, userNum);
    }
    
    // 방의 모든 공지사항 가져오기
    public List<AnnouncementVo> getRoomAnnouncements(int roomNum) {
        System.out.println("AnnouncementService.getRoomAnnouncements()");
        return announcementDAO.getRoomAnnouncements(roomNum);
    }

    // 공지사항 삭제
    public void deleteAnnouncement(int noticeId) {
        System.out.println("AnnouncementService.deleteAnnouncement()");
        announcementDAO.deleteAnnouncement(noticeId);
    }

    // 공지사항 수정
    public void editAnnouncement(int noticeId, AnnouncementVo announcementVo) {
        System.out.println("AnnouncementService.editAnnouncement()");
        announcementDAO.editAnnouncement(noticeId, announcementVo);
    }

    // 공지사항 추가
    public void addAnnouncement(AnnouncementVo announcementVo) {
        System.out.println("AnnouncementService.addAnnouncement()");
        announcementDAO.addAnnouncement(announcementVo);
    }
   
}
