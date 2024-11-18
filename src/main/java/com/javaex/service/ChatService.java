// src/com/javaex/service/ChatService.java

package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.ChatDao;
import com.javaex.vo.ChatVo;

@Service
public class ChatService {
    
    @Autowired
    private ChatDao chatDao;
    
    // 특정 roomNum의 모든 채팅 메시지 가져오기
    public List<ChatVo> getChatMessages(int roomNum) {
        return chatDao.getChatMessages(roomNum);
    }
    
    // 새로운 채팅 메시지 전송
    public void sendMessage(ChatVo chatVo) {
        chatDao.insertMessage(chatVo);
    }
}
