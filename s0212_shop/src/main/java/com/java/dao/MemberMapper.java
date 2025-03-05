package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.MemberDto;

@Mapper
public interface MemberMapper {

	// 로그인 하기
	MemberDto selectLogin(MemberDto mdto);

	// 리액트 회원전체 가져오기
	List<MemberDto> selectAll();

	// 리액트 회원1명 가져오기
	MemberDto selectOne(String id);

}
