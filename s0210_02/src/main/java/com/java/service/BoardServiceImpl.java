package com.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dao.BoardMapper;
import com.java.dto.BoardDto;

@Transactional
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	int rowPerPage = 10;  // 한 페이지당 게시글 개수

	// 게시글 전체리스트
	@Override
	public Map<String, Object> blist(int page,String category, String searchW) {
		
		int count = boardMapper.countAll(category,searchW);
		
		// 마지막 페이지 넘버링
		int maxpage = (int)Math.ceil((double)count/rowPerPage);
		// 첫번째 번호 넘버링
		int startpage = (int)((page-1)/10)*10+1;
		// 마지막 번호 넘버링
		int endpage = startpage+10-1;
		if(endpage>maxpage) endpage = maxpage;
		
		// 요청 페이지별 검색번호
		int startrow = (page-1)*rowPerPage+1;  // 4페이지 요청 -> (4-1)*10+1=31
		int endrow = startrow+rowPerPage-1;  //      		      31+10-1=40
		
		// 전체 리스트 - 시작번호,끝번호,카테고리,검색어
		ArrayList<BoardDto> list = boardMapper.selectAll(startrow,endrow,category,searchW);
		
		// 리턴타입 map
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("category", category);
		map.put("searchW", searchW);
		map.put("list", list);
		
		return map;
	}

	// 글쓰기 저장
	@Override
	public void bwrite(BoardDto bdto) {
		int result = boardMapper.insertBoard(bdto);
		System.out.println("BoardServiceImpl result : "+result);
	}

	// 게시글 상세보기
	@Override
	public Map<String, Object> bview(int bno) {  // 2개이상 있을시, map 으로보냄
		Map<String, Object> map = new HashMap<>();
		
		// 조회수 1증가 - update
		boardMapper.updateBhit(bno);
		
		// 현재게시글 정보
		BoardDto boardDto = boardMapper.selectOne(bno);
		map.put("boardDto", boardDto);
		
		// 이전게시글 정보
		BoardDto prevDto = boardMapper.selectOnePrev(bno);
//		System.out.println("prevDto : "+prevDto.getBno());
		map.put("prevDto", prevDto);
		
		// 다음게시글 정보
		BoardDto nextDto = boardMapper.selectOneNext(bno);
		map.put("nextDto", nextDto);
		
		return map;
	}

	// 게시글 삭제
	@Override
	public void bdelete(int bno) {
		boardMapper.deleteBoard(bno);
	}

	// 게시글 수정 페이지
	@Override
	public BoardDto bupdate(int bno) {
		BoardDto boardDto = boardMapper.selectOne(bno);
		return boardDto;
	}

	// 게시글 수정,저장
	@Override
	public void bupdate(BoardDto bdto) {
		boardMapper.updateBoard(bdto);
		
	}
	
	// 답변달기 페이지
	@Override
	public BoardDto breply(int bno) {
		BoardDto boardDto = boardMapper.selectOne(bno);
		return boardDto;
	}

	// 답변달기 저장
	@Override
	public void breply(BoardDto bdto) {
		// 같은 그룹에서 부모보다 bstep 높은 게시글을 모두 +1
		boardMapper.bstepUp(bdto);
		// 답변달기 저장
		boardMapper.InsertBreply(bdto);
	}

}
