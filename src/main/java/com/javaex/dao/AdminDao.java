package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AdminVo;

@Repository
public class AdminDao {
    @Autowired
    private SqlSession sqlSession;
    
    private String namespace = "admin";
    
    public List<AdminVo> getAllUserList() {
        System.out.println("AdminDao.getAllUserList()");
        return sqlSession.selectList(namespace + ".selectAllUsers");
    }
    
    public AdminVo getKeyStats(){
        System.out.println("AdminDao.getKeyStats()");
        return sqlSession.selectOne(namespace + ".getKeyStats");
    }

    
    public List<AdminVo> getSignUpRate(){
        System.out.println("AdminDao.getSignUpRate()");
        return sqlSession.selectList(namespace + ".getSignupRate");
    }
    
    public List<AdminVo> getSalesData(){
        System.out.println("AdminDao.getSalesData()");
        return sqlSession.selectList(namespace + ".getSalesData");
    }
    
    public List<AdminVo> getCategoryDistribution(){
        System.out.println("AdminDao.getCategoryDistribution()");
        return sqlSession.selectList(namespace + ".getCategoryDistribution");
    }
    
    public List<AdminVo> getCategoryPerformance(){
        System.out.println("AdminDao.getCategoryPerformance()");
        return sqlSession.selectList(namespace + ".getCategoryPerformance");
    }
    
    public List<AdminVo> getRecentActivities(){
        System.out.println("AdminDao.getRecentActivities()");
        return sqlSession.selectList(namespace + ".getRecentActivities");
    }
    
 // **새로운 메소드 추가: 모든 상품 정보 가져오기**
    public List<AdminVo> getAllItems() {
        System.out.println("AdminDao.getAllItems()");
        return sqlSession.selectList(namespace + ".selectAllItems");
    }
    
    
}
