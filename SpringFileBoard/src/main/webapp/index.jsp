<%
  response.sendRedirect(request.getContextPath()+"/board/list.do");
  	
	//getContextPath()   => 도메인 + 프로젝트이름
  //이렇게 getContextPath()를 쓰는 이유 =>  프로젝트바뀔때마다 SpringMVC5/board/list.do 이런식으로 수정안해도되도록
%>