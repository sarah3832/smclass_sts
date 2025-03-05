package com.java.service;

import java.util.ArrayList;
import java.util.List;

import com.java.dto.CboardDto;
import com.java.dto.EventDto;

public interface EventService {

	// 이벤트 페이지
	List<EventDto> findAll();

	// 이벤트 상세보기
	EventDto findByEno(int eno);

	// 이벤트 저장
	void eventSave(EventDto edto);



}
