<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<script>
	function signIn(){
		let mid = document.getElementById("mid").value;
		let pwd = document.getElementById("pwd").value;
		
		let query = {
				mid : mid,
				pwd : pwd,
		}
		
		$.ajax({
			url  : "signIn.member",
			type : "post",
			data : query,
			success:function(res) {
				if(res=='idfault'){
					alert("아이디가 틀렸습니다.")
					return;
				}
				if(res=='pwdfault'){
					alert("비밀번호가 틀렸습니다.")
					return;
				}
				
				if(res=='1'){
					alert("환영합니다.");
					location.reload()
				}
			},
			error : function() {
				alert("전송오류!!");
			}
		});
	}
</script>

<div class="jumbotron text-center" style="margin-bottom:0; height:150px">
  <h1 style="margin:-16px">모두의 일기장</h1>
  <br/>
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="http://192.168.50.60:9090/javaProject3">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item ml-2 mr-2">
        <button type="button" class="btn btn-secondary" onclick="location.href='myDiaryList.diary'">나의일기</button>
      </li>
      <li class="nav-item ml-2 mr-2">
        <button type="button" class="btn btn-secondary" onclick="location.href=''">공유일기</button>
      </li>
      <li class="nav-item ml-2 mr-2">
        <button type="button" class="btn btn-secondary" onclick="location.href=''">친구일기</button>
      </li>
      <li class="nav-item ml-2 mr-2">
        <button type="button" class="btn btn-secondary" onclick="location.href=''">친구관리</button>
      </li>
      
      <li class="nav-item ml-2 mr-2">
        <div class="dropdown">
			    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">베스트 일기</button>
			    <div class="dropdown-menu">
			      <a class="dropdown-item" href="#">오늘의 일기</a>
			      <a class="dropdown-item" href="#">이번주의 일기</a>
			      <a class="dropdown-item" href="#">이번달의 일기</a>
			      <a class="dropdown-item" href="#">올해의 일기</a>
			      <a class="dropdown-item" href="#">분류별 일기</a>
			    </div>
			  </div>
      </li>
      <li class="nav-item ml-2 mr-2">
        <div class="dropdown">
			    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">관리자기능</button>
			    <div class="dropdown-menu">
			    	<a class="dropdown-item" href="#">신고관리</a>
			      <a class="dropdown-item" href="#">회원관리</a>
			      <a class="dropdown-item" href="#">일기관리</a>
			      <a class="dropdown-item" href="#">이벤트관리</a>
			      <a class="dropdown-item" href="#">통계</a>
			    </div>
			  </div>
      </li>
      <li class="nav-item ml-2 mr-2">
        <button type="button" class="btn btn-secondary" onclick="''">쪽지함</button>
      </li>
      <li class="nav-item ml-2 mr-2">
      	<c:if test="${empty sMid}">
          <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#SignInModal">로그인</button>
        </c:if>
        <c:if test="${!empty sMid}">
          <button type="button" class="btn btn-secondary" onclick="location.href='logout.member'">로그아웃</button>
        </c:if>
      </li>
      <li class="nav-item ml-2 mr-2">
     	 <button type="button" class="btn btn-secondary" onclick="location.href='signUp.member'">회원가입</button>
      </li>
    </ul>
  </div>
 

  <!-- The Modal -->
  <div class="modal fade" id="SignInModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">로그인</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<form action="signIn.member">
	          <div>아이디 : <input type="text" id="mid" name="mid" class="mb-2 form-control"></div>
	          <div>비밀번호 : <input type="password" id="pwd" name="pwd" class="mb-2 form-control"></div>
          </form>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" onclick="signIn()">로그인</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
  
</nav>
</body>
</html>