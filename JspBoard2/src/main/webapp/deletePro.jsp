<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="ymj.board.*" %>
    
<%
	int num=Integer.parseInt(request.getParameter("num"));
   String pageNum=request.getParameter("pageNum");
   //암호만 추가
   String passwd=request.getParameter("passwd");
   System.out.println("deletePro.jsp의 매개변수확인");
   System.out.println("num=>"+num+",passwd=>"+passwd+",pageNum=>"+pageNum);
	
	BoardDAO dbPro=new BoardDAO();
	int check=dbPro.deleteArticle(num,passwd);
	if(check==1){//글 삭제 성공
	%>
	<script>
		alert("성공적으로 삭제되었습니다.");
	</script>
	<meta http-equiv="Refresh"
			  content="0;url=list.jsp?pageNum=<%=pageNum%>">
	<%}else{ %>
	<script>
	alert("비밀번호가 맞지 않습니다. \n다시 비밀번호를 확인요망!");
	history.go(-1);
	</script>
	<%} %>
	}