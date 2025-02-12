package com.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CustomerMapper;
import com.java.dto.BoardDto;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerMapper customerMapper;
	int rowPerPage = 10;

	// 공지사항
	@Override
	public Map<String, Object> notice(int page, String category, String searchW) {
		
		int count = customerMapper.countAll(category,searchW);
		
		// 마지막 페이지 넘버링
		int maxpage = (int)Math.ceil((double)count/rowPerPage);
		// 첫번째 번호 넘버링
		int startpage = (int)((page-1)/10)*10+1;
		// 마지막 번호 넘버링
		int endpage = startpage+10-1;
		if(endpage>maxpage) endpage = maxpage;
		
		// 요청 페이지별 검색번호
		int startrow = (page-1)*rowPerPage+1;
		int endrow = startrow+rowPerPage-1;
		
		// 전체 리스트
		ArrayList<BoardDto> list = customerMapper.selectAll(startrow,endrow,category,searchW);
		// 리턴타입 map
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("category", category);
		map.put("list", list);
		
		return map;
	}

	// 공지사항 게시글
	@Override
	public Map<String, Object> nview(int bno) {
		Map<String, Object> map = new HashMap<>();
		// 현재 게시글 정보
		BoardDto boardDto = customerMapper.selectOne(bno);
		map.put("boardDto", boardDto);
		// 이전 게시글 정보
		BoardDto prevDto = customerMapper.selectOneP(bno);
		map.put("prevDto", prevDto);
		// 다음 게시글 정보
		BoardDto nextDto = customerMapper.selectOneN(bno);
		map.put("nextDto", nextDto);
		
		return map;
	}

}
