package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public Page<BoardDto> findAll(Pageable pageable) {
		// Sort - 다중정렬을 사용해서 검색
//		Sort sort = Sort.by(
//				Sort.Order.desc("bgroup"),
//				Sort.Order.asc("bstep")
//				);
//		
//		List<BoardDto> list = boardRepository.findAll(sort);
		
		// navtiveQuery 사용
		Page<BoardDto> list = boardRepository.findAll(pageable);
		return list;
	}

	// 게시글 1개 가져오기
	@Override
	public BoardDto findById(int bno) {
		BoardDto boardDto = boardRepository.findById(bno).orElseThrow(
				()->{  // 람다식
					return new IllegalArgumentException("데이터 처리시 에러!");
				});
		boardDto.setBhit(boardDto.getBhit()+1);  // 게시글 조회수 1증가
//		boardRepository.save(boardDto);  // @Transactional 걸어서 save 생략 가능
		
		return boardDto;
	}

	// 게시글 검색
	@Override
	public List<BoardDto> findByBtitleContaining(String search) {
		List<BoardDto> list = boardRepository.findByBtitleContaining(search);
		
		return list;
	}

}
