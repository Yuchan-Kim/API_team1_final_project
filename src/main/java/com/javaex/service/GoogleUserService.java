package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GoogleUserDao;
import com.javaex.vo.HmkSocialUserVo;

@Service
public class GoogleUserService {

	@Autowired
	private GoogleUserDao userDao;

	@Autowired
	private HmkWelcomService welcomeService; // 가입 축하 처리를 위해 추가

	/* 구글 회원 가입 및 로그인 */
	public HmkSocialUserVo SetGoogleUser(HmkSocialUserVo userVo) {
		HmkSocialUserVo existingUser = userDao.selectUserByEmail(userVo.getUserEmail());
		System.out.println("서비스가 받은거: " + userVo);

		if (existingUser == null) {
			// 새 회원으로 가입
			userVo.setRegionNum(1);
			userVo.setSocialLogin("google"); // "naver"에서 "google"로 수정
			userDao.insertGoogleUser(userVo);

			// 새로 가입한 사용자 정보 조회
			HmkSocialUserVo newUser = userDao.selectUserByEmail(userVo.getUserEmail());

			// 가입 축하 처리
			welcomeService.celebrateNewUser(newUser.getUserNum());

			return newUser;
		}

		return existingUser;
	}
}
