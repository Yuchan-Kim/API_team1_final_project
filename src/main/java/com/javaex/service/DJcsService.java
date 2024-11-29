package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.DJcsDao;
import com.javaex.vo.DJcsBotVo;
import com.javaex.vo.DyFAQVo;

@Service
public class DJcsService {
	
	@Autowired
	private DJcsDao DJcsDao;
	

	/* 고객센터 FAQ  */
	public List<DyFAQVo> exeFAQList() {
		//System.out.println("DJcsService.exeFAQList()");
		
		List<DyFAQVo> FAQList = DJcsDao.getFAQList();
		
		return FAQList;
	}
	
   public List<DyFAQVo> exeFAQList2() {
//	  System.out.println("DJcsService.exeFAQList2()");
      
      List<DyFAQVo> FAQList = DJcsDao.getFAQList2();
      
      //System.out.println(FAQList);
      
      return FAQList;
   }


   public boolean saveChat(DJcsBotVo chatMessage) {
       //System.out.println("DJcsService.saveChat()");

       return DJcsDao.saveChat(chatMessage);
   }
   
   
   public List<DJcsBotVo> getRecentChats(int userNum) {
	    return DJcsDao.getRecentChats(userNum);
	}
   
}
