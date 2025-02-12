package com.java.service;

import java.util.ArrayList;

import com.java.dto.BoardDto;

public interface BoardService {

	// 게시판 리스트 페이지
	ArrayList<BoardDto> blist();

	// 게시글 저장
	void bwrite(BoardDto bdto);

	// 게시글 1개 상세보기
	BoardDto bview(int bno);

}
