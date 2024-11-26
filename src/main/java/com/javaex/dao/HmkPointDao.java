package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HmkPointDao {
    @Autowired
    private SqlSession sqlSession;
    
    public int insertSignupPoint(Map<String, Object> params) {
        return sqlSession.insert("Point.insertSignupPoint", params);
    }
}