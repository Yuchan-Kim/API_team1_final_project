package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.DJcsService;
import com.javaex.util.JsonResult;
import com.javaex.vo.DJcsBotVo;
import com.javaex.vo.DyFAQVo;

@RestController	
public class DJcsController {

	@Autowired		
	private DJcsService DJcsService;
	
	
	/* 고객센터 FAQ */
	@GetMapping("/api/faq")
	public JsonResult faqForm() {
	    //System.out.println("DJcsController.faqForm()");

	    List<DyFAQVo> FAQList = DJcsService.exeFAQList();

	    return JsonResult.success(FAQList);
	}
	
	
   @GetMapping("/api/getPrompt")
   public JsonResult faqForm2() {
//	   System.out.println("DJcsController.faqForm2()");

       List<DyFAQVo> FAQList = DJcsService.exeFAQList2();

       return JsonResult.success(FAQList);
   }

	/* 채팅내용 저장 */
   @PostMapping("/api/saveChat")
   public JsonResult saveChat(@RequestBody DJcsBotVo chatMessage) {
       System.out.println("DJcsController.saveChat()");

       boolean isSaved = DJcsService.saveChat(chatMessage);

       if (isSaved) {
           return JsonResult.success("채팅 저장 성공");
       } else {
           return JsonResult.fail("채팅 저장 실패");
       }
   }
   
   /* 최근채팅내용 */
   @GetMapping("/api/getRecentChats")
   public JsonResult getRecentChats(@RequestParam("userNum") int userNum) {
       List<DJcsBotVo> recentChats = DJcsService.getRecentChats(userNum);

       if (recentChats != null) {
           return JsonResult.success(recentChats);
       } else {
           return JsonResult.fail("채팅 메시지 가져오기 실패");
       }
   }
   
   
}
