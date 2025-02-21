package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.MemberDto;

// JpaRepository<MemberDto, String> : <리턴타입, primary key타입>
public interface MemberRepository extends JpaRepository<MemberDto, String>{

	// 로그인 하기
	// = select * from memberDto where id=#{id} and pw=#{pw}
	MemberDto findByIdAndPw(String id, String pw);

}
