package com.javaex.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
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
	private String googleClientId; // 클래스 변수로 선언
	@Value("${google.client.secret}")
	private String googleClientSecret;

	@Autowired
	private GoogleUserService userService;

	private GoogleIdTokenVerifier verifier;

	@PostConstruct // 추가
	public void init() {
		NetHttpTransport transport = new NetHttpTransport();
		GsonFactory jsonFactory = GsonFactory.getDefaultInstance();

		verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(googleClientId)).build();
	}

	@PostMapping("/api/users/google/login")
	public JsonResult googleLogin(@RequestBody Map<String, String> request, HttpServletResponse response) {
		try {
			String credential = request.get("credential");
			GoogleIdToken idToken = verifier.verify(credential);

			if (idToken != null) {
				GoogleIdToken.Payload payload = idToken.getPayload();
				String email = payload.getEmail();
				String name = (String) payload.get("name");

				HmkSocialUserVo userVo = new HmkSocialUserVo();
				userVo.setUserEmail(email);
				userVo.setUserName(name);
				userVo.setSocialLogin("google"); // socialLogin 값 설정

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

	// 콜백용 GET 엔드포인트 추가
	@GetMapping("/api/users/google/login")
	public JsonResult googleCallback(@RequestParam("code") String code, HttpServletResponse response) {
		System.out.println("Google OAuth 콜백 - 인증 코드: " + code);

		try {
			// 받은 인증 코드로 Google Access Token 얻기
			GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(),
					GsonFactory.getDefaultInstance(), "https://oauth2.googleapis.com/token", googleClientId,
					googleClientSecret, code, "https://challengedonkey.com/api/users/google/login") // redirect_uri는
																									// Google Console에
																									// 등록된 URI와 일치해야 함
					.execute();

			// ID 토큰 검증
			GoogleIdToken idToken = verifier.verify(tokenResponse.getIdToken());
			if (idToken != null) {
				GoogleIdToken.Payload payload = idToken.getPayload();

				String email = payload.getEmail();
				String name = (String) payload.get("name");

				HmkSocialUserVo userVo = new HmkSocialUserVo();
				userVo.setUserEmail(email);
				userVo.setUserName(name);
				userVo.setSocialLogin("google");

				HmkSocialUserVo authUser = userService.SetGoogleUser(userVo);
				if (authUser != null) {
					JwtUtil.createTokenAndSetHeader(response, "" + authUser.getUserNum());
					// 프론트엔드로 리다이렉트
					response.sendRedirect("/");
					return JsonResult.success(authUser);
				}
			}
			// 실패시 로그인 페이지로 리다이렉트
			response.sendRedirect("/user/loginform");
			return JsonResult.fail("Google 로그인 처리 실패");

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("Google 로그인 처리 중 오류 발생: " + e.getMessage());
		}
	}

}