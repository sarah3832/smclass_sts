package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {

	// 회원전체 가져오기
	List<MemberDto> findAll();

}
