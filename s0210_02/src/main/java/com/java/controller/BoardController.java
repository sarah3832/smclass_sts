package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 게시글 전체리스트
	@GetMapping("/board/blist")
	public String blist(Model model) {
		ArrayList<BoardDto> list = boardService.blist();
		model.addAttribute("list",list);
		return "blist";
	}
	
	// 글쓰기 페이지
	@GetMapping("/board/bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	// 글쓰기 저장
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bdto) {
		boardService.bwrite(bdto);
		return "redirect:/board/blist";  // redirect 하면 model이 안감
	}
	
	// 게시글 상세보기
	@GetMapping("/board/bview")
	public String bview(@RequestParam(defaultValue = "1") int bno, Model model) {
		// 1개 게시글 가져오기
		BoardDto boardDto = boardService.bview(bno);
		model.addAttribute("bdto",boardDto);
		return "bview";
	}
	
	// 게시글 삭제
	@GetMapping("/board/bdelete")
	public String bdelete(int bno, Model model) {
		System.out.println("boardController delete-bno : "+bno);
		boardService.bdelete(bno);
		return "redirect:/board/blist";
	}

}
