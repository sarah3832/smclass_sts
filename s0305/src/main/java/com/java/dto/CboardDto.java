package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CboardDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스 사용
	private int cno;
	
	@ManyToOne // default EAGER 전략
	@JoinColumn(name="eno")  // CboardDtoEno
	private EventDto eventDto;
	
	@ManyToOne
	@JoinColumn(name="id")  // CboardDtoID
	private MemberDto memberDto;
	
	@Column(nullable = true, length = 100)
	private String cpw; 
	
	@Column(length = 1000)
	private String ccontent;
	
	@UpdateTimestamp
	private Timestamp cdate; 

}
