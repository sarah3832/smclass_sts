package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.KakaoProfile;
import com.java.dto.OAuthtoken;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	
	@Autowired
	HttpSession session;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/kakao/oauth")
//	@ResponseBody
	public String oauth(String code) {
		System.out.println("kakao oauth coke : "+code);
		// code : code
		String grant_type = "authorization_code";
		String client_id = "820cec10ac246e5c29e35fdf05f1f0a5";
		String redirect_uri = "http://localhost:8181/kakao/oauth";
		String content_type = "application/x-www-form-urlencoded;charset=utf-8";
		
		// http 전송 : RestTemplate
		RestTemplate rt = new RestTemplate();
		// header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", content_type);

		// 4개 데이터 묶기
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		
		// headers, 4개 데이터 묶기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);
		// http요청 url
		String oauthUrl = "https://kauth.kakao.com/oauth/token";
		
		// http 전송 - 토큰키 받기
		ResponseEntity<String> response = 
		rt.exchange(oauthUrl, HttpMethod.POST, kakaoTokenRequest, String.class);
		System.out.println("kakaoToken response : "+response);
		
		// response 데이터(json)를 OauthToken dto객체 로 저장
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthtoken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthtoken.class);
		} catch (Exception e) {e.printStackTrace();}
		
		System.out.println("oauthToken 객체에서 access_token 출력 : "+oauthToken.getAccess_token());
		
		
		// -------------------------------------------------------------------------
		
		// access_token 분리해서 전송하면 회원정보 아이디,닉네임,사진,성별 등을 받아옴.
		String access_token = oauthToken.getAccess_token();
		String authorization = "Bearer "+access_token;
		content_type = "application/x-www-form-urlencoded;charset=utf-8";
		
		// http 전송 - RestTemplage
		RestTemplate rt2 = new RestTemplate();
		// header
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", authorization);
		headers2.add("Content-Type", content_type);
		
		// 1개로 묶기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 = 
				new HttpEntity<>(headers2);
		// url
		String oauthUrl2 = "https://kapi.kakao.com/v2/user/me";
		
		// http 전송 - 카카오 회원정보 : 닉네임,아이디,사진,성별 등 받을 수 있음.
		ResponseEntity<String> response2 = 
		rt2.exchange(oauthUrl2, HttpMethod.POST,kakaoTokenRequest2,String.class);
		System.out.println("회원정보 response2 : "+response2);
		System.out.println("데이터 출력 : "+response2.getBody());
		
		// response 데이터(json)를 OauthToken KaKaoProfile객체 저장
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (Exception e) {e.printStackTrace();}
		
		System.out.println("KaKaoProfile 닉네임 : "+kakaoProfile.getProperties().getNickname());
		System.out.println("KaKaoProfile id : "+kakaoProfile.getId());
		System.out.println("KaKaoProfile profile_image : "+kakaoProfile.getProperties().getProfile_image());
		
		// 섹션저장 - 로그인을 시켜주면 됨.
		session.setAttribute("kakaoProfile_nickname", kakaoProfile.getProperties().getNickname());
		session.setAttribute("kakaoProfile_id", kakaoProfile.getId());
		
		
		return "redirect:/";
	}
	
	
	
	
	
	
}
