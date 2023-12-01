<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  	'use strict'
  	
  	function deleteFriendProc(friendId){
  		
  		let query = {		
    			friendId	: friendId
    	}
    	
    	$.ajax({
    		url  : "friendDeleteProc.friend",
    		type : "post",
    		data : query,
    		success:function(res) {
    			if(res == "1") {
    				alert("삭제완료");
    				location.reload();
    			}
    			else {
    				alert("삭제 실패");
    			}
    		},
    		error : function() {
    			alert("전송오류!!");
    		}
    	});
  	}
  	
  	
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
  
	  <h2>${sMid }님의 친구목록</h2>
  <c:forEach var="vo" items="${vos}">
	  <div>${vo.friendId }&nbsp; &nbsp; <input type="button" class="btn btn-warning" onclick="deleteFriendProc('${vo.friendId}')" value="회원삭제"></div>
  </c:forEach>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>