<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/modifyMember</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<h3>회원정보 수정</h3>
<form method="post" action="${pageContext.request.contextPath}/member/modifyMember">
	<input type="hidden" name="memberNo" value="${member.memberNo }">
	<label>회원ID : </label>
	<input class="form-control" type="text" name="memberId" value="${member.memberId }" readonly="readonly">
	<label>회원이름 : </label>
	<input class="form-control" type="text" name="memberName" value="${member.memberName }">
	<label>회원전화번호 : </label>
	<input class="form-control" type="text" name="memberPhone" value="${member.memberPhone }">
	<label>회원주소 : </label>
	<input class="form-control" type="text" name="memberAddress" value="${member.memberAddress }">
	<label>회원성별 : </label>
	<c:if test='${member.memberGender == "M" }'>
		<select class="form-control" name="memberGender">
			<option value="M">남</option>
			<option value="F">여</option>
		</select>
	</c:if>
	<c:if test='${member.memberGender == "F" }'>
		<select class="form-control" name="memberGender">
			<option value="F">여</option>
			<option value="M">남</option>
		</select>
	</c:if>

	<label>회원등급 : </label>
	<input class="form-control" type="text" name="memberLevel" value="${member.memberLevel }" readonly="readonly">
	<br>
	<label>회원PW 확인 : </label>
	<input class="form-control" type="password" name="memberPw">
	<input class="btn btn-primary" type="submit" value="회원 정보 수정">
</form>
</body>
</html>