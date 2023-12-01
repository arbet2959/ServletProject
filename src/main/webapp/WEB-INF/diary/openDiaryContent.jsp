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
  	
  	function etcShow() {
    	$("#complaintTxt").show();
    }
    
    // 신고화면 선택후 신고사항 전송하기
    function complaintCheck() {
    	if (!$("input[type=radio][name=complaint]:checked").is(':checked')) {
        alert('신고항목을 선택하세요');
        return false;
      }
    	if($("input[type=radio][id=complaint7]:checked").val() == 'on' && $("#complaintTxt").val() == "") {
        alert("기타 사유를 입력해 주세요.");
        return false;
    	}
    	
      let cpContent = ComplaintForm.complaint.value;
      if(cpContent == '기타') cpContent += "/" + $("#complaintTxt").val();
      
      $.ajax({
    	  url  : "diaryComplaintInput.admin",
    	  type : "post",
    	  data : {
    		  diary_Idx : ${vo.idx},
    		  cpMid   : '${sMid}',
    		  cpContent : cpContent
    	  },
    	  success:function(res) {
    		  if(res == "1") {
    			  alert("신고 되었습니다.");
    			  location.reload();
    		  }
    		  else alert('신고 실패');
    	  },
    	  error : function() {
    		  alert('전송오류!');
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
  <input type="button" value="돌아가기" onclick="location.href='openDiaryList.diary?pag=${pag}&pageSize=${pageSize}'" class="btn btn-warning"/>
  <c:if test="${!empty sMid}">
 	 <input type="button" value="친구추가" onclick="addFriend()" class="btn btn-warning"/>
  </c:if>
  <c:if test="${vo.mid != sMid}">
  	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#complaintModal">신고하기</button>
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
 </div>
 
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
 
 <!-- The Modal -->
<div class="modal fade" id="complaintModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content modal-sm">
      <!-- Modal Header -->
      <div class="modal-header">
        <h5 class="modal-title">현재 게시글을 신고합니다.</h5>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <!-- Modal body -->
      <div class="modal-body">
        <b>신고사유 선택</b>
        <hr class="m-2"/>
        <form name="ComplaintForm">
          <div class="form-check"><input type="radio" name="complaint" id="complaint1" value="욕설,비방,차별,혐오" class="form-check-input"/>욕설,비방,차별,혐오</div>
          <div class="form-check"><input type="radio" name="complaint" id="complaint2" value="홍보,영리목적" class="form-check-input"/>홍보,영리목적</div>
          <div class="form-check"><input type="radio" name="complaint" id="complaint3" value="불법정보" class="form-check-input"/>불법정보</div>
          <div class="form-check"><input type="radio" name="complaint" id="complaint4" value="음란,청소년유해" class="form-check-input"/>음란,청소년유해</div>
          <div class="form-check"><input type="radio" name="complaint" id="complaint5" value="개인정보노출,유포,거래" class="form-check-input"/>개인정보노출,유포,거래</div>
          <div class="form-check"><input type="radio" name="complaint" id="complaint6" value="도배,스팸" class="form-check-input"/>도배,스팸</div>
          <div class="form-check"><input type="radio" name="complaint" id="complaint7" value="기타" class="form-check-input" onclick="etcShow()"/>기타</div>
          <div id="etc"><textarea rows="2" name="complaintTxt" id="complaintTxt" class="form-control" style="display:none;"></textarea></div>
          <hr class="m-1"/>
          현재글 제목 : <span class="mb-2">${vo.title}</span><br/>
          신고자 아이디 : <span class="mb-2">${sMid}</span>
          <hr class="m-2"/>
          <input type="button" value="확인" onclick="complaintCheck()" class="btn btn-success form-control" />
          <input type="hidden" name="idx" id="idx" value="${vo.idx}"/>
        </form>
      </div>
      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
		</div>
	</div>
 </div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>