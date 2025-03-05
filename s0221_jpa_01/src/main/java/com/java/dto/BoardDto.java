package com.java.dto;

import java.sql.Timestamp;
<<<<<<< HEAD
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity  // jpa 연결
public class BoardDto {

//	@SequenceGenerator(
//			name="board_seq_generator", // 시퀀스 이름
//			sequenceName = "boarddto_seq", // 생성된 시퀀스 이름
//			initialValue = 1, // 시작값
//			allocationSize = 1) // 메모리를 통한 할당범위
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 시퀀스 자동생성
	private int bno;
	
	@Column(nullable = false, length = 100)
	private String btitle;
	
	@Lob // 대용량 데이터
	private String bcontent;

	@ManyToOne(fetch = FetchType.EAGER)  // 다대일[N:1] - 외래키가 있는곳에 참조를걸고 연관관계 매핑
	@JoinColumn(name="id")  // Fk가 id라고 설정
	private MemberDto memberDto;  // memberdto 테이블 primary key - 객체타입 입력불가
//	private String id;  // db에서도 id만 저장
	
	private String bgroup;
	
	@ColumnDefault("0")  // 숫자 "0" / 문자 "'남자'" (홀따움표 추가)
	private String bstep;
	@ColumnDefault("0")  
	private String bindent;
	@ColumnDefault("0") 
	private String bhit;
	
	@UpdateTimestamp  // 자동 시간입력
	private Timestamp bdate;
	
	@Column(nullable = true, length = 100)
	private String bfile;
	
	// CboardDto의 boardDto 컬럼을 넣어줌 (BoardDto boardDto 이름을 가져옴)
	// mappedBy 연관관계 주인이 아님 - FK 생성 x
	// 테이블을 조인해서 가져오는 것만 하면 됨.
	@OneToMany(mappedBy = "boardDto",fetch=FetchType.EAGER)
	private List<CboardDto> clist;

	
=======

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String id;
	private String bgroup;
	private String bstep;
	private String bindent;
	private String bhit;
	private Timestamp bdate;

>>>>>>> branch 'master' of https://github.com/sarah3832/smclass_sts.git

}
