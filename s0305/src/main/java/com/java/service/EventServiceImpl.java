package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.CboardDto;
import com.java.dto.EventDto;
import com.java.repository.EventRepository;

@Transactional
@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	// 이벤트 페이지
	@Override
	public List<EventDto> findAll() {
		List<EventDto> list = eventRepository.findAll();
		return list;
	}

	// 이벤트 상세보기
	@Override
	public EventDto findByEno(int eno) {
		// findById만 존재
		EventDto eventDto = eventRepository.findByEno(eno);
		return eventDto;
	}

	// 이벤트 저장
	@Override
	public void eventSave(EventDto edto) {
		eventRepository.save(edto);
	}

	

}
