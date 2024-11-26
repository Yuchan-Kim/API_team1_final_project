package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.HmkNoticeDao;
import com.javaex.vo.HmkNoticeVo;

@Service
public class HmkNoticeService {
	
	@Autowired
	private HmkNoticeDao noticeDao;
	
	// 방 생성 알림 생성
    public boolean createRoomNotice(int roomMaker, String roomTitle) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomMaker", roomMaker);
        params.put("roomTitle", roomTitle);
        
        return noticeDao.insertRoomCreationNotice(params) > 0;
    }

    // 방 참가 알림 생성
    public boolean createJoinNotice(int roomMaker, int newUserNum, String roomTitle, String userName) {
        // 방장에게 알림
        Map<String, Object> makerParams = new HashMap<>();
        makerParams.put("roomMaker", roomMaker);
        makerParams.put("roomTitle", roomTitle);
        makerParams.put("userName", userName);
        makerParams.put("newUserNum", newUserNum);
        
        // 참가자에게 알림
        Map<String, Object> userParams = new HashMap<>();
        userParams.put("newUserNum", newUserNum);
        userParams.put("roomTitle", roomTitle);
        userParams.put("roomMaker", roomMaker);
        
        return noticeDao.insertRoomEnterNoticeToMaker(makerParams) > 0 &&
               noticeDao.insertRoomEnterNoticeToUser(userParams) > 0;
    }

    // 방 상태 변경 알림 생성
    public boolean createStatusChangeNotice(int roomNum, String roomTitle, int roomMaker, int statusNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomNum);
        params.put("roomTitle", roomTitle);
        params.put("roomMaker", roomMaker);
        
        if (statusNum == 3) { // 시작
            return noticeDao.insertRoomStartNotice(params) > 0;
        } else if (statusNum == 4) { // 종료
            return noticeDao.insertRoomEndNotice(params) > 0;
        }
        
        return false;
    }

    // 알림 읽음 처리
    public boolean markNoticeAsRead(int noticeNum) {
        return noticeDao.updateNoticeReadStatus(noticeNum) > 0;
    }
    
 // 알림 목록 조회 추가
    public List<HmkNoticeVo> getNotices(int userNum, String startDate, String endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNum", userNum);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return noticeDao.getNotices(params);
    }

    // 알림 요약 정보 조회 추가
    public Map<String, Integer> getNoticeSummary(int userNum) {
        return noticeDao.getNoticeSummary(userNum);
    }
}
