package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.service.BoardService;

@Controller
public class FController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

}
