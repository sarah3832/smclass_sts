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

	// 회원가입
	@Override
	public void save(MemberDto mdto) {
		memberRepository.save(mdto);  // 자동으로 저장 (mapper.xml 필요없음)
	}

	// 회원리스트 페이지
	@Override
	public List<MemberDto> findAll() {
		List<MemberDto> list = memberRepository.findAll();
		
		return list;
	}

	// 회원정보 상세보기
	@Override
	public MemberDto findById(String id) {
		// findById -> 검색이 없을경우, 에러처리 해야 함.
		// = select * from memberDto where id = #{id}
		// findByIdAndPw = select * from memberDto where id = #{id} and pw = #{pw}
		MemberDto memberDto = memberRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("데이터 처리시 에러");
				});
		
		return memberDto;
	}
	
	// 회원정보 삭제
	public void deleteById(String id) {
		memberRepository.deleteById(id);
	}

	// 로그인 하기
	@Override
	public MemberDto findByIdAndPw(String id, String pw) {
		MemberDto memberDto = memberRepository.findByIdAndPw(id,pw);
		return memberDto;
	}

}
