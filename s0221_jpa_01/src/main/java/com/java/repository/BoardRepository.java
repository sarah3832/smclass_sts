package com.java.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.dto.BoardDto;

public interface BoardRepository extends JpaRepository<BoardDto, Integer>{

	// 게시글 검색
	// = select * from boarddto where btitle like '%search%'
//	@Query(value = "select * from boarddto where btitle like %:search% or bcontent like %:search%",
//	nativeQuery = true)
//	List<BoardDto> findByBtitleOrBcontentContaining(String search);  // 제목,내용
	List<BoardDto> findByBtitleContaining(String search);  // 제목
	
//	@Query(value = "select * from boarddto order by bgroup desc, bstep asc",
//			nativeQuery = true)
//	Page<BoardDto> findAll(pageable);
	
//	// Memberdto 로그인부분 쿼리
//	@Query(value = "select * from memberdto where id=? and pw=?",
//			nativeQuery = true)
//	Optional<MemberDto> findByIdAndPw(String id, String pw);

}
