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
	                         @RequestParam(value = "state", required = false) String redirectPath,
	                         HttpServletResponse response) {
	    try {
	        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(),
	                GsonFactory.getDefaultInstance(), 
	                "https://oauth2.googleapis.com/token", 
	                googleClientId,
	                googleClientSecret, 
	                code, 
	                "https://challengedonkey.com/api/users/google/login").execute();

	        GoogleIdToken idToken = verifier.verify(tokenResponse.getIdToken());
	        if (idToken != null) {
	            GoogleIdToken.Payload payload = idToken.getPayload();
	            
	            HmkSocialUserVo userVo = new HmkSocialUserVo();
	            userVo.setUserEmail(payload.getEmail());
	            userVo.setUserName((String) payload.get("name"));
	            userVo.setSocialLogin("google");

	            HmkSocialUserVo authUser = userService.SetGoogleUser(userVo);
	            if (authUser != null) {
	                welcomeService.celebrateNewUser(authUser.getUserNum());
	                JwtUtil.createTokenAndSetHeader(response, String.valueOf(authUser.getUserNum()));

	                String authHeader = response.getHeader("Authorization");
	                if (authHeader != null && authHeader.startsWith("Bearer ")) {
	                    String token = authHeader.substring(7);
	                    String cookie = String.format("token=%s; HttpOnly; Secure; SameSite=Lax; Path=/;", token);
	                    response.addHeader("Set-Cookie", cookie);
	                    
	                    String baseUrl = "https://challengedonkey.com";
	                    String path = (redirectPath != null && !redirectPath.isEmpty()) ? redirectPath : "";
	                    String redirectUrl = baseUrl + path + "?token=" + token + "&authUser=" 
	                        + URLEncoder.encode(new Gson().toJson(authUser), StandardCharsets.UTF_8);
	                    
	                    response.sendRedirect(redirectUrl);
	                    return;
	                }
	            }
	        }
	        
	        String fallbackUrl = (redirectPath != null && redirectPath.startsWith("/mobile"))
	            ? "https://challengedonkey.com/mobile?error=google"
	            : "https://challengedonkey.com/user/loginform?error=google";
	        response.sendRedirect(fallbackUrl);
	        
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
