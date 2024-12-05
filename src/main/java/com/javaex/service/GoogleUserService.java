package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.GoogleUserDao;
import com.javaex.vo.HmkSocialUserVo;

@Service
public class GoogleUserService {

	@Autowired
	private GoogleUserDao userDao;

	@Autowired
	private HmkWelcomService welcomeService; // 가입 축하 처리를 위해 추가

	/* 구글 회원 가입 및 로그인 */
	@Transactional
	public HmkSocialUserVo SetGoogleUser(HmkSocialUserVo userVo) {
	    System.out.println("구글 로그인 시도 - 이메일: " + userVo.getUserEmail());
	    
	    HmkSocialUserVo existingUser = userDao.selectUserByEmail(userVo.getUserEmail());
	    System.out.println("기존 사용자 조회 결과: " + existingUser);

	    if (existingUser == null) {
	        System.out.println("새 사용자 등록 시작");
	        userVo.setRegionNum(1);
	        userVo.setSocialLogin("google");
	        int insertResult = userDao.insertGoogleUser(userVo);
	        System.out.println("새 사용자 등록 결과: " + insertResult);

	        HmkSocialUserVo newUser = userDao.selectUserByEmail(userVo.getUserEmail());
	        System.out.println("새로 등록된 사용자 정보: " + newUser);

	        welcomeService.celebrateNewUser(newUser.getUserNum());
	        return newUser;
	    }

	    System.out.println("기존 사용자 반환: " + existingUser);
	    return existingUser;
	}
}
