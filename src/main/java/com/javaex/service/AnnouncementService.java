package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.AnnouncementDao;
import com.javaex.vo.AnnouncementVo;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    /* 모든 공지사항 조회 */
    public List<Integer> getEnteredUserList(int roomNum) {
        System.out.println("AnnouncementService.getEnteredUserList()");
        return announcementDao.getEnteredUserList(roomNum);
    }
    
    public int getEnteredUserauth(int roomNum, int userNum) {
        System.out.println("AnnouncementService.getEnteredUserauth()");
        return announcementDao.getEnteredUserAuth(roomNum, userNum);
    }
    
    // 방의 모든 공지사항 가져오기
    public List<AnnouncementVo> getRoomAnnouncements(int roomNum) {
        System.out.println("AnnouncementService.getRoomAnnouncements()");
        return announcementDao.getRoomAnnouncements(roomNum);
    }

    // 공지사항 삭제
    public void deleteAnnouncement(int noticeId) {
        System.out.println("AnnouncementService.deleteAnnouncement()");
        announcementDao.deleteAnnouncement(noticeId);
    }

    public void editAnnouncement(AnnouncementVo announcementVo) {
        System.out.println("AnnouncementService.editAnnouncement()");
        announcementDao.editAnnouncement(announcementVo);
    }



    public void addAnnouncement(AnnouncementVo announcementVo) {
        System.out.println("AnnouncementService.addAnnouncement()");
        announcementDao.addAnnouncement(announcementVo);
    }

    
   
}
