package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.CboardDto;
import com.java.service.EventService;

import jakarta.servlet.http.HttpSession;

@RestController  // 데이터가 넘어가는 구조의 형태
public class ReController {
	
	@Autowired
	EventService eventService;
	@Autowired
	HttpSession session;

	// 하단댓글 저장
	@PostMapping("/event/cwrite")
	public CboardDto cwrite(CboardDto cdto) {
		System.out.println("eno : "+cdto.getEno());
		System.out.println("cpw : "+cdto.getCpw());
		System.out.println("ccontent : "+cdto.getCcontent());
		
		cdto.setId("aaa");
		//String id = (String) session.getAttribute("session_id");
		
		// 하단댓글 저장
		CboardDto cboardDto = eventService.cwrite(cdto);
		
		return cboardDto;  // 데이터를 전달함, 페이지를 오픈하는 것이 아님.
	}
}
