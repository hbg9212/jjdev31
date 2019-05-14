<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOARD VIEW(모델2 방식)</title>
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
    <h1>BOARD VIEW(모델2 방식)</h1>

     <table class="table">
         <tbody>
             <tr>
                <td>boardNo :</td>
                <td>${board.boardNo }</td>
               </tr>
            <tr>
                   <td>board_title :</td>
                   <td>${board.boardTitle }</td>
            </tr>
            <tr>
                   <td>board_content :</td>
                   <td>${board.boardContent }</td>
            </tr>
            <tr>
                   <td>boardUser :</td>
                   <td>${board.boardUser }</td>
            </tr>
            <tr>
                   <td>board_date :</td>
                   <td>${board.boardDate }</td>
            </tr>
        </tbody>
    </table>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${board.boardNo }">글수정</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/addBoardFile?boardNo=${board.boardNo }">첨부파일추가</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/removeBoard?boardNo=${board.boardNo }">삭제</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/getBoardList">글목록</a>
</div>
<!-- 파일리스트 -->
<div class="container">
	<h4>첨부파일</h4>
	<c:if test="${boardFileList.size() > 0}">
		<c:forEach var="f" items="${boardFileList}">
	    	<div>
	    		<a href="${pageContext.request.contextPath}/upload/${f.boardFileSaveName}.${f.boardFileExt }">${f.boardFileOriginName}.${f.boardFileExt }</a>
	    		<!-- 
	    		<a href="${pageContext.request.contextPath}/board/boardFileDown?boardFileNo=${f.boardFileNo}&boardNo=${f.boardNo}">${f.boardFileOriginName}.${f.boardFileExt }</a>
	    		 -->
	    		<a class="btn btn-default" href="${pageContext.request.contextPath}/board/modifyBoardFile?boardNo=${f.boardNo }&boardFileNo=${f.boardFileNo}">수정</a>
	    		<a class="btn btn-default" href="${pageContext.request.contextPath}/board/removeBoardFile?boardNo=${f.boardNo }&boardFileNo=${f.boardFileNo}">삭제</a>
	       	</div>
	    </c:forEach>
	</c:if>
	<c:if test="${boardFileList.size() == 0}">
		첨부파일 없음
	</c:if>
	
</div>
<!-- 댓글리스트 -->
<div class="container">
	<h4>댓글</h4>
	<c:forEach var="b" items="${boardCommentList}">
    	<div>
	    	<form action="${pageContext.request.contextPath}/board/removeBoardComment" method="post">
				<input type="hidden" name="boardNo" value="${b.boardNo }">
				<input type="hidden" name="boardCommentNo" value="${b.boardCommentNo }">
				<div>${b.boardCommentContent} &nbsp; 작성자 : ${b.boardCommentUser}</div>
				<div>
					<button type="submit">삭제</button>
				</div>
			</form>
       	</div>
    </c:forEach>	
</div>
<!-- 댓글 입력 폼-->
<div class="container">
	<form action="${pageContext.request.contextPath}/board/addBoardComment" method="post">
		<input type="hidden" name="boardNo" value="${board.boardNo }">
		<div>
			<textarea name="boardCommentContent" rows="3" cols="80" ></textarea>
		</div>
		<div>
			boardCommentUser : 
			<input type="text" name="boardCommentUser">
		</div>
		<div>
			<button type="submit">댓글입력</button>
		</div>
	</form>
</div>
</body>
</html>