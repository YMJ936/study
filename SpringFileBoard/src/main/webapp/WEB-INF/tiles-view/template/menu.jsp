<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//요청에 따른 프로젝트의 경로
	String contextPath = request.getContextPath();

//밑에 /board/list.do => board라는 폴더는 없음 => 가상경로(보안을 위해)를 통해 링크
%>
<ul>
	<li><a href="<%=contextPath%>/board/list.do">목록</a></li>
	<li><a href="<%=contextPath%>/board/write.do">글쓰기</a></li>
</ul>