package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardMapper;
import com.java.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	// 글작성 저장
	@Override
	public void bwrite(BoardDto bdto) {
		int result = boardMapper.InsertB(bdto);
	}

}
