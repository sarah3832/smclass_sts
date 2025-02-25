package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.BoardDto;
import com.java.dto.CboardDto;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 전체 리스트
	// pageable page를 자동으로 계산해서 가지고 옴.
	// page를 파라미터 값으로 받아야 함.
	@GetMapping("/blist")
	public String blist(@PageableDefault(page=0,size=10) // 디폴트 - 1페이지, 한 페이지당 10개
			@SortDefaults({
				@SortDefault(sort="bgroup",direction = Sort.Direction.DESC),
				@SortDefault(sort="bstep",direction = Sort.Direction.ASC)})
			Pageable pageable, Model model) {
		// List타입이 아닌 Page객체
		Page<BoardDto> page = boardService.findAll(pageable);
		List<BoardDto> list = page.getContent();
		
		// 하단넘버링 생성
		// page정보를 정리해서 model로 전송
		// page는 0번부터 시작
		int nowpage = page.getPageable().getPageNumber()+1;
		int maxpage = page.getTotalPages();
		int startpage = ((nowpage-1)/10)*10+1;
		int endpage = startpage+10-1;
		endpage = Math.min(maxpage, endpage);
		
		// 프린트
		System.out.println("nowpage 페이지 : "+nowpage);
		System.out.println("maxpage 페이지 : "+maxpage);
		System.out.println("startpage 페이지 : "+startpage);
		System.out.println("endpage 페이지 : "+endpage);
		
		model.addAttribute("list",list);
		model.addAttribute("nowpage",nowpage);
		model.addAttribute("maxpage",maxpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		return "blist";
	}
	
	// 게시글 상세보기
	@GetMapping("/bview")
	public String bview(int bno, Model model) {
		BoardDto boardDto = boardService.findById(bno);
		List<CboardDto> clist = boardDto.getClist();
		model.addAttribute("bdto",boardDto);
		model.addAttribute("clist",clist);
		return "bview";
	}
	
	// 제목,내용 검색
	@GetMapping("/bsearch")
	public String bsearch(String search, Model model) {
		// 1. select * from boarddto where btitle = '답변';
		// 1. List<BoardDto> list = boardService.findByBtitle(search);
		
		// 2. select * from boarddto where btitle like '%답변%';
		List<BoardDto> list = boardService.findByBtitleContaining(search);  // Containing - like형태로 검색
		System.out.println("search controllelr : "+search);
		
		model.addAttribute("list",list);
		return "blist";
	}
	
	

}
