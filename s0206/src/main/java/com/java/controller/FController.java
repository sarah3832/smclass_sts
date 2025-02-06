package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	
	@Autowired HttpSession session;
	
	@GetMapping("/")
	public String index() {
		System.out.println("index페이지가 열립니다.");
		return "index";
	}
	
	// 가입회원 정보
	@GetMapping("/memInfo")
	public String memInfo() {
		return "memInfo";
	}
	
	// 회원가입 페이지
	@GetMapping("/member")
	public String member() {
		return "member";
	}
	
	@PostMapping("/member")
	public ModelAndView member(HttpServletRequest requset) {
		String id = requset.getParameter("id");
		String pw = requset.getParameter("pw");
		String name = requset.getParameter("name");
		String tel = requset.getParameter("tel");
		String gender = requset.getParameter("gender");
		String[] hobbys = requset.getParameterValues("hobby");
		String hobby = "";  // ex) "게임,골프,수영"
		for(int i=0;i<hobbys.length;i++) {
			if(i==0) hobby += hobbys[i];
			else hobby += ","+hobbys[i];
		}
		// model 사용
//		model.addAttribute("member",new MemberDto(id,pw,name,tel,gender,hobby));
		
		// ModelAndView 사용
		ModelAndView mv = new ModelAndView();
		mv.setViewName("memInfo");
		mv.addObject("member", new MemberDto(id,pw,name,tel,gender,hobby));
		return mv;
	}
		
	@GetMapping("/login")
	public String login() {
		System.out.println("login get방식.");
		return "login";
	}
	
	@PostMapping("/login")  // 데이터를 입력받을 때에는 @PostMapping
	public String login(HttpServletRequest requset, Model model) {
		String id = requset.getParameter("id");
		String pw = requset.getParameter("pw");
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		
		session.setAttribute("session_id", id);
		return "loginInfo";
	}
	
	@RequestMapping("/logout")  // @GetMapping, @PostMapping 2개 모두사용
	public String logout() {
		session.invalidate();  // 섹션 없애기
		return "logout";
	}

}
