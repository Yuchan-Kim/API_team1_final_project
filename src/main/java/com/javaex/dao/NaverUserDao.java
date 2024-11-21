package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.HmkNaverUserVo;

@Repository
public class NaverUserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 네이버 회원 가입 */
	public int insertNaverUser(HmkNaverUserVo userVo) {
		return sqlSession.insert("naveruser.insertNaver", userVo);
	}
	
	/* 디비에서 이메일 존재 확인 */
	public HmkNaverUserVo selectUserByEmail(String email) {
		return sqlSession.selectOne("naveruser.selectByEmailOnly", email);
	}

}
