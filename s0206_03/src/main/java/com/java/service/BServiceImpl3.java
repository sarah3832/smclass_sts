package com.java.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.java.dto.StuDto;

public class BServiceImpl3 implements BService {
	
	public String slist() {
		ArrayList<StuDto> list = new ArrayList<>();
		return "stu 여러개 보냄3";
	}

}
