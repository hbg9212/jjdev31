<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getProduct</title>
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
			<jsp:include page="/WEB-INF/jsp/inc/sideMenu.jsp"></jsp:include>
		</div>
		<div class="col-sm-10" >
			<div>
				<table class="table table-striped">
			        <thead>
			            <tr>
			                <th>productCommonName</th>
			                <th>productCommonPrice</th>
			                <th>productCommonDate</th>
			            </tr>
			        </thead>
			        <tbody>
		                <tr>
		                    <td>${productCommon.productCommonName}</td>
		                    <td>${productCommon.productCommonPrice}</td>
		                    <td>${productCommon.productCommonDate}</td>
		                </tr>
			        </tbody>
			    </table>
			</div>
			<div>
				<table class="table table-striped">
			        <thead>
			            <tr>
			                <th>productColor</th>
			                <th>productSize</th>
			                <th>productStock</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="p" items="${list}">
			                <tr>
			                    <td>${p.productColor}</td>
			                    <td>${p.productSize}</td>
			                    <td>${p.productStock}</td>
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
			</div>
		</div>
	</div>
</body>
</html>