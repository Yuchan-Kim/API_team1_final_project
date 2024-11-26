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

}
