package com.java.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.CboardDto;
import com.java.dto.EventDto;

@Mapper
public interface EventMapper {

	// 이벤트 페이지 불러오기
	ArrayList<EventDto> selectAll();

	// 이벤트 게시글 상세보기
	EventDto selectOne(int eno);

	// 하단댓글 가져오기
	ArrayList<CboardDto> selectAllCboard(int eno);

}
