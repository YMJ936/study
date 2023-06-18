<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="hewon.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id,passwd확인</title>
</head>
<body>
<%
    //id,passwd를 받아서 loginCheck()호출->true->LoginSuccess.jsp
    String mem_id=request.getParameter("mem_id");
    String mem_passwd=request.getParameter("mem_passwd");
    System.out.println("mem_id=>"+mem_id);
    System.out.println("mem_passwd=>"+mem_passwd);
    //->member=>loginCheck()호출
    MemberDAO memMgr=new MemberDAO();
    boolean check=memMgr.loginCheck(mem_id, mem_passwd);
    System.out.println("LoginProc.jsp의 check=>"+check);
%>
<%
  //check->LoginSuccess.jsp(인증화면),LogError.jsp(에러메세지)
  if(check){
	  session.setAttribute("idKey",mem_id);//키명,저장할값(id)
	  //response.sendRedirect("LoginSuccess.jsp");
	  response.sendRedirect("Login.jsp");
  }else{//check=false (id X passwd X)
	  response.sendRedirect("LogError.jsp");
  }
%>
</body>
</html>







