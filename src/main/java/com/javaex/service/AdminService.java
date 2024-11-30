package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.AdminDao;
import com.javaex.vo.AdminVo;

@Service
public class AdminService {
    @Autowired
    private AdminDao admindao;
    
    public List<AdminVo> getAllUserList() {
        return admindao.getAllUserList();
    }
    
    public List<AdminVo> getSignUpRate(){
        return admindao.getSignUpRate();
    }
    
    public List<AdminVo> getSalesData(){
        return admindao.getSalesData();
    }
    
    public List<AdminVo> getCategoryDistribution(){
        return admindao.getCategoryDistribution();
    }
    
    public List<AdminVo> getCategoryPerformance(){
        return admindao.getCategoryPerformance();
    }
    
    public List<AdminVo> getRecentActivities(){
        return admindao.getRecentActivities();
    }
    
    public AdminVo getKeyStats(){
        return admindao.getKeyStats();
    }
    
    public boolean changeUserStatus(int userNum, int newStatus) {
        return admindao.updateUserStatus(userNum, newStatus) > 0;
    }

    
    public boolean addItem(AdminVo itemVo) {
        return admindao.insertItem(itemVo) > 0;
    }

    public List<AdminVo> getAllItems() {
        return admindao.getAllItems();
    }
    public List<AdminVo> getItemBrands() {
        return admindao.getItemBrands();
    }

    public boolean addItemBrand(String itemBrandName) {
        int result = admindao.insertItemBrand(itemBrandName);
        return result > 0;
    }
    
    public AdminVo getItemInfo(int itemNum) {
    	return admindao.getItemInfo(itemNum);
    }
    
    public boolean updateItem(AdminVo itemVo) {
        return admindao.updateItem(itemVo) > 0;
    }
    
    public boolean deleteItem(int itemNum) {
        return admindao.deleteItem(itemNum) > 0;
    }
    
 // 테이블 데이터 조회
    public List<AdminVo> getTableData(String tableName) {
        return admindao.getTableData(tableName);
    }

    // 테이블 데이터 추가
    public boolean addTableData(String tableName, AdminVo adminVo) {
        return admindao.insertTableData(tableName, adminVo) > 0;
    }

    // 테이블 데이터 수정
    public boolean updateTableData(String tableName, AdminVo adminVo) {
        return admindao.updateTableData(tableName, adminVo) > 0;
    }

    // 테이블 데이터 삭제
    public boolean deleteTableData(String tableName, int id) {
        return admindao.deleteTableData(tableName, id) > 0;
    }
    
    public boolean sendNotification(AdminVo request) {
        List<Integer> userNums;

        if ("all".equals(request.getUserNums())) {
            // 모든 유저 번호 가져오기
            userNums = admindao.getAllUserNums();
        } else {
            // 특정 유저
            userNums = (List<Integer>) request.getUserNums();
        }

        boolean success = true;
        for (int userNum : userNums) {
            AdminVo notice = new AdminVo();
            notice.setUserNum(userNum);
            notice.setNoticeTitle(request.getNoticeTitle());
            notice.setNoticeMsg(request.getNoticeMsg());
            notice.setMsgSender(0); // 관리자 ID (예: 1번)
            notice.setNoticeType("ADMIN_NOTICE");

            int result = admindao.insertNotice(notice);
            if (result <= 0) {
                success = false;
            }
        }
        return success;
    }

}
