<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
//클라이언트가 서버에 항상 동일한 요청을 해도 항상 새로운내용으로 전송
 response.setHeader("Cache-Control","no-cache");//메모리X
 response.setHeader("Pragma","no-cache");//메모리에 저장X
 response.setDateHeader("Expires",0);//보관하지 마라
%>
<%
    //MemberUpdate.jsp==>MemberUpdateProc.jsp(8개)
    request.setCharacterEncoding("utf-8");//한글처리
%>
<jsp:useBean id="memMgr" class="hewon.MemberDAO" />
<jsp:useBean id="mem" class="hewon.MemberDTO" />
<jsp:setProperty name="mem" property="*" />
<%   
    //->member=>memberInsert()호출하기위해서
    //추가
    String mem_id=request.getParameter("mem_id");
    System.out.println
    ("MemberUpdateProc.jsp(mem_id)=>"+mem_id);
    //-------------------------------------------------------
    boolean check=memMgr.memberUpdate(mem);
%>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 체크</title>
</head>
<body bgcolor="#FFFFCC">
<br>
<center>
<%
  if(check){//회원수정에 성공했다면
%>
  <script>
     alert("성공적으로 수정되었습니다.");
     location.href="Login.jsp";
  </script>
 <% }else{ %>
  <script>
     alert("수정도중 에러가 발생이 되었습니다.");
     history.back();//MemberUpdate.jsp
  </script>
 <%  } %>
</center>
</body>
</html>