<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<%
	//int num=(Integer)request.getAttribute("num"); -> 불편하기 때문에 ${num}으로 쓴다.
%>
<body bgcolor="#e0ffff">  
<center><b>글쓰기</b>
<br>
<!-- onsubmit 이벤트="return 호출할함수명()"
	  return true인 경우에만 자동으로 전송(writePro.jsp)
 -->
<form method="post" name="writeform" action="/JspBoard2/writePro.do" onsubmit="return writeSave()">

	<!-- 입력하지 않고 매개변수로 전달해서 테이블에 저장(hidden) 4개 
		아래 값들을 제대로 저장하지 않으면 발생할 수 있는 오류 -> NumberFormatException->"3"->3 "3>"->3(X) 조심하기 url창에 보인다.
	-->
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="re_step" value="${re_step}">
<input type="hidden" name="re_level" value="${re_level}">


<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="#e0ffff" align="center">
   <tr>
    <td align="right" colspan="2" bgcolor="#b0e0e6">
	    <a href="/JspBoard2/list.do"> 글목록</a> 
   </td>
   </tr>
   <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >제 목</td>
    <td  width="330">
    <c:if test="${num==0}">
       <input type="text" size="40" maxlength="50" name="subject">
       </c:if>
       <c:if test="${num!=0}">
       <input type="text" size="40" maxlength="50" name="subject" value="[re]"></td>
       </c:if>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center">Email</td>
    <td  width="330">
       <input type="text" size="40" maxlength="30" name="email" ></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40"></textarea> </td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >비밀번호</td>
    <td  width="330" >
     <input type="password" size="8" maxlength="12" name="passwd"> </td>
  </tr>
<tr>      
 <td colspan=2 bgcolor="#b0e0e6" align="center"> 
  <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='/JspBoard2/list.do'">
</td></tr></table>    
</form>    
</body>
</html>      
