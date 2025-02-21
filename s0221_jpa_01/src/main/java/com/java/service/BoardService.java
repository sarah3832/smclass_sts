package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {
	
	List<BoardDto> findAll();

	// 게시글 1개 가져오기
	BoardDto findById(int bno);

}
