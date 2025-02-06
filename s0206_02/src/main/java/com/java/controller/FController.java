package com.java.controller;

import java.lang.reflect.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemDto;
import com.java.dto.StuDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board")
	public String board(int bno, String btitle, Model model) {
		System.out.println("bno : "+bno);
		System.out.println("btitle : "+btitle);

		model.addAttribute("bno",bno);
		model.addAttribute("btitle",btitle);
		return "board";
	}
	
	@GetMapping("/stuInput")
	public String stuInput() {
		return "stuInput";
	}
	
	@PostMapping("/stuInput")
	public String stuInput(int stuNo, String name, int kor, int eng, int math, Model model) {
		int total = kor+eng+math;
		double avg = total/3.0;
		
//		model.addAttribute("stuNo",stuNo);
//		model.addAttribute("name",name);
//		model.addAttribute("kor",kor);
//		model.addAttribute("eng",eng);
//		model.addAttribute("math",math);
//		model.addAttribute("total",total);
//		model.addAttribute("avg",avg);
		
		StuDto s = StuDto.builder().stuNo(stuNo).name(name).kor(kor).eng(eng).math(math).total(total).avg(avg).build();
		model.addAttribute("stu",s);
		
		return "doStuInput";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request,
			@RequestParam("id") String id,  // form name = "id" 데이터 입력받음
			@RequestParam(required = false) String pw,  // required = false : null값 허용
//			String name,  // @RequestParam 생략가능, form 이름과 같아야 함.
			@RequestParam int kor,
			@RequestParam(defaultValue = "0")int eng,  // defaultValue = "0" : 값이 없으면 0으로 세팅
			int math,
			Model model) {
		
		int total = kor+eng+math;
		double avg = total/3.0;
		
//		String aid = request.getParameter("id");
//		String apw = request.getParameter("pw");
//		int akor = Integer.parseInt(request.getParameter("kor"));
//		int aeng = Integer.parseInt(request.getParameter("eng"));
//		int amath = Integer.parseInt(request.getParameter("math"));
//		int total = kor+eng+math;
//		double avg = total/3.0;
//		System.out.println("데이터 : "+id+","+pw);
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		model.addAttribute("kor",kor);
		model.addAttribute("eng",eng);
		model.addAttribute("math",math);
		model.addAttribute("total",total);
		model.addAttribute("avg",avg);
		model.addAttribute("member",MemDto.builder().id(id).pw(pw).build());  // 부분적으로 넣는법
		
		MemDto m = new MemDto();
		MemDto m1 = MemDto.builder().id("bbb").pw("1111").build();
		return "doLogin";
		
	}

}
