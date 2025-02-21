package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	HttpSession session;
	
	// 회원가입 페이지
	@GetMapping("/member")
	public String member() {
		return "member";
	}
	
	// 회원가입 하기 - insert
	@PostMapping("/member")
	public String member(MemberDto mdto) {
		memberService.save(mdto);
		
		return "redirect:/";
	}
	
	// 회원리스트 페이지
	@GetMapping("/mlist")
	public String mlist(Model model) {
		// 전체 회원리스트 - selectAll
		List<MemberDto> list = memberService.findAll();
		model.addAttribute("list",list);
		
		return "mlist";
	}
	
	// 회원정보 상세보기 
	@GetMapping("/memInfo")
	public String memInfo(String id, Model model) {
		// 회원정보 1명 - selectOne
		MemberDto memberDto = memberService.findById(id);
		model.addAttribute("mdto",memberDto);
		
		return "memInfo";
	}
	
	// 회원정보 수정 페이지 
	@GetMapping("/memUpdate")
	public String memUpdate(String id, Model model) {
		// 회원정보 1명 - selectOne
		MemberDto memberDto = memberService.findById(id);
		model.addAttribute("mdto",memberDto);
		
		return "memUpdate";
	}
	
	// 회원정보 수정 저장 
	@PostMapping("/memUpdate")
	public String memUpdate(MemberDto mdto, Model model) {
		// 회원정보 1명 - selectOne
		MemberDto memberDto = memberService.findById(mdto.getId());
		memberDto.setPw(mdto.getPw());
		memberDto.setName(mdto.getName());
		memberDto.setPhone(mdto.getPhone());
		memberDto.setGender(mdto.getGender());
		memberDto.setHobby(mdto.getHobby());
		memberDto.setMdate(memberDto.getMdate());
		memberService.save(mdto);  // id가 존재하면 update 실행, 존재하지 않으면 save 실행
		
		return "redirect:/mlist";
	}
	
	// 회원정보 삭제
	@ResponseBody  // 데이터로 넘아김
	@PostMapping("/memDelete")
	public String memDelete(String id) {
		// 회원정보 삭제
		System.out.println("id : "+id);
		memberService.deleteById(id);
		
		return "1";
	}
	
	// 로그인 열기
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	// 로그인 하기
	@PostMapping("/login")
	public String login(String id, String pw) {
		MemberDto memberDto = memberService.findByIdAndPw(id,pw); 
		if(memberDto != null) {
			System.out.println("로그인이 되었습니다.");
			session.setAttribute("session_id", id);
			return "redirect:/";
		}else {
			System.out.println("로그인이 되지 않았습니다.");
		}
		
		return "redirect:/login?loginChk=0";
	}

}
