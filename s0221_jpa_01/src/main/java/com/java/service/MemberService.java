package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {

	// 회원가입
	void save(MemberDto mdto);

	// 회원리스트 페이지
	List<MemberDto> findAll();

	// 회원정보 상세보기
	MemberDto findById(String id);

	// 회원정보 삭제
	void deleteById(String id);

	// 로그인 하기
	MemberDto findByIdAndPw(String id, String pw);

}
