package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface BoardMapper {

	void insertBoard(BoardDto bdto);

	ArrayList<BoardDto> selectAll();

	// 글 상세보기
	BoardDto selectOne(int bno);

}
