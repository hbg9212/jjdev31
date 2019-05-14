<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/myInformation</title>
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
	<div class="col-sm-10" >
		<h1>내정보</h1>
		<div>회원ID : ${member.memberId }</div>
		<div>회원이름 : ${member.memberName }</div>
		<div>회원전화번호 : ${member.memberPhone }</div>
		<div>회원주소 : ${member.memberAddress }</div>
		<div>회원성별 : ${member.memberGender }</div>
		<div>회원등급 : ${member.memberLevel }</div>
		<br>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/">홈으로</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/member/modifyMember?memberNo=${memberNo}">회원정보 수정</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/member/modifyMemberPw?memberNo=${memberNo}">비밀번호 수정</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/member/removeMember?memberNo=${memberNo}">회원탈퇴</a>
	</div>
</body>
</html>