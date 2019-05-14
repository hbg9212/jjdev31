<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>productList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</head>
<body>
<div class="container">
    <h1>카테고리별 상품리스트</h1>
    <div>전체행의 수 : ${productCount}</div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>productCommonName</th>
                <th>productCommonPrice</th>
                <th>productCommonDate</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${list}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/product/getproduct?productNo=${p.productCommonNo}">${p.productCommonName}</a></td>
                    <td>${p.productCommonPrice}</td>
                    <td>${p.productCommonDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <ul class="pager">
        <c:if test="${currentPage > 10}">
            <li class="previous"><a href="${pageContext.request.contextPath}/productList?currentPage=${currentTenPage*10}">이전10페이지</a></li>
        </c:if>
        <c:forEach var = "i" begin="${1}" end="${repetitionPage}">
			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/productList?currentPage=${currentTenPage*10+i}">${currentTenPage*10+i}</a></li>
		</c:forEach>
        <c:if test="${currentTenPage+1 < lastTenPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/productList?currentPage=${(currentTenPage+1)*10+1}">다음10페이지</a></li>
        </c:if>
    </ul>
</div>
</body>
</html>