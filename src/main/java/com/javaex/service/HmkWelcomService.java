package com.javaex.service;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.HmkNoticeDao;
import com.javaex.dao.HmkPointDao;

@Service
public class HmkWelcomService {
	
	 // Logger 선언 (문제가 되는 부분)
    private static final Logger logger = LoggerFactory.getLogger(HmkWelcomService.class);

	@Autowired
    private HmkNoticeDao noticeDao;
    
    @Autowired
    private HmkPointDao pointDao;
    
    @Transactional  // 트랜잭션 처리 중요
    public void celebrateNewUser(int userNum) {
        try {
            // 가입 축하 알림 생성
            noticeDao.insertSignupNotice(userNum);
            System.out.println("가입 축하 알림 생성 완료 - userNum: " + userNum);
            
            // 가입 축하 포인트 지급
            Map<String, Object> pointParams = new HashMap<>();
            pointParams.put("userNum", userNum);
            pointDao.insertSignupPoint(pointParams);
            System.out.println("가입 축하 포인트 지급 완료 - userNum: " + userNum);
            
            logger.info("회원가입 축하 처리 완료 - userNum: {}", userNum);
        } catch (Exception e) {
            logger.error("회원가입 축하 처리 중 오류 발생 - userNum: {}", userNum, e);
            throw e;  // 트랜잭션 롤백을 위해 예외 재발생
        }
        
    }
}
