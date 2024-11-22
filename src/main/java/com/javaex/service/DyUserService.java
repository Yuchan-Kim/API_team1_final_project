package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.DyUserDao;
import com.javaex.vo.DyUserVo;

@Service
public class DyUserService {
	
	@Autowired
	private DyUserDao dyUserDao;
	
	/* 회원가입 */
	public int exeUserJoin(DyUserVo dyUserVo) {	 
		System.out.println("DyUserService.exeUserJoin()");
		
		int count = dyUserDao.insertUser(dyUserVo);
		
		return count;
		
	}
	
	
	/* 카카오 회원가입 */
	public int exeKakaoJoin(DyUserVo dyUserVo) {	 
		System.out.println("DyUserService.exeKakaoJoin()");
		
		int count = dyUserDao.insertKakao(dyUserVo);
		
		return count;
		
	}
	
	
	/* 이메일 중복체크  */
	public boolean exeEmailCheck(String userEmail) {
		System.out.println("DyUserService.exeEmailCheck()");

		int count = dyUserDao.selectUserByEmail(userEmail);
		
		if(count >= 1) {
			return false;		// 이미 가입된 아이디 가입불가능
		}else {
			return true;		// 없는 아이디 가입가능
		}
		
	}
	
	/* 닉네임 중복체크  */
	public boolean exeNameCheck(String userName) {
		System.out.println("DyUserService.exeNameCheck()");

		int count = dyUserDao.selectUserByName(userName);
		
		if(count >= 1) {
			return false;		// 이미 가입된 아이디 가입불가능
		}else {
			return true;		// 없는 아이디 가입가능
		}
		
	}
	
	
	/* 로그인 */
	public DyUserVo exeLogin(DyUserVo dyUserVo) {
		System.out.println("DyUserService.exeLogin()");
		
		DyUserVo authUser =dyUserDao.selectUser(dyUserVo);
		
		return authUser;
	}
	
	
	/* 헤더에 유저 포인트 */
    public int getUserPoints(int userNum) {
    	System.out.println("DyUserService.getUserPoints()");
        return dyUserDao.getUserPoints(userNum);
    }
    
    
    
	/* 이메일로 회원 정보 조회 */
    public DyUserVo exeKakaoLogin(String userEmail) {
        return dyUserDao.selectUserByUserEmail(userEmail); // 이메일로 사용자 조회
    }
    
}
