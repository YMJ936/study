<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="ymj.board.*" %>
<%
	//글쓰기,글수정은 둘다 한글처리
	request.setCharacterEncoding("utf-8");
	//BoardDTO->Setter Method()+hidden(2)num,pageNum
	//BoardDAO =>updateArticle(DTO객체) 호출
%>
<jsp:useBean id="article" class="ymj.board.BoardDTO" />
<jsp:setProperty name="article" property="*" />
<%
	//수정한 게시물이 있는 페이지로 이동시키기 위해서
	String pageNum=request.getParameter("pageNum");
	
	BoardDAO dbPro=new BoardDAO();
	int check=dbPro.updateArticle(article);//입력+계산한 값을 전부 저장한 값
	if(check==1){//글수정에 성공했다면
	//response.sendRedirect("list.jsp");
	//meta태그 오타=>화면에 하얗게 뜨는 현상
	//http-equiv="Refresh"=>새로고침 content="초;url=이동할페이지명"
	%>
	<meta http-equiv="Refresh"
			  content="0;url=list.jsp?pageNum=<%=pageNum%>">
	<%}else{ %>
	<script>
	alert("비밀번호가 맞지 않습니다. \n다시 비밀번호를 확인요망!");
	history.go(-1);
	</script>
	<%} %>
	}