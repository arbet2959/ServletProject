<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memberIdCheck.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <script>
    'use strict';
    
    // 사용가능한 아이디를 부모창으로 전송후 창닫기
    function sendCheck() {
    	opener.window.document.myform.mid.value = '${mid}';
    	opener.window.document.myform.pwd.focus();
    	window.close();
    }
    
    // 아이디 재검색
    function idCheck() {
    	let mid = childForm.mid.value;
    	
    	if(mid.trim() == "") {
    		alert("아이디를 입력하세요");
    		childForm.mid.focus();
    	}
    	else {
    		childForm.submit();
    	}
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h3>아이디 체크폼</h3>
  <c:if test="${res == 1}">
    <h4><font color="blue"><b>${mid}</b></font>아이디는 사용 가능합니다.</h4>
    <p><input type="button" value="창닫기" onclick="sendCheck()"/>
  </c:if>
  <c:if test="${res != 1}">
    <h4><font color="blue"><b>${mid}</b></font>아이디는 이미 사용중인 아이디입니다.</h4>
    <form name="childForm" method="post" action="memberIdCheck.member">
      <p>
        <input type="text" name="mid">
        <input type="button" value="아이디검색" onclick="idCheck()"/>
      </p>
    </form>
  </c:if>
</div>
<p><br/></p>
</body>
</html>