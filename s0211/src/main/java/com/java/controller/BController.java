package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BController {
	
	@Autowired
	BoardService boardService;
	
	// 게시판 리스트
	@GetMapping("/board/blist")
	public String blist() {
		return "blist";
	}
	
	// 글작성 페이지
	@GetMapping("/board/bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	// 글작성 저장
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bdto) {
		boardService.bwrite(bdto);
		return "redirect:/board/blist";
	}

}
