package com.java.dao;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface BoardMapper {

	// 글작성 저장
	int InsertB(BoardDto bdto);

}
