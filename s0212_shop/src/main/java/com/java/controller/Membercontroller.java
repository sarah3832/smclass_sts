package com.java.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class Membercontroller {
	
	@Autowired
	HttpSession session;
	@Autowired
	MemberService memberSerive;
	
	// 리액트 회원전체 가져오기
	@CrossOrigin
	@ResponseBody
	@GetMapping("/member/memberList")
	public List<MemberDto> memberList() {
		List<MemberDto> list = memberSerive.memberList();
		
		return list;
	}
	
	// 리액트 회원1명 가져오기
	// json데이터로 전달 받으면 @RequestBody 붙어야함.
	@CrossOrigin
	@ResponseBody
	@GetMapping("/member/memberView")
	public MemberDto memberView(String id) {
		System.out.println("memberView id : "+id);
		MemberDto memberDto = memberSerive.memberView(id);
		
		return memberDto;
	}
	
	// 로그인 페이지 열기
	@GetMapping("/member/login")
	public String login(HttpServletResponse response) {
		// 쿠키생성 - 자바에서 생성
//		Cookie cookie = new Cookie("cook_id", "aaa");
//		cookie.setMaxAge(60*60*24); // 1일
//		response.addCookie(cookie); // cookie 저장
		
		return "member/login";
	}
	
	// 로그인 하기
	@PostMapping("/member/login")
	public String login(MemberDto mdto, Model model) {
		MemberDto memberDto = memberSerive.login(mdto);
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			return "redirect:/?loginChk=1";
		}else {
			model.addAttribute("loginChk",0);
			return "member/login";
		}
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/?loginChk=0";
	}
	
	// 회원가입 페이지 01
	@GetMapping("/member/step01")
	public String step01() {
		return "member/step01";
	}
	
	// 회원가입 페이지 02
	@GetMapping("/member/step02")
	public String step02() {
		session.removeAttribute("pwCode");  // 섹션 삭제
		return "member/step02";
	}
	
	// 인증코드 이메일 발송 - text
	@ResponseBody
	@PostMapping("/member/sendEmail")
	public String sendEmail(String email) {
		System.out.println("sendEmail : "+email);
		String pwCode = memberSerive.sendEmail(email);  // email 발송 - text
		
		return pwCode;
	}
	
	// 인증코드 이메일 발송2 - html
	@ResponseBody
	@PostMapping("/member/sendEmail2")
	public String sendEmail2(String email) {
		System.out.println("sendEmail2 : "+email);
		String pwCode = memberSerive.sendEmail2(email);  // email 발송 - html
		session.setAttribute("pwCode", pwCode);
		
		return pwCode;
	}
	
	// 인증코드 확인
	@ResponseBody
	@PostMapping("/member/pwCodeCheck")
	public String pwCodeCheck(String pwCode) {
		System.out.println("pwCodeCheck pwCode : "+pwCode);
		String pw = (String)session.getAttribute("pwCode");
		if(pwCode.equals(pw)) {
			return "1";
		}else {
			return "0";
		}
	}
	

	
	
}
