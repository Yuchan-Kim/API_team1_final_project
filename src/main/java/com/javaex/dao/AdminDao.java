package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public int insertItem(AdminVo itemVo) {
        System.out.println("AdminDao.insertItem()");
        return sqlSession.insert(namespace + ".insertItem", itemVo);
    }
    
 // **새로운 메소드 추가: 모든 상품 정보 가져오기**
    public List<AdminVo> getAllItems() {
        System.out.println("AdminDao.getAllItems()");
        return sqlSession.selectList(namespace + ".selectAllItems");
    }
    public List<AdminVo> getItemBrands() {
        System.out.println("AdminDao.getItemBrands()");
        return sqlSession.selectList(namespace + ".selectAllItemBrands");
    }

    public int insertItemBrand(String itemBrandName) {
        System.out.println("AdminDao.insertItemBrand()");
        AdminVo adminVo = new AdminVo();
        adminVo.setItemBrandName(itemBrandName);
        return sqlSession.insert(namespace + ".insertItemBrand", adminVo);
    }
    
    public AdminVo getItemInfo (int itemNum) {
    	return sqlSession.selectOne(namespace + ".getItemInfo", itemNum);
    }
    
    public int updateItem(AdminVo itemVo) {
        System.out.println("AdminDao.updateItem()");
        return sqlSession.update(namespace + ".updateItem", itemVo);
    }
    
    public int deleteItem(int itemNum) {
        System.out.println("AdminDao.deleteItem()");
        return sqlSession.delete(namespace + ".deleteItem", itemNum);
    }
    
 // 테이블 데이터 조회
    public List<AdminVo> getTableData(String tableName) {
        System.out.println("AdminDao.getTableData() - Table: " + tableName);
        return sqlSession.selectList(namespace + ".getTableData", tableName);
    }

    // 테이블 데이터 추가
    public int insertTableData(String tableName, AdminVo adminVo) {
        System.out.println("AdminDao.insertTableData() - Table: " + tableName);
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);
        params.put("adminVo", adminVo);
        return sqlSession.insert(namespace + ".insertTableData", params);
    }

    // 테이블 데이터 수정
    public int updateTableData(String tableName, AdminVo adminVo) {
        System.out.println("AdminDao.updateTableData() - Table: " + tableName);
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);
        params.put("adminVo", adminVo);
        return sqlSession.update(namespace + ".updateTableData", params);
    }

    // 테이블 데이터 삭제
    public int deleteTableData(String tableName, int id) {
        System.out.println("AdminDao.deleteTableData() - Table: " + tableName);
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);
        params.put("id", id);
        return sqlSession.delete(namespace + ".deleteTableData", params);
    }
    
    public int insertNotice(AdminVo notice) {
        System.out.println("AdminDao.insertNotice()");
        return sqlSession.insert(namespace + ".insertNotice", notice);
    }

    public List<Integer> getAllUserNums() {
        System.out.println("AdminDao.getAllUserNums()");
        return sqlSession.selectList(namespace + ".getAllUserNums");
    }


}
