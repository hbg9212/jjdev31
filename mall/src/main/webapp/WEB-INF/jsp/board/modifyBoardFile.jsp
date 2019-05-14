<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<title>BOARD MODIFYFILE FORM(spring mvc 방식)</title>
</head>
<body>
<div class="container">
    <h1>BOARD MODIFYFILE FORM(spring mvc 방식)</h1> 
    <form id="modifyForm" action="${pageContext.request.contextPath}/board/modifyBoardFile" method="post"
    	enctype="multipart/form-data">
        <input type="hidden" name="boardNo" value="${boardFile.boardNo }">
        <input type="hidden" name="boardFileNo" value="${boardFile.boardFileNo }">
        <div class="form-group">boardFile :
			<input type="file" class="boardFile" name="boardFile">
        </div>
        <div>
            <input class="btn btn-default" id="modifyButton" type="submit" value="첨부파일수정"/>
            <input class="btn btn-default" type="reset" value="초기화"/>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/getBoardList">글목록</a>
        </div>
    </form>
</div>
</body>
</html>