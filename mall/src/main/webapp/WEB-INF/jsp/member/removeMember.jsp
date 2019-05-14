<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/removeMember</title>
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
	<h1>회원탈퇴</h1>
	<h3>${message}</h3>
	<form method="post" action="${pageContext.request.contextPath}/member/removeMember">
		<input type="hidden" name="memberNo" value="${memberNo}">
		<label>회원PW : </label>
		<input class="form-control" type="password" name="memberPw">
		<br>
		<input class="btn btn-primary" type="submit" value="회원탈퇴">
	</form>
</body>
</html>