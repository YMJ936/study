<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catch(7).jsp</title>
</head>
<body>
<% 
	/*
		try{실행구문}catch(Exception e)
		{e.getMessaege or e.toString()}
	  형식)<c:catch var="예외처리객체명">예외처리 내용</c:catch>
	*/
%>
<h1>
	<c:catch var="ex">
		name매개변수명:<%=request.getParameter("name") %>
		<%
		if(request.getParameter("name").equals("test")) {%>
		  ${param.name}은 test입니다.
		  <%} %>
	</c:catch>
	<c:if test="${not empty ex}">
	<%-- <c:if test="${ex!=null}"> --%>
		예외가 발생하였습니다.<br>
		${ex}
	</c:if>
</h1>
</body>
</html>