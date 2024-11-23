// src/com/javaex/controller/ChatController.java

package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.ChatService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.ChatVo;

import jakarta.servlet.http.HttpServletRequest;

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

    
    @GetMapping("/info")
    public JsonResult getUserInfo(HttpServletRequest request) {
        try {
            int userNum = JwtUtil.getNoFromHeader(request);
            return JsonResult.success(userNum);
        } catch (Exception e) {
            return JsonResult.fail("유저 정보를 가져오는 데 실패했습니다.");
        }
    }
    
    // POST: 새로운 채팅 메시지 보내기
    @PostMapping("/")
    public JsonResult sendMessage(@RequestBody ChatVo chatVo, HttpServletRequest request) {
        try {
            // 토큰에서 userNum 추출
            String token = request.getHeader("Authorization");
            int userNum = JwtUtil.getNoFromHeader(request); // 이 메서드를 구현하여 userNum을 추출하세요.
            chatVo.setChatter(userNum); // chatter를 현재 사용자 번호로 설정
            chatService.sendMessage(chatVo);
            return JsonResult.success("메시지를 성공적으로 보냈습니다.");
        } catch (Exception e) {
            return JsonResult.fail("메시지 전송에 실패했습니다.");
        }
    }

    // getUserNumFromToken 메서드 구현 필요 (UserController와 동일하게)

    
 
}
