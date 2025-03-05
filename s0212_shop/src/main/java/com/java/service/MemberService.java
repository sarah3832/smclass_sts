package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {

	// 로그인 하기
	MemberDto login(MemberDto mdto);

	// 인증코드 이메일 발송 - text
	String sendEmail(String email);

	// 인증코드 이메일 발송 - html
	String sendEmail2(String email);

	// 리액트 회원전체 가져오기
	List<MemberDto> memberList();

	// 리액트 회원1명 가져오기
	MemberDto memberView(String id);

}
