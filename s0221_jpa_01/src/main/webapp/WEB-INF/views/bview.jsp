<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판 상세보기</title>
		<style>
			h2{text-align: center;}
			table,th,td{border:1px solid gray; border-collapse: collapse;}
			table{text-align: center; width: 800px; margin:0 auto;}
			th,td{height: 40px;}
			th{background-color: pink;}
			div{text-align: center; margin-top: 30px;}
		</style>
	</head>
	<body>
		<h2>게시판 상세보기</h2>
		<table>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="15%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>파일</th>
				<th>조회수</th>
			</tr>
				<tr>
					<td>${bdto.bno }</td>
					<td><a href="/">${bdto.btitle }</a></td>
					<td>${bdto.memberDto.id }</td>
					<td>${bdto.bdate }</td>
					<td>${bdto.bfile }</td>
					<td>${bdto.bhit }</td>
				</tr>
			
		</table>
		<div><a href="/">메인페이지</a></div>
	
	</body>
</html>