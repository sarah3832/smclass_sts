package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemDto;
import com.java.dto.StuDto;
import com.java.service.BService;
import com.java.service.BServiceImpl;
import com.java.service.BServiceImpl2;
import com.java.service.BServiceImpl3;
import com.java.service.BServiceImpl4;

@Controller
public class FController {
	
	// 객체선언
	@Autowired BService b;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/data")
	public String data(@RequestParam(defaultValue = "1") int bno, Model model) {
		System.out.println("bno : "+bno);
		model.addAttribute("bno",bno);
		return "data";
	}
	
	@GetMapping("/data2/{bno}")  // @PathVariable 방식
	public String data2(@PathVariable int bno, Model model) {
		System.out.println("bno : "+bno);
		model.addAttribute("bno",bno);
		return "data2";
	}
	
	@GetMapping("/data3/{bno}")  // @PathVariable 방식 - 여러개 받음(List<Integer>)
	public String data3(@PathVariable List<Integer> bno, Model model) {
		System.out.println("bno : "+bno);
		model.addAttribute("bno",bno);
		return "data3";
	}
	
	@GetMapping("/data4")  
	public String data4() {
		
		String str = b.slist();
		System.out.println("str : "+str);
		return "data4";
	}
	
	@GetMapping("/stuInput")
	public String stuInput() {
		return "stuInput";
	}
	
	@PostMapping("/doStuInput")
	public String doStuInput(StuDto sdto, Model model) {
		sdto.setTotal(sdto.getKor()+sdto.getEng()+sdto.getMath());
		sdto.setAvg(sdto.getTotal()/3.0);
				
		model.addAttribute("stu",sdto);
		return "doStuInput";
	}
	
	@GetMapping("/member")
	public String member() {
		return "member";
	}
	
	@PostMapping("/memInfo")
	public String memInfo(MemDto mdto, Model model) {
		System.out.println("mDto id : "+mdto.getId());
		System.out.println("mDto hobby : "+mdto.getHobby());
		
		model.addAttribute("member",mdto);
		return "memInfo";
	}
	
//	@PostMapping("/member")
//	public String memInfo(String id, String pw, String name, String tel, String gender, String hobby, Model model) {
//		MemDto m = MemDto.builder().id(id).pw(pw).name(name).tel(tel).gender(gender).hobby(hobby).build();
//		model.addAttribute("mem",m);
//		return "memInfo";
//	}

}
