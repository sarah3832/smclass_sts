package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

@Mapper
public interface CustomerMapper {

	// 공지사항 리스트
	ArrayList<BoardDto> selectAll(int startrow, int endrow, String category, String searchW);

	// 현재 게시글 정보
	BoardDto selectOne(int bno);
	// 이전 게시물 정보
	BoardDto selectOneP(int bno);
	// 다음 게시글 정보
	BoardDto selectOneN(int bno);

	// 하단 넘버링
	int countAll(String category, String searchW);

}
