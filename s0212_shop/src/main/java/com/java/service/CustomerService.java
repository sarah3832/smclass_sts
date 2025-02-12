package com.java.service;

import java.util.ArrayList;
import java.util.Map;

import com.java.dto.BoardDto;

public interface CustomerService {

	// 공지사항 리스트
	Map<String, Object> notice(int page, String category, String searchW);

	// 공지사항 게시글
	Map<String, Object> nview(int bno);

}
