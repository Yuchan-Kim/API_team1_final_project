package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.DJcsService;
import com.javaex.util.JsonResult;
import com.javaex.vo.DyFAQVo;

@RestController	
public class DJcsController {

	@Autowired		
	private DJcsService DJcsService;
	
	
	/* 고객센터 FAQ */
	@GetMapping("/api/faq")
	public JsonResult faqForm() {
	    System.out.println("DJcsController.faqForm()");

	    List<DyFAQVo> FAQList = DJcsService.exeFAQList();

	    return JsonResult.success(FAQList);
	}
	
	
}
