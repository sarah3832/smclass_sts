package com.java.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.BoardDto;
import com.java.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	// 공지사항 리스트
	@GetMapping("/customer/notice")
	public String notice(@RequestParam(value = "page",defaultValue = "1") 
	int page, String category, String searchW, Model model) {
		System.out.println("category : "+category);
		System.out.println("searchW : "+searchW);
		
		// 현재날짜
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(d);
		System.out.println("현재날자 : "+today);
		
		Map<String, Object> map = customerService.notice(page,category,searchW);
		model.addAttribute("category",map.get("category"));
		model.addAttribute("searchW",map.get("searchW"));
		model.addAttribute("page",map.get("page"));
		model.addAttribute("maxpage",map.get("maxpage"));
		model.addAttribute("startpage",map.get("startpage"));
		model.addAttribute("endpage",map.get("endpage"));
		model.addAttribute("list",map.get("list"));
		model.addAttribute("today",map.get("today"));
		
		return "/customer/notice";
	}
	
	// 공지사항 게시글
	@GetMapping("/customer/nview")
	public String nview(@RequestParam(defaultValue = "1") int bno, Model model) {

		Map<String, Object> map = customerService.nview(bno);
		model.addAttribute("bdto",map.get("boardDto"));
		model.addAttribute("pdto",map.get("prevDto"));
		model.addAttribute("ndto",map.get("nextDto"));
		
		return "/customer/nview";
	}

}
