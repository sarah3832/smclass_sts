package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemDto;
import com.java.dto.StuDto;

@Controller
public class FController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/member")
	public String member() {
		return "member";
	}
	
	@PostMapping("/memInfo")
	public String memInfo(MemDto mdto, Model model ) {
		model.addAttribute("mem",mdto);
		return "memInfo";
	}
	
	@GetMapping("/stuInput")
	public String stuInput() {
		return "stuInput";
	}
	
	@PostMapping("/doStuInput")
	public String doStuInput(StuDto sdto, Model model ) {
		sdto.setTotal(sdto.getKor()+sdto.getEng()+sdto.getMath());
		sdto.setAvg(sdto.getTotal()/3.0);
		
		model.addAttribute("stu",sdto);
		return "doStuInput";
	}
}
