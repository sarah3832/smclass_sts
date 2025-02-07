package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")  // /member 생략가능
public class MController {
	
	@Autowired
	HttpSession session;  // 섹션 객체
	
	@GetMapping("/login")
	public String login(String chk) {
		System.out.println("chk : "+chk);
		return "member/login";  // member/login.jsp
	}
	
	@PostMapping("/login")
	public String login(String id, String pw, Model model) {
		
		// db 접근해서 id, pw가 맞는지 확인
		if(id.equals("aaa") && pw.equals("1111")) {
			session.removeAttribute("logoutChk");  // 섹션 1개삭제
			session.setAttribute("session_id", id);  // 섹션 저장
			return "redirect:/?chk=1";  // redirect 해서 /로 가기
		}else {
			return "redirect:/member/login?chk=0";
		}
		
	} //
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();  // 섹션 모두삭제
		session.setAttribute("logoutChk", 1);
		return "redirect:/?outChk=1";
	}

}
