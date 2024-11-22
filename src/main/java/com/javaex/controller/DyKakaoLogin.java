package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.DyKakaoService;
import com.javaex.service.DyUserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.DyUserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class DyKakaoLogin {
   
   @Autowired
   private DyUserService dyUserService;

   @Autowired
   private DyKakaoService dyKakaoService; 

   /* 카카오 로그인 액세스 토큰 가져오기 */
   @PostMapping("/api/auth/kakao")
   public JsonResult kakaLogin(@RequestParam String authorizeCode) {
      try {

         // Access Token 가져오기
         String accessToken = dyKakaoService.getKakaoAccessToken(authorizeCode);
         
         System.out.println("1 -- accessToken : " + accessToken);
         
         // 성공적으로 토큰을 가져왔을 경우
         return JsonResult.success(accessToken);

      } catch (Exception e) {
         // 예외 처리 및 에러 메시지 반환
         System.err.println("카카오 로그인 중 오류 발생: " + e.getMessage());
         e.printStackTrace();
         return JsonResult.fail("카카오 로그인 실패. 관리자에게 문의하세요.");
      }
   }

   /* 카카오 유저 정보 가져오기 및 유저 체크 */
   @GetMapping("/api/users/profile")
   public JsonResult getUserInfo(@RequestHeader("Authorization") String authHeader, HttpServletResponse response) {
       try {
           // Step 1: 카카오 사용자 정보 가져오기
    	   DyUserVo userInfo = dyKakaoService.getKakaoUserProfile(authHeader);

           // Step 2: 회원 존재 여부 체크
           boolean isUserExists = dyUserService.exeEmailCheck(userInfo.getUserEmail());

           // Step 3: 결과에 따른 응답
           if (isUserExists) { // 없는 이메일이므로 신규 회원
               // 신규 사용자
               return JsonResult.success(Map.of(
                   "userInfo", userInfo,
                   "message", "신규 회원"
               ));
           } else {
        	   DyUserVo authUser = dyUserService.exeKakaoLogin(userInfo.getUserEmail()); // 이메일로 회원 정보 가져오기
              if (authUser != null) {
                 JwtUtil.createTokenAndSetHeader(response, "" + authUser.getUserEmail());
              } else {
                 // 예외 처리: 회원 정보가 없는 경우
               return JsonResult.fail("사용자 정보를 가져올 수 없습니다.");
              }
              
               // 이미 가입된 사용자
               return JsonResult.success(Map.of(
                   "userInfo", userInfo,
                   "message", "이미 가입된 이메일",
                   "authUser", authUser
               ));
           }
       } catch (Exception e) {
           // 오류 처리
           return JsonResult.fail("사용자 정보 조회 또는 회원 체크 실패: " + e.getMessage());
       }
   }

   
}
