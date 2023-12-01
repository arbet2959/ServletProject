<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>title</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <script>
  	'use strict';
  	function fileCheck(){
  		
  		let fName = document.getElementById("file").value;
 	  	let ext = fName.substring(fName.lastIndexOf(".")+1).toLowerCase();
 	  	let maxSize = 1024 * 1024 * 10;
 	  	if(fName.trim() == "") {
 	  		alert("업로드할 파일을 선택하세요!");
 				return false;
 			}
 	  	
 	  	let fileSize = document.getElementById("file").files[0].size;
 	  	
 	  	if(ext != 'jpg' && ext != 'gif' && ext != 'png') {
 				alert("그림파일만가능합니다.");
 				return false;
 			}
 	  	
 	  	if(fileSize > maxSize) {
 				alert("업로드할 파일의 최대용량은 10MByte입니다.");
 				return;
 			}
  	  	
 	  	myform.submit();
  		
  		
  		
  	}
  	
  	function imgCheck(e) {
    	if(e.files && e.files[0]) {
    		let reader = new FileReader();
    		reader.onload = function(e) {
    			document.getElementById("photoDemo").src = e.target.result;
    		}
    		reader.readAsDataURL(e.files[0]);
    	}
    	else {
    		document.getElementById("photoDemo").src = "";
    	}
    }
  	
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
<form name="myform" method="post" action="myDiaryUpdateProc.diary" enctype="multipart/form-data">
	<table class="table table=bordered" style="width:800">
		<tr><th>닉네임</th> <td>${vo.nickName }</td></tr>
		<tr><th>아이디</th><td> ${vo.mid }</td></tr>
		<tr><th>제목 </th>
			<td>
				<input type="text" value="${vo.title}" class="form-control" id="title" name="title" />
			</td>
		</tr>
		<tr><th>작성일</th> <td>${fn:substring(vo.wDate,0,16)}</td></tr>
  	<tr><td colspan="2"><img id ="photoDemo" src="${ctp}/images/diary/${vo.photo}"></td></tr>
  	<tr>
  		<td colspan="2">
	  		<textarea class="form-control"id="content" name="content" rows="20" cols="40">
	  			${vo.content }
	 			</textarea>
 			</td>
 		</tr>
  	<tr>
  		<th>공개</th>
  		<td>
  			<input type="radio" class="form-check-input" name="openLevel" value="0" <c:if test="${vo.openLevel == 0}">checked</c:if>/>전체공개 &nbsp;&nbsp;
  			<input type="radio" class="form-check-input" name="openLevel" value="1" <c:if test="${vo.openLevel == 1}">checked</c:if>/>친구공개	&nbsp;&nbsp;
  			<input type="radio" class="form-check-input" name="openLevel" value="2" <c:if test="${vo.openLevel == 2}">checked</c:if>/>비공개
  		</td>
  	</tr>
  	<tr>
  		<td colspan="2">
  			<div class="form-group">
		      일기 사진 :
		      <input type="file" name="fName" id="file" onchange="imgCheck(this)" class="form-control-file border"/>
    		</div>
    	</td>
  	</tr>
  	
  	<tr>
  		<td colspan="2">
  			<input type="hidden" name="idx" id="idx" value="${idx}">
  			<input type="hidden" name="photo" id="photo" value="${vo.photo}">
    	</td>
  	</tr>
  	
  </table>
  <input type="button" value="돌아가기" onclick="location.href='myDiaryContent.diary?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}'" class="btn btn-primary"/>
  <input type="button" value="수정하기" onclick="fileCheck()" class="btn btn-warning"/> &nbsp;
</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>