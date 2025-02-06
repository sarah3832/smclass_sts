<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 정보</title>
		<style>
			h2{text-align: center;}
			table,th,td{border:1px solid black; border-collapse: collapse;}
			table{text-align: center; width: 800px; margin:0 auto;}
			th,td{height: 40px;}
			th{background-color: pink;}
			div{text-align: center; margin-top: 30px;}
		</style>
	</head>
	<body>
		<h2>로그인 정보</h2>
		<table>
			<colgroup>
				<col width="50%">
				<col width="50%">
			</colgroup>
		<tr>
			<th>아이디</th>
			<th>패스워드</th>
		</tr>
		<tr>
			<td>${id }</td>
			<td>${pw }</td>
		</tr>
		</table>
		<div><a href="/">메인페이지</a></div>
	
	</body>
</html>