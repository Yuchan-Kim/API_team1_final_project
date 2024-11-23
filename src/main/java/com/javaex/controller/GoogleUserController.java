package com.javaex.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.javaex.service.GoogleUserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.HmkSocialUserVo;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
@RestController
public class GoogleUserController {
	@Value("${google.client.id}")
    private String googleClientId;  // 클래스 변수로 선언
	@Autowired
	private GoogleUserService userService;

	private GoogleIdTokenVerifier verifier;

	
	@PostConstruct  // 추가
    public void init() {
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList(googleClientId))
            .build();
    }

	@PostMapping("/api/users/google/login")
	public JsonResult googleLogin(@RequestBody Map<String, String> request, HttpServletResponse response) {
		System.out.println("구글은 뭐 보냄? " + response);
		try {
			String credential = request.get("credential");

			// Google ID 토큰 검증
			GoogleIdToken idToken = verifier.verify(credential);
			if (idToken != null) {
				GoogleIdToken.Payload payload = idToken.getPayload();

				// 사용자 정보 추출
				String email = payload.getEmail();
				String name = (String) payload.get("name");

				HmkSocialUserVo userVo = new HmkSocialUserVo();
				userVo.setUserEmail(email);
				userVo.setUserName(name);
				userVo.setSocialLogin("google");

				// DB 처리
				HmkSocialUserVo authUser = userService.SetGoogleUser(userVo);

				if (authUser != null) {
					JwtUtil.createTokenAndSetHeader(response, "" + authUser.getUserNum());
					return JsonResult.success(authUser);
				}
			}
			return JsonResult.fail("Google 로그인 처리 실패");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("Google 로그인 처리 중 오류 발생: " + e.getMessage());
		}
	}
}