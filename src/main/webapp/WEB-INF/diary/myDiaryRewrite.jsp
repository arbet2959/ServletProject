<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>boardInput.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <style>
    th {
      text-align: center;
      background-color: #eee;
    }
  </style>
  
  <script>
  	'use strict'
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
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2 class="text-center">일 기 쓰 기</h2>
  <form name="myform" method="post" action="myDiaryReWriteProc.diary" enctype="multipart/form-data">
    <table class="table table-bordered" style="width:800">
      <tr>
        <th>닉네임</th>
        <td>${sNickName}</td>
      </tr>
      <tr>
        <th>일기제목</th>
        <td><input type="text" name="title" id="title" placeholder="제목을 입력하세요" autofocus required class="form-control"/></td>
      </tr>
      <tr>
        <th>오늘의사진</th>
         <td>
         <input type="file" name="fName" id="file" onchange="imgCheck(this)" class="form-control-file border mb-2"/>
      	</td>
      </tr>
      <tr>
      	<td colspan="2">
      		<div><img id="photoDemo"/></div>
      	</td>
      </tr>
      <tr>
        <th>내용</th>
        <td><textarea rows="6" name="content" id="content" class="form-control" rows="20" cols="40" required></textarea></td>
      </tr>
      <tr>
        <th>공개여부</th>
        <td>
          <input type="radio" name="openLevel" value="0" checked />전체공개 &nbsp;
          <input type="radio" name="openLevel" value="1" checked />친구공개 &nbsp;
          <input type="radio" name="openLevel" value="2" />비공개
        </td>
      </tr>
      <tr>
        <td colspan="2" class="text-center">
          <input type="button" value="답글쓰기" onclick="fileCheck()"class="btn btn-success" /> &nbsp;
          <input type="reset" value="다시입력" class="btn btn-warning" /> &nbsp;
          <input type="button" onclick="location.href='myDiaryList.diary';" value="돌아가기" class="btn btn-info" />
        </td>
      </tr>
    </table>
    <input type="hidden" name="hostIp" value="${pageContext.request.remoteAddr}" />
    <input type="hidden" name="ref" id="ref" value="${ref}">
		<input type="hidden" name="re_step" id="re_step" value="${re_step}">
		<input type="hidden" name="re_level" id="re_level" value="${re_level}">
		</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>