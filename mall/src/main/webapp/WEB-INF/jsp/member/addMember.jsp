<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/addMember</title>
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
	<div class="row">
		<div class="col-sm-2 bg-light text-dark" >

		</div>
		<div class="col-sm-10" >
			<h1>회원추가</h1>
			<form method="post" action="${pageContext.request.contextPath}/member/addMember">
				<label>회원ID : </label>
				<input class="form-control" type="text" name="memberId" value="${member.memberId }" readonly="readonly">
				<label>회원PW : </label>
				<input class="form-control" type="password" name="memberPw">
				<label>회원이름 : </label>
				<input class="form-control" type="text" name="memberName">
				<label>회원전화번호 : </label>
				<input class="form-control" type="text" name="memberPhone">
				<label>회원주소 : </label>
				<input class="form-control" type="text" name="memberAddress">
				<label>회원이메일 : </label>
				<input class="form-control" type="text" name="memberEmail">
				<label>회원성별 : </label>
				<select class="form-control" name="memberGender">
					<option value="M">남</option>
					<option value="F">여</option>
				</select>
				<br>
				<input class="btn btn-primary" type="submit" value="회원 추가하기">
			</form>
		</div>
	</div>
</body>
</html>