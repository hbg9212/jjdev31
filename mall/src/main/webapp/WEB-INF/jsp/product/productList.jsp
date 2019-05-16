<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		$('#searchbtn').click(function(){
			console.log("addbtn click...");
			$('#searchForm').submit();
		});
	});
</script>
</head>
<body>
	<div class="row">
		<div class="col-sm-2 bg-light text-dark" >
			<jsp:include page="/WEB-INF/jsp/inc/sideMenu.jsp"></jsp:include>
		</div>
		<div class="col-sm-10" >
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
		                    <td><a href="${pageContext.request.contextPath}/product/getProduct?productCommonNo=${p.productCommonNo}&searchWord=${searchWord}">${p.productCommonName}</a></td>
		                    <td>${p.productCommonPrice}</td>
		                    <td>${p.productCommonDate}</td>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		    <div>
		    	<form id="searchForm" action="${pageContext.request.contextPath}/product/productList" method="get">
		    		<input type="hidden" name="categoryNo" value="${categoryNo}">
		    		productName 검색어 : <input type="text" name="searchWord" value="${searchWord}">
		    		<button type="button" id="searchbtn">검색</button>
		    	</form>
		    </div>
		    <ul class="pager">
		        <c:if test="${currentPage > 10}">
		            <li class="previous"><a href="${pageContext.request.contextPath}/product/productList?currentPage=${currentTenPage*10}&categoryNo=${categoryNo}">이전10페이지</a></li>
		        </c:if>
		        <c:forEach var = "i" begin="${1}" end="${repetitionPage}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/product/productList?currentPage=${currentTenPage*10+i}&categoryNo=${categoryNo}">${currentTenPage*10+i}</a></li>
				</c:forEach>
		        <c:if test="${currentTenPage+1 < lastTenPage}">
		            <li class="next"><a href="${pageContext.request.contextPath}/product/productList?currentPage=${(currentTenPage+1)*10+1}&categoryNo=${categoryNo}">다음10페이지</a></li>
		        </c:if>
		    </ul>
		</div>
	</div>
</body>
</html>