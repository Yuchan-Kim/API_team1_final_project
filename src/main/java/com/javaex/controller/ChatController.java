// src/com/javaex/controller/ChatController.java

package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaex.service.ChatService;
import com.javaex.util.JsonResult;
import com.javaex.vo.ChatVo;

@RestController
@RequestMapping("api/chat")
public class ChatController {
    
    @Autowired
    private ChatService chatService;
    
    // GET: 특정 roomNum의 채팅 메시지 모두 가져오기
    @GetMapping("/{roomNum}")
    public JsonResult getChatMessages(@PathVariable int roomNum) {
        try {
            List<ChatVo> messages = chatService.getChatMessages(roomNum);
            return JsonResult.success(messages);
        } catch (Exception e) {
            return JsonResult.fail("채팅 메시지를 불러오는 데 실패했습니다.");
        }
    }
    
    // POST: 새로운 채팅 메시지 보내기
    @PostMapping("/")
    public JsonResult sendMessage(@RequestBody ChatVo chatVo) {
        try {
            chatService.sendMessage(chatVo);
            return JsonResult.success("메시지를 성공적으로 보냈습니다.");
        } catch (Exception e) {
            return JsonResult.fail("메시지 전송에 실패했습니다.");
        }
    }
}
