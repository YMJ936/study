<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>메인페이지</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
	<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<style>
		.cm-1{
			margin-top: 15px;
			margin-bottom: 50px;
		}
	</style>
	<script>
	$(function(){
		$("#header").load("resources/template/head.jsp");
		$("#footer").load("resources/template/footer.jsp");
	})
	</script>
  </head>
  <body>
  <!-- 상단바 시작-->
	<table border="solid 1px white" cellpadding="0" cellspacing="0" color="#a0a0a0" align="center" width="1536">
<tr valign="top" height="150" >
<td >
	<div id="header"></div>
</td>
</tr>
<tr valign="middle" bgcolor="#ffffff"><td>
	<!-- 상단바 끝 -->
	<!-- 메인페이지 시작 -->
	<!-- 캐러셀 시작 -->
	<div class="container cm-1">
	<div id="carouselExampleIndicators" class="carousel slide mx-2 my-2" data-bs-ride="true">
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
	  </div>
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="./resources/img/w1.jpg" class="d-block w-100" alt="...">
	       <div class="carousel-caption d-none d-md-block">
	        <h3>Dog Walker</h3>
	        <p>당신의 강아지를 산책 맡기거나, 다른 사람의 강아지를 산책해주고 멍코인을 얻어보세요!</p>
	      </div>
	    </div>
	    <div class="carousel-item">
	      <img src="resources/img/w2.jpg" class="d-block w-100" alt="...">
	       <div class="carousel-caption d-none d-md-block">
	        <h3>멍코인</h3>
	        <p>멍코인은 각종 애견용품들을 구매하는데 사용되고, 현금으로 환전도 가능해요</p>
	      </div>
	    </div>
	    <div class="carousel-item">
	      <img src="resources/img/w3.jpg" class="d-block w-100" alt="...">
	       <div class="carousel-caption d-none d-md-block">
	        <h3>지금 당장 시작해보세요</h3>
	        <p>귀여운 댕댕이들이 당신을 기다리고 있어요!</p>
	      </div>
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
	<!-- 캐러셀 끝 -->
	<!-- 카드 리뷰시작 -->
	<div class="card-group">
  <div class="card mx-2 my-2">
    <img src="resources/img/CD.png" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">아지님</h5>
      <p class="card-text">산책을 많이 못시켜줘서 미안했었는데 도그워커 덕분에 마음이 많이 편해졌어요</p>
      <p class="card-text"><small class="text-muted">2분전</small></p>
    </div>
  </div>
  <div class="card mx-2 my-2">
    <img src="resources/img/CD.png" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">김산책님</h5>
      <p class="card-text">주말에 심심할 때 강아지 산책도 시켜주고 용돈벌이도 할 수 있어서 좋아요</p>
      <p class="card-text"><small class="text-muted">15분전</small></p>
    </div>
  </div>
  <div class="card mx-2 my-2">
    <img src="/resources/img/CD.png" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">댕댕님</h5>
      <p class="card-text">강아지 키울 여건이 안되는데 이렇게라도 강아지들이랑 시간을 보낼 수 있어서 좋아요</p>
      <p class="card-text"><small class="text-muted">3시간전</small></p>
    </div>
  </div>
</div>
<!-- 카드 리뷰 끝 -->
	</div><!-- 주 컨텐츠 컨테이너 끝 -->
	<!-- 메인페이지 끝 -->
</td></tr>
<tr valign="bottom" height="150"  bgcolor="#ffffff">
<td>
	<div id="footer"></div>
</td>
</tr>
</table>
	<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  </body>
</html>