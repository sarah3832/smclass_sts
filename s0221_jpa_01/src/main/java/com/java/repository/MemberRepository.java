package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;

import com.java.dto.MemberDto;

// JpaRepository<MemberDto, String> : <리턴타입, primary key타입>
public interface MemberRepository extends JpaRepository<MemberDto, String>{

	// 로그인 하기
	// = select * from memberDto where id=#{id} and pw=#{pw}
	MemberDto findByIdAndPw(String id, String pw);
	
//	// Memberdto 로그인부분 쿼리
//	@Query(value = "select * from memberdto where id=? and pw=?",
//			nativeQuery = true)
//	MemberDto findByIdAndPw(String id, String pw);

=======

import com.java.dto.MemberDto;

// JpaRepository<MemberDto, String> : <리턴타입, primary key타입>
public interface MemberRepository extends JpaRepository<MemberDto, String>{

	// 로그인 하기
	// = select * from memberDto where id=#{id} and pw=#{pw}
	MemberDto findByIdAndPw(String id, String pw);
>>>>>>> branch 'master' of https://github.com/sarah3832/smclass_sts.git

}
