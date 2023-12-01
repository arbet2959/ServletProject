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
  
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
  <h2>${vo.mid } 회원 상세 정보</h2>
  <br>
  <table>
	  <tr><td>아이디 : ${vo.mid }</td></tr>
	  <tr><td>별명 : ${vo.nickName }</td></tr>
	  <tr><td>성명 : ${vo.name }</td></tr>
	  <tr><td>생일 : ${vo.birthday }</td></tr>
	  <tr><td>전화번호 : ${vo.tel }</td></tr>
	  <tr><td>주소 : ${vo.address }</td></tr>
	  <tr><td>이메일 : ${vo.email }</td></tr>
	  <tr><td>자기소개 : ${vo.content }</td></tr>
	  <tr><td>
	 		삭제신청 : <c:if test="${vo.level }==-1"> 삭제신청중</c:if>
	  </td></tr>
	  <tr><td>마지막접속일 : ${vo.lastDate }</td></tr>
  </table>
  <br><br>
  <div><a href="adminMemberList.admin?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}">돌아가기</a></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>