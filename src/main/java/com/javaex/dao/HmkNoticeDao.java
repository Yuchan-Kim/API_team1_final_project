package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.HmkNoticeVo;

@Repository
public class HmkNoticeDao {
	
	@Autowired
    private SqlSession sqlSession;

    // 방 생성 알림 추가
    public int insertRoomCreationNotice(Map<String, Object> params) {
        return sqlSession.insert("Notice.insertRoomCreationNotice", params);
    }

    // 방 참가 알림 추가 (방장에게)
    public int insertRoomEnterNoticeToMaker(Map<String, Object> params) {
        return sqlSession.insert("Notice.insertRoomEnterNoticeToMaker", params);
    }

    // 방 참가 알림 추가 (참가자에게)
    public int insertRoomEnterNoticeToUser(Map<String, Object> params) {
        return sqlSession.insert("Notice.insertRoomEnterNoticeToUser", params);
    }

    // 방 시작 알림 추가
    public int insertRoomStartNotice(Map<String, Object> params) {
        return sqlSession.insert("Notice.insertRoomStartNotice", params);
    }

    // 방 종료 알림 추가
    public int insertRoomEndNotice(Map<String, Object> params) {
        return sqlSession.insert("Notice.insertRoomEndNotice", params);
    }

    // 알림 읽음 상태 업데이트
    public int updateNoticeReadStatus(int noticeNum) {
        return sqlSession.update("Notice.updateNoticeReadStatus", noticeNum);
    }
    
    // 알림 목록 조회 메서드 추가
    public List<HmkNoticeVo> getNotices(Map<String, Object> params) {
        return sqlSession.selectList("Notice.getNotices", params);
    }

    // 알림 요약 정보 조회
    public Map<String, Integer> getNoticeSummary(int userNum) {
        return sqlSession.selectOne("Notice.getNoticeSummary", userNum);
    }
}

