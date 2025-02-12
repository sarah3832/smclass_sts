package com.java.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 게시글 전체리스트, 검색 포함
	@GetMapping("/board/blist")
	public String blist(@RequestParam(value="page", defaultValue = "1") int page, String category, String searchW, Model model) {
		System.out.println("category : "+category);
		System.out.println("searchW : "+category);
		// 리스트 출력 - 페이지,카테고리,검색어
		Map<String, Object> map = boardService.blist(page,category,searchW);
//		model.addAttribute("map", map);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("list", map.get("list"));
		model.addAttribute("page", map.get("page"));
		model.addAttribute("maxpage", map.get("maxpage"));
		model.addAttribute("startpage", map.get("startpage"));
		model.addAttribute("endpage", map.get("endpage"));
		model.addAttribute("category", map.get("category"));
		model.addAttribute("searchW", map.get("searchW"));
		return "blist";
	}
	
	// 글쓰기 페이지
	@GetMapping("/board/bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	// 글쓰기 저장
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bdto, @RequestPart MultipartFile files) throws Exception{

		bdto.setBfile("");  // 처음엔 파일이 없는상태
		if(!files.isEmpty()) {  // 파일이 있나
			String origin = files.getOriginalFilename(); 
			long time = System.currentTimeMillis();
			String realFilename = String.format("%d_%s", time, origin);
			String url = "c:/upload/board/";
			File f = new File(url+realFilename);
			files.transferTo(f);
			bdto.setBfile(realFilename);
		}
		
		boardService.bwrite(bdto);
		return "redirect:/board/blist";  // redirect 하면 model이 안감
	}
	
	// 게시글 상세보기
	@GetMapping("/board/bview")
	public String bview(@RequestParam(defaultValue = "1") int bno,int page, Model model) {
		// 1개 게시글 가져오기
		Map<String, Object> map = boardService.bview(bno);
		model.addAttribute("bdto",map.get("boardDto"));
		model.addAttribute("pdto",map.get("prevDto"));
		model.addAttribute("ndto",map.get("nextDto"));
		model.addAttribute("page",page);
		return "bview";
	}
	
	// 게시글 삭제
	@GetMapping("/board/bdelete")
	public String bdelete(int bno, Model model) {
		System.out.println("boardController delete-bno : "+bno);
		boardService.bdelete(bno);
		return "redirect:/board/blist";
	}
	
	// 게시글 수정 페이지
	@GetMapping("/board/bupdate")
	public String bupdate(int bno, int page, Model model) {
		System.out.println("boardController bupdate-bno : "+bno);
		BoardDto boardDto =  boardService.bupdate(bno);
		model.addAttribute("bdto",boardDto);
		model.addAttribute("page",page);
		return "bupdate";
	}
	
	// 게시글 수정,저장
	@PostMapping("/board/bupdate")
	public String bupdate(BoardDto bdto, int page, Model model) {
		System.out.println("boardController bupdate-bno : "+bdto.getBno());
		boardService.bupdate(bdto);
		return "redirect:/board/blist?page="+page;
	}
	
	// 답변달기 페이지
	@GetMapping("/board/breply")
	public String breply(int bno, int page, Model model) {
		System.out.println("boardController bupdate-bno : "+bno);
		BoardDto boardDto =  boardService.breply(bno);
		model.addAttribute("bdto",boardDto);
		return "breply";
	}
	
	// 답변달기 저장
	@PostMapping("/board/breply")
	public String breply(BoardDto bdto) {
		System.out.println("boardController breply-bno : "+bdto.getBno());
		boardService.breply(bdto);
		return "redirect:/board/blist";
	}

}
