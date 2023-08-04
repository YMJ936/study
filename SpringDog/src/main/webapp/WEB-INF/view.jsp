<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="../resources/css/css.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<style>
#containerDiv{
	width:1001px;
	margin:0 auto;
}


.board-post{
width:1000px;
}

.board-post tr {
border-bottom:1px solid #CDCDCD;
}

#board-post-content{
	white-space:pre-line;
	padding:30px;
	width:800px;
	word-wrap:break-word;
}

.board-post-option{
height:30px;
}

.board-post-option > td{
font-weight:bold;
}

.board-post-option > td > span {
font-weight:normal;
margin-left:10px;
}

.board_reply{
	margin-left:auto;
	margin-right:auto;
	width:1000px;
	border-top:1px solid black;
	border-bottom:1px solid black;
	margin-bottom:10px;
}

.board_reply_option{
background:#a0a0a0;
}

.board_reply_option > td {
font-weight:bold;
}

.board_reply_option > td > span {
font-weight:normal;
margin-left:10px;
}

#board_reply_content{
	white-space:pre-line;
	padding:10px;
	width:700px;
	word-wrap:break-word;
	
}

</style>

<script>
	$(function(){
		$("#header").load("../template/head.jsp");
		$("#footer").load("../template/footer.jsp");
	})
</script>
</head>
<body>
	<!-- Header -->
	<table border="solid 1px white" cellpadding="0" cellspacing="0" color="#a0a0a0" align="center" width="1536">
		<tbody><tr valign="top" height="150">
			<td>
				<div id="header">


<meta charset="UTF-8">
<title>Insert title here</title>





<meta charset="UTF-8">
		<title>header</title>
		
		    
		  <nav class="navbar navbar-expand-lg bg-light pb-3">
		<div class="container">
			<a class="navbar-brand" href="main.jsp "><img src="img/logo2.png" width="200" alt="..."></a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="WalkingStatusNothing.jsp" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 산책매칭 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="matching.jsp">산책자 찾기</a></li>
							<li><a class="dropdown-item" href="matching.jsp">강아지 찾기</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="WalkingStatusOngoing.jsp ">산책현황</a></li>
							<li><a class="dropdown-item" href="ReservationCheck.jsp">예약확인</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" href="#">채팅</a></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 커뮤니티 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="list.jsp ">공지사항</a></li>
							<li><a class="dropdown-item" href="list2.jsp">Q&amp;A</a></li>
							<li><a class="dropdown-item" href="list3.jsp">자유게시판</a></li>
							<li><a class="dropdown-item" href="list4.jsp">강아지자랑게시판</a></li>
							<li><a class="dropdown-item" href="list5.jsp">고민게시판</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 쇼핑 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="it_list.jsp ">상품페이지</a></li>
						</ul></li>
				</ul>
				<ul class="nav justify-content-end">
					<li class="nav-item"><a class="nav-link" href="login.jsp ">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="mypage.jsp ">마이페이지</a></li>
				</ul>
				<form class="d-flex" action="CartView.jsp" method="get">
					<button class="btn btn-outline-dark" type="submit">
						<i class=" me-1"></i> 장바구니 
						<span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
					</button>
				</form>
			</div>
		</div>
	</nav>
		    
		

</div>
			</td>
		</tr>
		<tr valign="middle" bgcolor="#ffffff">
			<td>
	<!-- 본문 -->
	<div id="containerDiv">
				<table class="board-post">
					<tbody><tr>
						<td colspan="4" style=""><h1>공지사항</h1></td>
					</tr>
					<tr>
						<td colspan="4" style="height:50px;font-weight:bold;">글제목이 들어갑니다</td>
					</tr>
					<tr class="board-post-option">
						<td style="width:10%;">번호<span>1</span></td>
						<td style="width:30%;">글쓴이<span>홍길동</span></td>
						<td style="width:50%;">작성일<span>2023.07.20</span></td>
						<td style="width:10%;">조회<span>1</span></td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="board-post-content">자유게시판 글 상세보기 기능1
							자유게시판 글상세보기 들어가서 댓글보기 기능추가 댓글작성기능추가
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3" width="70%"></td>
						<td width="30%" align="right"><button>수정</button>&nbsp;&nbsp;<button>삭제</button></td>
					</tr>
				
				</tbody></table>

				<div height="50px"><h2>댓글</h2></div>
				
			
				<!-- 댓글 보기 -->
				<table class="board_reply">
					<tbody><tr class="board_reply_option">
						<td width="30%">댓글번호<span>1</span></td>
						<td width="30%">작성자<span>홍길동</span></td>
						<td width="40%">작성일<span>2023.07.20</span></td>
					</tr>
					<tr>
						<td colspan="3" id="board_reply_content">
							<div id="board_reply_content"></div>
						</td>
					</tr>
					<tr style="font-weight:bold;border-top:1px solid black">
						<td style="width:400px"></td>
						<td></td>
						<td align="right"><button onclick="board_reply_form.submit()">수정</button>&nbsp;&nbsp;<button onclick="board_reply_form.submit()">삭제</button></td>
					</tr>
				</tbody></table>
				<!-- 댓글 보기 끝 -->
	
				<div height="50px"><h2>댓글작성</h2></div>
				
				<!-- 댓글 작성 -->
				<form name="board_reply_form" action="#">
				<table class="board_reply">
					<tbody><tr class="board_reply_head">
						<td width="50">글번호<span>2</span></td>
						<td width="200">작성자<span>홍길동</span></td>
						<td width="200">작성일<span>2023.07.20</span></td>
					</tr>
					<tr>
						<td colspan="3">
							<textarea cols="100" rows="4" placeholder="200자로 제한됩니다." required=""></textarea>
						</td>
					</tr>
					<tr style="font-weight:bold">
						<td style="width:400px"></td>
						<td align="right"><button onclick="board_reply_form.submit()">작성완료</button></td>
					</tr>
				</tbody></table>
				</form>
				<!-- 댓글 작성 끝 -->
				
				</div>
				 <!-- 본문끝 --> <!-- Footer -->
			</td>
		</tr>
		<tr valign="bottom" height="150" bgcolor="#ffffff">
			<td>
				<div id="footer"></div>
			</td>
		</tr>
	</table>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>