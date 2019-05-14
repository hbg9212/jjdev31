<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h1>ID 중복채크</h1>
			<h3>${message}</h3>
			<form method="post" action="${pageContext.request.contextPath}/member/idOverlapCheck">
				<label>회원ID : </label>
				<input class="form-control" type="text" name="memberId" value="${member.memberId }" >
				<br>
				<input class="btn btn-primary" type="submit" value="중복채크">
			</form>
		</div>
	</div>
</body>
</html>