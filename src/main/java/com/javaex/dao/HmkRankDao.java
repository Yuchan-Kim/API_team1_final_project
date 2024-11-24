package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.HmkUserVo;

@Repository
public class HmkRankDao {
    @Autowired
    private SqlSession sqlSession;

    public List<HmkUserVo> selectTop10Ranks() {
        System.out.println("[DAO] selectTop10Ranks 호출됨");
        List<HmkUserVo> result = sqlSession.selectList("rank.selectTop10");
        System.out.println("[DAO] selectTop10Ranks SQL 실행 결과: " + result);
        return result;
    }

    public HmkUserVo selectUserRank(int userNum) {
        System.out.println("[DAO] selectUserRank 호출됨 - userNum: " + userNum);
        HmkUserVo result = sqlSession.selectOne("rank.selectUserRank", userNum);
        System.out.println("[DAO] selectUserRank SQL 실행 결과: " + result);
        return result;
    }
}