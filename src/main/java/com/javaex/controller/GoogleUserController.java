package com.javaex.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.javaex.service.GoogleUserService;
import com.javaex.service.HmkWelcomService;
import com.javaex.util.JwtUtil;
import com.javaex.vo.HmkSocialUserVo;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GoogleUserController {

	@Value("${google.client.id}")
	private String googleClientId;

	@Value("${google.client.secret}")
	private String googleClientSecret;

	@Autowired
	private HmkWelcomService welcomeService;

	@Autowired
	private GoogleUserService userService;

	private GoogleIdTokenVerifier verifier;

	@PostConstruct
	public void init() {
		NetHttpTransport transport = new NetHttpTransport();
		GsonFactory jsonFactory = GsonFactory.getDefaultInstance();

		verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(googleClientId)).build();
	}

	/**
	 * GET 요청을 처리하는 엔드포인트 (OAuth 리다이렉트 URI)
	 */
	@GetMapping("/api/users/google/login")
	public void googleCallback(@RequestParam("code") String code,
			@RequestParam(value = "redirect", required = false) String redirect, HttpServletResponse response) {
		try {
			// Google OAuth 토큰 요청
			GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(),
					GsonFactory.getDefaultInstance(), "https://oauth2.googleapis.com/token", googleClientId,
					googleClientSecret, code, "https://challengedonkey.com/api/users/google/login").execute();

			// ID 토큰 검증
			GoogleIdToken idToken = verifier.verify(tokenResponse.getIdToken());
			if (idToken != null) {
				GoogleIdToken.Payload payload = idToken.getPayload();

				String email = payload.getEmail();
				String name = (String) payload.get("name");

				// 사용자 VO 생성
				HmkSocialUserVo userVo = new HmkSocialUserVo();
				userVo.setUserEmail(email);
				userVo.setUserName(name);
				userVo.setSocialLogin("google");

				// DB 처리 (사용자 조회 또는 생성)
				HmkSocialUserVo authUser = userService.SetGoogleUser(userVo);
				if (authUser != null) {
					// 환영 메시지 및 포인트 지급
					welcomeService.celebrateNewUser(authUser.getUserNum());

					// JWT 토큰 생성 및 Authorization 헤더에 추가
					JwtUtil.createTokenAndSetHeader(response, String.valueOf(authUser.getUserNum()));

					// 쿠키 설정 (HttpOnly, Secure, SameSite=Lax)
					String authHeader = response.getHeader("Authorization");
					String token = null;

					if (authHeader != null && authHeader.startsWith("Bearer ")) {
						token = authHeader.substring(7);
						String cookie = String.format("token=%s; HttpOnly; Secure; SameSite=Lax; Path=/;", token);
						response.addHeader("Set-Cookie", cookie);
					}

					// 메인 페이지로 리다이렉트
					// 리다이렉트 URL 결정
		            String baseUrl = "https://challengedonkey.com";
		            String redirectPath = redirect != null ? redirect : "";
		            String redirectUrl = baseUrl + redirectPath + "?token=" + token + "&authUser=" 
		                + URLEncoder.encode(new Gson().toJson(authUser), StandardCharsets.UTF_8.toString());
		            
		            response.sendRedirect(redirectUrl);
		            return;
		        }
			}
			// 인증 실패 시 리다이렉트
			// 실패 시 모바일 여부에 따른 리다이렉트
	        String failureRedirect = redirect != null && redirect.startsWith("/mobile") 
	            ? "https://challengedonkey.com/mobile?error=google"
	            : "https://challengedonkey.com/user/loginform?error=google";
	        response.sendRedirect(failureRedirect);
	    } catch (Exception e) {
			e.printStackTrace();
			try {
				response.sendRedirect("https://challengedonkey.com/user/loginform?error=exception");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
}
