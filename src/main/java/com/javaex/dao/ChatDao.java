// src/com/javaex/dao/ChatDao.java

package com.javaex.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ChatVo;

@Repository
public class ChatDao {
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    private String namespace = "Chat"; // MyBatis 매퍼 네임스페이스
    
    // 특정 roomNum의 모든 채팅 메시지 조회
    public List<ChatVo> getChatMessages(int roomNum) {
        return sqlSession.selectList(namespace + ".getChatMessages", roomNum);
    }
    
    // 새로운 채팅 메시지 삽입
    public void insertMessage(ChatVo chatVo) {
        sqlSession.insert(namespace + ".insertMessage", chatVo);
    }
}
