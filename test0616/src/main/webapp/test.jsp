<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 3번째 예제(표현식활용,선언문의 메서드 작성)</title>
</head>
<body>

<%
 float f=2.3f;//지역변수
 int i=Math.round(f);//반올림해주는 메서드
 java.util.Date d=new java.util.Date();
 out.println("d의 값은?=>"+d);
%>
<hr><p>
정수 f의 반올림값은? <%=i%><p>
현재의 날짜와 시간? <%=d.toString()%>
</body>
</html>