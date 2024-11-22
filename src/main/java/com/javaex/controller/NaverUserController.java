package com.javaex.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.NaverUserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.HmkNaverUserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class NaverUserController {
    @Autowired
    private NaverUserService userService;
    
    @PostMapping("/api/users/naver/login")
    public JsonResult naverLogin(@RequestBody Map<String, String> request, HttpServletResponse response) {
        String accessToken = request.get("accessToken");
        
        // 네이버 API 호출
        String userInfoJson = getUserInfo(accessToken);
        JSONObject userInfo = new JSONObject(userInfoJson);
        JSONObject naverResponse = userInfo.getJSONObject("response");
        
        // 사용자 정보 추출
        HmkNaverUserVo userVo = new HmkNaverUserVo();
        userVo.setUserEmail(naverResponse.getString("email"));
        userVo.setUserName(naverResponse.getString("nickname"));
        userVo.setSocialLogin("naver");
        
        // DB 처리
        HmkNaverUserVo authUser = userService.SetNaverUser(userVo);
        
        if(authUser != null) {
            JwtUtil.createTokenAndSetHeader(response, ""+authUser.getUserNum());
            return JsonResult.success(authUser);
        }
        return JsonResult.fail("네이버 로그인 처리 실패");
    }
    
    private String getUserInfo(String accessToken) {
        try {
        	URL url = URI.create("https://openapi.naver.com/v1/nid/me").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("네이버 사용자 정보 조회 실패", e);
        }
    }
}