package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.AdminService;
import com.javaex.util.JsonResult;
import com.javaex.vo.AdminVo;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/key-stats")
	public JsonResult getKeyStats() {
	    System.out.println("AdminController.getKeyStats()");
	    AdminVo keyStats = service.getKeyStats();
	    if (keyStats != null) {
	        return JsonResult.success(keyStats);
	    } else {
	        return JsonResult.fail("주요 통계 데이터를 불러오는 중 오류 발생");
	    }
	}
	@GetMapping("/userlist")
	public JsonResult getAllUserList() {
		System.out.println("AdminController.getAllUserList()");
		List<AdminVo> userList = service.getAllUserList();
		if (userList != null) {
			return JsonResult.success(userList);
		}else {
			return JsonResult.fail("유저 목록을 불러오는 중 오류 발생");
		}
	}
	
	
	@GetMapping("/signupusersrate")
	public JsonResult getSignUpRate() {
		System.out.println("AdminController.getSignUpRate()");
		List<AdminVo> signupRate = service.getSignUpRate();
		if (signupRate != null) {
			return JsonResult.success(signupRate);
		}else {
			return JsonResult.fail("가입율 분석 실패");
		}

	}
		
	@GetMapping("/sales")
	public JsonResult getSalesData() {
		System.out.println("AdminController.getSalesData()");
		List<AdminVo> salesData = service.getSalesData();
		if (salesData != null) {
			return JsonResult.success(salesData);
		}else {
			return JsonResult.fail("판매 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/category-distribution")
	public JsonResult getCategoryDistribution() {
		System.out.println("AdminController.getCategoryDistribution()");
		List<AdminVo> categoryDistribution = service.getCategoryDistribution();
		if (categoryDistribution != null) {
			return JsonResult.success(categoryDistribution);
		}else {
			return JsonResult.fail("카테고리 분포 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/category-performance")
	public JsonResult getCategoryPerformance() {
		System.out.println("AdminController.getCategoryPerformance()");
		List<AdminVo> categoryPerformance = service.getCategoryPerformance();
		if (categoryPerformance != null) {
			return JsonResult.success(categoryPerformance);
		}else {
			return JsonResult.fail("카테고리 퍼포먼스 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/recent-activities")
	public JsonResult getRecentActivities() {
		System.out.println("AdminController.getRecentActivities()");
		List<AdminVo> recentActivities = service.getRecentActivities();
		if (recentActivities != null) {
			return JsonResult.success(recentActivities);
		}else {
			return JsonResult.fail("최근 활동 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/items")
	public JsonResult getAllItems() {
	    System.out.println("AdminController.getAllItems()");
	    List<AdminVo> items = service.getAllItems();
	    if (items != null) {
	        return JsonResult.success(items);
	    } else {
	        return JsonResult.fail("상품 데이터를 불러오는 중 오류 발생");
	    }
	}
	

	
}
