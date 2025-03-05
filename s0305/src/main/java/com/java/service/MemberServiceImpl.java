package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository memberRepository;

	// 회원전체 가져오기
	@Override
	public List<MemberDto> findAll() {
		List<MemberDto> list = memberRepository.findAll();
		return list;
	}
	

	

	
	
	
}
