package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
//	MemberService memberService2 = new MemberServiceImpl();  // 위와 동일
	@Autowired
	HttpSession session;
	
	// 회원가입 저장
		@PostMapping("/member/member")
		public String member(MemberDto mdto) {
			System.out.println("입력된 name : "+mdto.getName());
			memberService.member(mdto);
			return "redirect:/";
		}
		
		// 회원가입 페이지
		@GetMapping("/member/member")
		public String member() {
			return "member";
		}
		
		// 로그아웃 페이지
		@GetMapping("/member/logout")  
		public String logout(Model model) {  
			session.invalidate();
			model.addAttribute("chkLogin","0");
			return "logout";
		}
		
		// 로그인 페이지 열기
		@GetMapping("/member/login")  
		public String login() {  
			return "login";
		}
		
		// 로그인 체크확인
		@PostMapping("/member/login")  
		public String login(String id, String pw, Model model) {  // 여러개 일땐 'MemberDto mdto'로 받으면 편함
			MemberDto memberDto = memberService.login(id,pw);
			if(memberDto != null) {
				model.addAttribute("chkLogin","1");  // 로그인 성공시
				session.setAttribute("session_id", id);  // 섹션 추가
				session.setAttribute("session_name",memberDto.getName());
			}else {
				model.addAttribute("chkLogin","0");  // 로그인 실패시
			}
			return "login";
		}

}
