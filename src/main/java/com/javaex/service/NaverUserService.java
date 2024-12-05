package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.NaverUserDao;
import com.javaex.vo.HmkSocialUserVo;

@Service
public class NaverUserService {
    
    @Autowired
    private NaverUserDao userDao;
    
    @Autowired
    private HmkWelcomService welcomeService;
    
    /* 네이버 회원 가입 */
    public HmkSocialUserVo SetNaverUser(HmkSocialUserVo userVo) {
        HmkSocialUserVo existingUser = userDao.selectUserByEmail(userVo.getUserEmail());
        System.out.println("서비스가 받은거: 포인트는 신규만줘 제발!!" + userVo);
        if (existingUser == null) {
            // 새 회원으로 가입
            userVo.setRegionNum(1);
            userVo.setSocialLogin("naver");
            userDao.insertNaverUser(userVo);
            HmkSocialUserVo newUser = userDao.selectUserByEmail(userVo.getUserEmail());
            
            // 환영 메시지 및 포인트 지급
            welcomeService.celebrateNewUser(newUser.getUserNum());
            
            return newUser;
        }
        return existingUser;
    }
}

