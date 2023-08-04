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
					<div class="board_write_wrap">
						<div class="board_write">
							<div class="title">
								<dl>
									<dt>제목</dt>
									<dd>
										<input type="text" placeholder="제목 입력">
									</dd>
								</dl>
							</div>
							<div class="info">
								<dl>
									<dt>글쓴이</dt>
									<dd>
										<input type="text" placeholder="글쓴이 입력">
									</dd>
								</dl>
								<dl>
									<dt>비밀번호</dt>
									<dd>
										<input type="password" placeholder="비밀번호 입력">
									</dd>
								</dl>
							</div>
							<div class="cont">
								<textarea placeholder="내용 입력"></textarea>
							</div>
						</div>
						<div class="bt_wrap">
							<a href="view.jsp" class="on">등록</a> <a href="list.jsp">취소</a>
						</div>
					</div>
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