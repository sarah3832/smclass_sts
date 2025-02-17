package com.java.service;

import com.java.dto.MemberDto;

public interface MemberService {

	// 로그인 하기
	MemberDto login(MemberDto mdto);

	// 인증코드 이메일 발송 - text
	String sendEmail(String email);

	// 인증코드 이메일 발송 - html
	String sendEmail2(String email);

}
