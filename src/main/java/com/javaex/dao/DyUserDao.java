package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.DyUserVo;

@Repository 
public class DyUserDao {
	
	@Autowired
	private SqlSession sqlSession;	

	/* 회원가입 */
	public int insertUser(DyUserVo dyUserVo) {
		System.out.println("DyUserDao.insertUser()");
		
		int count = sqlSession.insert("dyuser.insert", dyUserVo);
		
		return count;
		
	}
	
	/* 이메일 중복체크 */
	public int selectUserByEmail(String userEmail) {
		System.out.println("DyUserDao.selectUserByEmail()");
		System.out.println(userEmail);
		
		// 내가 요구해서 받는 count임 
		int count = sqlSession.selectOne("dyuser.selectByEmail", userEmail);
		System.out.println(count);
		
		return count;
		
	}
	
	/* 닉네임 중복체크 */
	public int selectUserByName(String userName) {
		System.out.println("DyUserDao.selectUserByName()");
		System.out.println(userName);
		
		int count = sqlSession.selectOne("dyuser.selectByName", userName);
		System.out.println(count);
		
		return count;
		
	}
	
	
	/* 로그인 */
	public DyUserVo selectUser(DyUserVo dyUserVo) {
		System.out.println("DyUserDao.selectUser()");
		
		DyUserVo authUser = sqlSession.selectOne("dyuser.selectByEmailPw", dyUserVo);
		return authUser;
	}
	
	
}
