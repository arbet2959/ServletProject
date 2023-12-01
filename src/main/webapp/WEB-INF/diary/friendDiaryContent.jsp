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
  	   
  	function goodPlus() {
    	$.ajax({
    		url  : "diaryGoodPlus.diary",
    		type : "post",
    		data : {idx : ${vo.idx}},
    		success:function(res) {
    			if(res == "0") alert('실패했습니다.');
    			//else location.reload();
    			else $("#goodDiv").load(location.href+ ' #goodDiv')
    		},
    		error : function() {
    			alert("전송 오류!!");
    		}
    	});
    }
  	
  	function goodMinus() {
    	$.ajax({
    		url  : "diaryGoodMinus.diary",
    		type : "post",
    		data : {idx : ${vo.idx}},
    		success:function(res) {
    			if(res == "0") alert('실패했습니다.');
    			//else location.reload();
    			else $("#goodDiv").load(location.href+ ' #goodDiv')
    		},
    		error : function() {
    			alert("전송 오류!!");
    		}
    	});
    }
  
  	
  	function replyDelete(idx) {
    	let ans = confirm("선택한 댓글을 삭제하시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		url  : "diaryReplyDelete.diary",
    		type : "post",
    		data : {idx : idx},
    		success:function(res) {
    			if(res == "1") {
    				alert("댓글이 삭제되었습니다.");
    				location.reload();
    			}
    			else
    				alert("삭제 실패");
    		},
    		error : function() {
    			alert("전송실패");
    		}
    	});
    }
  	
  	function replyInput(){
  		let replyContent = $("#replyContent").val();
    	if(replyContent.trim() == "") {
    		alert("댓글을 입력하세요!");
    		$("#content").focus();
    		return false;
    	}
    	let query = {
    			diaryIdx  : ${vo.idx},
    			mid				: '${sMid}',
    			nickName	: '${sNickName}',
    			replyContent		: replyContent
    	}
    	
    	$.ajax({
    		url  : "diaryReplyInput.diary",
    		type : "post",
    		data : query,
    		success:function(res) {
    			if(res == "1") {
    				alert("댓글을 작성했습니다.");
    				location.reload();
    			}
    			else {
    				alert("댓글 입력 실패");
    			}
    		},
    		error : function() {
    			alert("전송오류!!");
    		}
    	});
    }
  	
  	function showReplyForm(replyIdx){
  		$("#reply"+replyIdx).css('display','block');
  	}
  	
  	function replyRewrite(replyIdx){
  		let reWritecontent = $("#reWritecontent"+replyIdx).val();
  		let ref = $("#ref"+replyIdx).val();
  		let re_step = $("#re_step"+replyIdx).val();
  		let re_level = $("#re_level"+replyIdx).val();
  		
  		
  		
    	if(reWritecontent.trim() == "") {
    		alert("댓글을 입력하세요!");
    		$("#content").focus();
    		return false;
    	}
    	let query = {
    			idx : ${vo.idx},
    			diaryIdx  : ${vo.idx},
    			mid				: '${sMid}',
    			nickName	: '${sNickName}',
    			replyIdx	: replyIdx,
    			reWritecontent		: reWritecontent,
    			ref : ref,
    			re_step : re_step,
    			re_level : re_level
    	}
    	
    	$.ajax({
    		url  : "diaryReplyRewrite.diary",
    		type : "post",
    		data : query,
    		success:function(res) {
    			if(res == "1") {
    				alert("댓글을 작성했습니다.");
    				location.reload();
    			}
    			else {
    				alert("댓글 입력 실패");
    			}
    		},
    		error : function() {
    			alert("전송오류!!");
    		}
    	});
  	}
  	
  	function addFriend(){
  		let ans = confirm("친구추가 하시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		url  : "addfriendProc.friend",
    		type : "post",
    		data : {mid : '${vo.mid}'},
    		success:function(res) {
    			if(res == "1") {
    				alert("친구추가 되었습니다.");
    			}
    			else
    				alert("이미 친구입니다.");
    		},
    		error : function() {
    			alert("전송실패");
    		}
    	});
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
<p><br/></p>
<div class="container">
	<table style="width:800px" class="text-center">
		<tr><th>닉네임</th> <td>${vo.nickName }</td></tr>
		<tr><th>아이디</th><td> ${vo.mid }</td></tr>
		<tr><th>제목 </th> <td>${vo.title }</td></tr>
		<tr><th>작성일</th> <td>${fn:substring(vo.wDate,0,16)}</td></tr>
		<tr>
			<th>좋아요</th>
    	<td><div id="goodDiv">
    		<c:if test="${isGood==false}">
    			<font color="red"><a href="javascript:goodPlus()">❤</a></font>(${vo.good})
    		</c:if>
    		<c:if test="${isGood==true}">
    			<font color="red"><a href="javascript:goodMinus()">☆</a></font>(${vo.good})
    		</c:if>
    		</div></td>
    </tr>
  	<tr><td colspan="2"><img src="${ctp}/images/diary/${vo.photo}"></td></tr>
  	<tr><td colspan="2"><pre>${vo.content }</pre></td></tr>
  </table>
  	<input type="hidden" id="idx" name="idx" value="${vo.idx}">

  <input type="button" value="답글쓰기" onclick="location.href='openDiaryRewrite.diary?ref=${vo.ref}&re_step=${vo.re_step}&re_level=${vo.re_level}'" class="btn btn-warning"/>
  <input type="button" value="돌아가기" onclick="location.href='friendDiaryList.diary?pag=${pag}&pageSize=${pageSize}'" class="btn btn-warning"/>
  <c:if test="${!empty sMid}">
 	 <input type="button" value="친구추가" onclick="addFriend()" class="btn btn-warning"/>
  </c:if>
</div>
<p><br/></p>
<div class="container replyContainer">
	<!-- 댓글 리스트 보여주기 -->
  <c:forEach var="replyVO" items="${replyVOs}" varStatus="st">
  <br>
	  <c:forEach var="i" begin="2" end="${replyVO.re_step}">
	      	&nbsp; &nbsp; &nbsp; 
	    </c:forEach>
	  <div onclick="showReplyForm(${replyVO.idx})" style="background-color:lightgray; display:inline-block; width:800px">
	    
	    <div style="background-color : black; height : 5px"></div>
	    <br>
	    <p> 닉네임 : ${replyVO.nickName}</p>
	    <p class="text-left"> 내용 : ${fn:replace(replyVO.content,newLine,"<br/>")}</p>
	    
	    <c:if test="${replyVO.mid == sMid || sLevel == 0}">
	    	<input type="button" value="댓글삭제" onclick="replyDelete(${replyVO.idx})" class="btn btn-info btn-sm"/>
	    </c:if>
	    <input type="hidden" name="ref${replyVO.idx}" id="ref${replyVO.idx}" value="${replyVO.ref}">
	    <input type="hidden" name="re_step${replyVO.idx}" id="re_step${replyVO.idx}" value="${replyVO.re_step}">
	    <input type="hidden" name="re_level${replyVO.idx}" id="re_level${replyVO.idx}" value="${replyVO.re_level}">
	    <br><br>
		</div>
		<div id="reply${replyVO.idx}" style="display:none">
			<form name="replyForm${replyVO.idx }">
		    <table class="table table-center">
		      <tr>
		        <td style="width:85%" class="text-left">
		          글내용 :
		          <textarea rows="4" name="reWritecontent" id="reWritecontent${replyVO.idx}" class="form-control"></textarea>
		        </td>
		        <td style="width:15%">
		          <br/>
		          <p style="font-size:13px">작성자 : ${sNickName}</p>
		          <p><input type="button" value="대댓글달기" onclick="replyRewrite(${replyVO.idx})" class="btn btn-warning btn-sm"/></p>
		          <br>
		        </td>
		      </tr>
		    </table>
		  </form>
		</div>
  </c:forEach>
  
  <!-- 블록페이지 시작. 한페이지 기본은 5개 -->
<br/>
<div class="text-center">
  <ul class="pagination justify-content-center">
    <c:if test="${replyPag > 1}"><li class="page-item"><a class="page-link text-secondary" href="myDiaryContent.diary?idx=${vo.idx}&replyPag=1&replyPageSize=${replyPageSize}&pag=${pag}&pageSize=${pageSize}">첫페이지</a></li></c:if>
  	<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="myDiaryContent.diary?idx=${vo.idx}&replyPag=${(curBlock-1)*blockSize+1}&replyPageSize=${replyPageSize}&pag=${pag}&pageSize=${pageSize}">이전블록</a></li></c:if>
  	<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">
	    <c:if test="${i <= totPage && i == replyPag}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="myDiaryContent.diary?idx=${vo.idx}&replyPag=${i}&replyPageSize=${replyPageSize}&pag=${pag}&pageSize=${pageSize}">${i}</a></li></c:if>
	    <c:if test="${i <= totPage && i != replyPag}"><li class="page-item"><a class="page-link text-secondary" href="myDiaryContent.diary?idx=${vo.idx}&replyPag=${i}&replyPageSize=${replyPageSize}&pag=${pag}&pageSize=${pageSize}">${i}</a></li></c:if>
  	</c:forEach>
  	<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="myDiaryContent.diary?idx=${vo.idx}&replyPag=${(curBlock+1)*blockSize+1}&replyPageSize=${replyPageSize}&pag=${pag}&pageSize=${pageSize}">다음블록</a></li></c:if>
  	<c:if test="${replyPag < totPage}"><li class="page-item"><a class="page-link text-secondary" href="myDiaryContent.diary?idx=${vo.idx}&replyPag=${totPage}&replyPageSize=${replyPageSize}&pag=${pag}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
  </ul>
</div>
<!-- 블록페이지 끝 -->
  
  
  
  <!-- 댓글 입력창 -->
  <form name="replyForm">
    <table class="table table-center">
      <tr>
        <td style="width:85%" class="text-left">
          글내용 :
          <textarea rows="4" name="replyContent" id="replyContent" class="form-control"></textarea>
        </td>
        <td style="width:15%">
          <br/>
          <p style="font-size:13px">작성자 : ${sNickName}</p>
          <p><input type="button" value="댓글달기" onclick="replyInput()" class="btn btn-info btn-sm"/></p>
        </td>
      </tr>
    </table>
  </form>
</div>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>