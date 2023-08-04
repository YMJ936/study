<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="../css/css.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script>
	$(function(){
		$("#header").load("../template/head.jsp");
		$("#footer").load("../template/footer.jsp");
	})
</script>
</head>
<body>
	<!-- Header -->
	<table border="solid 1px white" cellpadding="0" cellspacing="0"
		color="#a0a0a0" align="center" width="1536">
		<tr valign="top" height="150">
			<td>
				<div id="header"></div>
			</td>
		</tr>
		<tr valign="middle" bgcolor="#ffffff">
			<td>
				<!-- 본문 -->

				<div class="board_wrap">
					<div class="board_title">
						<strong>공지사항</strong>
						<p>공지사항</p>
					</div>
					<div class="board_list_wrap">
						<div class="board_list">
							<div class="top">
								<div class="num">번호</div>
								<div class="title">제목</div>
								<div class="writer">글쓴이</div>
								<div class="date">작성일</div>
								<div class="count">조회</div>
							</div>
							<div>
								<div class="num">5</div>
								<div class="title">
									<a href="view.jsp">사이트 공지</a>
								</div>
								<div class="writer">관리자</div>
								<div class="date">2023.7.17</div>
								<div class="count">33</div>
							</div>
							<div>
						</div>
						<div class="board_page">
							<a href="#" class="bt first"><<</a> <a href="#" class="bt prev"><</a>
							<a href="#" class="num on">1</a> <a href="#" class="num">2</a> <a
								href="#" class="num">3</a> <a href="#" class="num">4</a> <a
								href="#" class="num">5</a> <a href="#" class="bt next">></a> <a
								href="#" class="bt last">>></a>
						</div>
						<div class="bt_wrap">
							<a href="write.jsp" class="on">등록</a>
						</div>
					</div>
				</div> <!-- 본문끝 --> <!-- Footer -->
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