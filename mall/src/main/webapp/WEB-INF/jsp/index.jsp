<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<h1>INDEX</h1>
	<h3>쇼핑몰 메인 페이지</h3>
	<c:if test="${memberId != null}">
		<div>${memberLevel} ${memberName}님 안녕하세요</div>
	</c:if>
	<ol>
		<c:if test="${memberId == null}">
			<li><a class="btn btn-primary" href="/member/login">로그인</a></li>
			<li><a class="btn btn-primary" href="/member/idOverlapCheck">회원가입</a></li>
			<li><a class="btn btn-primary" href="/member/searchId">아이디 찾기</a></li>
			<li><a class="btn btn-primary" href="/member/searchPw">비번 찾기</a></li>
		</c:if>
		<c:if test="${memberId != null}">
			<li><a class="btn btn-primary" href="/member/myInformation?memberNo=${memberNo}">내정보</a></li>
			<li><a class="btn btn-primary" href="/member/logout">로그아웃</a></li>
		</c:if>			
	</ol>
	<ol>
	<c:forEach var="category" items="${category}">
		<li><a class="btn btn-primary" href="/product/productList?categoryNo=${category.categoryNo}">${category.categoryName}</a></li>
	</c:forEach>
	</ol>
</body>
</html>