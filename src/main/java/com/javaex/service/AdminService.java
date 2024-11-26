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

    public List<AdminVo> getAllItems() {
        return admindao.getAllItems();
    }
}
