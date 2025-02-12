package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	// 게시판 리스트 페이지
	ArrayList<BoardDto> selectAll();

	// 게시글 저장
	void insertBoard(BoardDto bdto);

	// 게시글 1개 상세보기
	BoardDto selectOne(int bno);

}
