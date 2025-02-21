<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보 수정</title>
	</head>
	<body>
		<h2>회원정보 수정</h2>
		<form action="memUpdate" method="post">
			<label>아이디</label>
			<input type="text" name="id" value="${mdto.id }" readonly><br/>
			<label>패스워드</label>
			<input type="text" name="pw" value="${mdto.pw }"><br/>
			<label>이름</label>
			<input type="text" name="name" value="${mdto.name }"><br/>
			<label>전화번호</label>
			<input type="text" name="phone" value="${mdto.phone }"><br/>
			<label>성별</label><br/>
			<input type="radio" name="gender" id="man" value="남자" ${ (fn:contains(mdto.gender,'남자'))?'checked':'' }>
			<label for="man">남자</label>
			<input type="radio" name="gender" id="woman" value="여자" ${ (fn:contains(mdto.gender,'여자'))?'checked':'' }>
			<label for="woman">여자</label>
			<br/>
			<label>취미</label><br/>
			<input type="checkbox" name="hobby" id="game" value="게임" ${ (fn:contains(mdto.hobby,'게임'))?'checked':'' }>
			<label for="game">게임</label>
			<input type="checkbox" name="hobby" id="golf" value="골프" ${ (fn:contains(mdto.hobby,'골프'))?'checked':'' }>
			<label for="golf">골프</label>
			<input type="checkbox" name="hobby" id="swim" value="수영" ${ (fn:contains(mdto.hobby,'수영'))?'checked':'' }>
			<label for="swim">수영</label>
			<input type="checkbox" name="hobby" id="run" value="조깅" ${ (fn:contains(mdto.hobby,'조깅'))?'checked':'' }>
			<label for="run">조깅</label>
			<input type="checkbox" name="hobby" id="book" value="독서" ${ (fn:contains(mdto.hobby,'독서'))?'checked':'' }>
			<label for="book">독서</label>
			<br/>
			<input type="submit" value="수정하기">
		</form>
		<div><a href="/">메인페이지</a></div>
	
	</body>
</html>