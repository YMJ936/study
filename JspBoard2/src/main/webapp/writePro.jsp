<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.Timestamp,ymj.board.*" %>
<%
	request.setCharacterEncoding("utf-8");
	//BoardDTO->Setter Method()+hidden(4)
	//BoardDAO =>insertArticle(DTO객체) 호출
%>
<jsp:useBean id="article" class="ymj.board.BoardDTO" />
<jsp:setProperty name="article" property="*" />
<%
	//readcount(0)->default,날짜,원격주소 ip
	Timestamp temp=new Timestamp(System.currentTimeMillis()); //컴퓨터시간
	article.setReg_date(temp);//오늘날짜 계산해서 수동으로 저장
	article.setIp(request.getRemoteAddr());//원격ip주소
	
	BoardDAO dbPro=new BoardDAO();
	dbPro.insertArticle(article);//입력+계산한 값을 전부 저장한 값
	response.sendRedirect("list.jsp");
	//입력한 후-> 다시 DB접속 -> 새로고침
%>