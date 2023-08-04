<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<html>
<head>
<meta charset="UTF-8">
		<title>header</title>
		</head>
		    <body>
		  <nav class="navbar navbar-expand-lg bg-light pb-3">
		<div class="container">
			<a class="navbar-brand" href="../resources/main.jsp "><img src="../resources/img/logo2.png"
				width="200" alt="..."></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="./matching/WalkingStatusNothing.jsp" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 산책매칭 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="../matching/matching.jsp">강아지 찾기</a></li>
							<li><a class="dropdown-item" href="../matching/ReservationCheck.jsp">예약확인</a></li>
							<li><a class="dropdown-item" href="../matching/WalkingStatusOngoing.jsp ">산책현황</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" href="#">채팅</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 커뮤니티 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="list.do">공지사항</a></li>
							<li><a class="dropdown-item" href="../board/list2.jsp">Q&A</a></li>
							<li><a class="dropdown-item" href="../board/list3.jsp">자유게시판</a></li>
							<li><a class="dropdown-item" href="../board/list4.jsp">강아지자랑게시판</a></li>
							<li><a class="dropdown-item" href="../board/list5.jsp">고민게시판</a></li>
						</ul></li>
						
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 쇼핑 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="order/itlist" >상품페이지</a></li>
						</ul></li>
				</ul>
				<ul class="nav justify-content-end">
					<li class="nav-item"><a class="nav-link" href="../member/login.jsp ">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="../member/mypage.jsp ">마이페이지</a></li>
				</ul>
				<form class="d-flex" action="../order/CartView.jsp" method="get">
					<button class="btn btn-outline-dark" type="submit">
						<i class=" me-1" ></i> 장바구니 
						<span
							class="badge bg-dark text-white ms-1 rounded-pill" >0</span>
					</button>
				</form>
			</div>
		</div>
	</nav>
		    </body>
</body>
</html>