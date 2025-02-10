package com.java.service;

import java.util.ArrayList;

import com.java.dto.BoardDto;

public interface BoardService {

	// 게시글 전체리스트
	ArrayList<BoardDto> blist();

	// 글쓰기 저장
	void bwrite(BoardDto bdto);

	// 게시글 상세보기
	BoardDto bview(int bno);

	// 게시글 삭제
	void bdelete(int bno);


}
