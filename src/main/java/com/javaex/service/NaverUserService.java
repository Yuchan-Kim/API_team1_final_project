package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.NaverUserDao;
import com.javaex.vo.HmkNaverUserVo;

@Service
public class NaverUserService {
	
	@Autowired
	private NaverUserDao userDao;
	
	/* 네이버 회원 가입 */
	
	public HmkNaverUserVo SetNaverUser(HmkNaverUserVo userVo) {
		HmkNaverUserVo existingUser = userDao.selectUserByEmail(userVo.getUserEmail());
		System.out.println("서미스가 받은거: "+ userVo);
		if (existingUser == null) {
			//새 회원으로 가입
			userVo.setRegionNum(1);
			userVo.setSocialLogin("naver");
			userDao.insertNaverUser(userVo);
			return userDao.selectUserByEmail(userVo.getUserEmail());
			
		}
		return existingUser;
	}
}
