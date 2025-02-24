package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public List<BoardDto> findAll() {
		// Sort - 다중정렬을 사용해서 검색
//		Sort sort = Sort.by(
//				Sort.Order.desc("bgroup"),
//				Sort.Order.asc("bstep")
//				);
//		
//		List<BoardDto> list = boardRepository.findAll(sort);
		
		// navtiveQuery 사용
		List<BoardDto> list = boardRepository.findAll();
		return list;
	}

	// 게시글 1개 가져오기
	@Override
	public BoardDto findById(int bno) {
		BoardDto boardDto = boardRepository.findById(bno).orElseThrow(
				()->{  // 람다식
					return new IllegalArgumentException("데이터 처리시 에러!");
				});
		return boardDto;
	}

}
