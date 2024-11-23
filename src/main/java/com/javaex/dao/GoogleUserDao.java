package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.HmkSocialUserVo;

@Repository
public class GoogleUserDao {

	@Autowired
	private SqlSession sqlSession;

	/* 구글 회원 가입 */
	public int insertGoogleUser(HmkSocialUserVo userVo) {
		return sqlSession.insert("googleuser.insertGoogle", userVo);
	}

	/* 디비에서 이메일 있나 확인 */
	public HmkSocialUserVo selectUserByEmail(String email) {
		return sqlSession.selectOne("googleuser.selectByEmailOnly", email);
	}
}
