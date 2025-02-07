package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/mlist")
	public String mlist(Model model) {
		ArrayList<MemberDto> list = memberService.mlist();
		model.addAttribute("list",list);
		return "/member/mlist";
	}

}
