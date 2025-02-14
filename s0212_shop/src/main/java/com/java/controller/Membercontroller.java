package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Membercontroller {
	
	@Autowired
	HttpSession session;
	@Autowired
	MemberService memberSerive;
	
	// 로그인 페이지 열기
	@GetMapping("/member/login")
	public String login() {
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
	

	
	
}
