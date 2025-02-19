package com.java.service;

import java.util.ArrayList;

import com.java.dto.BoardDto;

public interface BoardService {

	void bwrite(BoardDto bdto);

	ArrayList<BoardDto> blist();

	// 글 상세보기
	BoardDto bveiw(int bno);

}
