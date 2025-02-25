package com.java.dto;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CboardDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동으로 시퀀스번호 생성
	private int cno;
	
	@ManyToOne(fetch = FetchType.EAGER)  // 댓글개수 여러개, id 1개
	@JoinColumn(name="id")  // Fk 등록
	private MemberDto memberDto;
//	private String id; 
	
	@Column(nullable = true, length = 100)
	private String cpw; 
	
	@ManyToOne
	@JoinColumn(name="dno")
	private BoardDto boardDto;
//	private int bno;
	
	@Column(nullable = false, length = 2000)
	private String ccontent; 
	
	@UpdateTimestamp
	private Timestamp cdate; 

//	@OneToMany(mappedBy = "BoardDto")
//	private List<CboardDto> cboardDto;
}
