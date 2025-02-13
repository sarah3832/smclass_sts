package com.java.service;

import java.util.ArrayList;

import com.java.dto.CboardDto;
import com.java.dto.EventDto;

public interface EventService {

	// 이벤트 페이지
	ArrayList<EventDto> event();

	// 이벤트 상세보기
	EventDto eview(int eno);

	// 하단댓글 가져오기
	ArrayList<CboardDto> clist(int eno);

	// 하단댓글 저장
	CboardDto cwrite(CboardDto cdto);

}
