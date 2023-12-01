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
  	'use strict'
  	function deleteDiary(diary_Idx){
  		return;
  		let query = {
  	  			idx : diary_Idx,
  				}
  			
 				$.ajax({
 					url  : "myDiaryDeleteProc.diary",
 					type : "post",
 					data : query,
 					success:function(res) {
 						 						
 						if(res=='1'){
 							alert("삭제완료");
 							location.reload()
 						}
 						else{
 							alert("삭제실패")
 						}
 					},
 					error : function() {
 						alert("전송오류!!");
 					}
 				});
  		
  		
  	}
  	
  	function deleteComplaint(idx){
  		let query = {
  			idx : idx,
			}
		
			$.ajax({
				url  : "admindeleteComplaintProc.admin",
				type : "post",
				data : query,
				success:function(res) {
					
					if(res=='1'){
						alert("처리완료");
						location.reload()
					}
					else{
						alert("실패했습니다.");
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
  <h2 class="text-center">신 고 리 스 트</h2>
  <table class="table table-hover text-center">
    <tr class="table-dark text-dark">
      <th>번호</th>
      <th>글제목</th>
      <th>글쓴이</th>
      <th>신고자</th>
      <th>신고내역</th>
      <th>처리</th>
    </tr>
    <c:set var="complaintCnt" value="${fn:length(vos)}"/>
    <c:forEach var="vo" items="${vos}" varStatus="st">
      <tr>
        <td>${complaintCnt}</td>
        <td>${vo.title}</td>
        <td>${vo.name}</td>
        <td>${vo.cpMid}</td>
        <td>${vo.cpContent}</td>
        <td><input type="button" value="글삭제" onclick="deleteDiary(${vo.diary_Idx})"></td>
        <td><input type="button" value="처리완료" onclick="deleteComplaint(${vo.idx})"></td>
      </tr>
      <c:set var="complaintCnt" value="${complaintCnt - 1}"/>
    </c:forEach>
    <tr><td colspan="5" class="m-0 p-0"></td></tr>
  </table>
  
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>