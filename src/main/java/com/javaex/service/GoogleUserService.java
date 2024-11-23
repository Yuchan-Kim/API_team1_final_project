package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GoogleUserDao;
import com.javaex.vo.HmkSocialUserVo;

@Service
public class GoogleUserService {
	
	@Autowired
	private GoogleUserDao userDao;
	
	/* 구글 회원 가입 및 로그인 */
	
	public HmkSocialUserVo SetGoogleUser(HmkSocialUserVo userVo) {
		HmkSocialUserVo existingUser = userDao.selectUserByEmail(userVo.getUserEmail());
		System.out.println("서미스가 받은거: "+ userVo);
		if (existingUser == null) {
			//새 회원으로 가입
			userVo.setRegionNum(1);
			userVo.setSocialLogin("naver");
			userDao.insertGoogleUser(userVo);
			return userDao.selectUserByEmail(userVo.getUserEmail());
			
		}
		return existingUser;
	}

}
