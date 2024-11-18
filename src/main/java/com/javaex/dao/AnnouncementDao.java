package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AnnouncementVo;

@Repository
public class AnnouncementDao {
    
    @Autowired
    private SqlSession sqlSession;
    
    private String namespace = "announcement"; // MyBatis 매퍼 네임스페이스 설정
    
    /* 모든 공지사항 조회 (특정 방 번호로 필터링 가능) */
    public List<Integer> getEnteredUserList(int roomNum) {
        System.out.println("AnnouncementDAO.getEnteredUserList()");
        return sqlSession.selectList(namespace + ".selectEnteredUserList", roomNum);
    }
    
    public int getEnteredUserAuth(int roomNum, int userNum) {
        System.out.println("AnnouncementDAO.getEnteredUserAuth()");
        AnnouncementVo announcementvo = new AnnouncementVo();
        announcementvo.setRoomNum(roomNum);
        announcementvo.setUserNum(userNum);
        return sqlSession.selectOne(namespace + ".selectEnteredUserAuth", announcementvo); // 오타 수정
    }

    
    // 방의 모든 공지사항 가져오기
    public List<AnnouncementVo> getRoomAnnouncements(int roomNum) {
        System.out.println("AnnouncementDAO.getRoomAnnouncements()");
        return sqlSession.selectList(namespace + ".getRoomAnnouncements", roomNum);
    }
    
    // 공지사항 삭제
    public void deleteAnnouncement(int noticeId) {
        System.out.println("AnnouncementDAO.deleteAnnouncement()");
        sqlSession.delete(namespace + ".deleteAnnouncement", noticeId);
    }
    
    public void editAnnouncement(AnnouncementVo announcementVo) {
        System.out.println("AnnouncementDAO.editAnnouncement()");
        sqlSession.update(namespace + ".editAnnouncement", announcementVo);
    }

    
    // 공지사항 추가
    public void addAnnouncement(AnnouncementVo announcementVo) {
        System.out.println("AnnouncementDAO.addAnnouncement()");
        sqlSession.insert(namespace + ".addAnnouncement", announcementVo);
    }
}
