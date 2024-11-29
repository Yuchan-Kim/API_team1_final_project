package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.DJcsBotVo;
import com.javaex.vo.DyFAQVo;

@Repository 
public class DJcsDao {
	
	@Autowired
	private SqlSession sqlSession;	


	/* 고객센터 FAQ  */
	public List<DyFAQVo> getFAQList() {
		System.out.println("DJcsDao.getFAQList()");
		
		List<DyFAQVo> FAQList =sqlSession.selectList("DJcs.selectList");
		
		return FAQList;	
	}
	
   public List<DyFAQVo> getFAQList2() {
//	  System.out.println("DJcsDao.getFAQList2()");
      
      List<DyFAQVo> FAQList =sqlSession.selectList("DJcs.selectList2");
      
      return FAQList;   
   }
   
   public boolean saveChat(DJcsBotVo chatMessage) {
       //System.out.println("DJcsDao.saveChat()");

       int result = sqlSession.insert("DJcs.insertChat", chatMessage);

       return result > 0;
   }
	
   
   public List<DJcsBotVo> getRecentChats(int userNum) {
	    return sqlSession.selectList("DJcs.getRecentChats", userNum);
	}
   
}
