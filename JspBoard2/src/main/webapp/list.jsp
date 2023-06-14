<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="ymj.board.*,java.util.*,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<%!

 int pageSize=5;//numPerPage->페이지당 보여주는 게시물수(=레코드수)
 int blockSize=3;//pagePerBlock->블럭당 보여주는 페이지수 10
 //작성날짜=> 우리나라 스타일 년월일 시분초->SimpleDateFormat
 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

%>
<%
	//게시판을 처음 실행시키면 무조건 1페이지부터 출력
	//->가장 최근의 글이 나오게 설정
	String pageNum=request.getParameter("pageNum");
	if(pageNum==null){
		pageNum="1";//default(무조건 1페이지 설정)
	}
	//nowPage(현재페이지(클릭해서 보고있는 페이지))
	int currentPage=Integer.parseInt(pageNum);//"1"->1  "문자열"->숫자
	//시작레코드번호
	//                  (1-1)*10+1,(2-1)*10+1=11,(3-1)*10+1=21
	int startRow=(currentPage-1)*pageSize+1;
	int endRow=currentPage*pageSize;//종료 레코드번호
	//						1*10,2*10=20,3*10=30
	int count=0;//총레코드수
	int number=0;//beginPerPage->페이지별로 시작하는 맨처음에 나오는 게시물번호
	List articleList=null;//화면에 출력할 레코드를 저장할 변수
	
	BoardDAO dbPro=new BoardDAO();
	count=dbPro.getArticleCount();//select count(*) from bo~
	System.out.println("현재의 레코드수(count)=>"+count);
	if(count >0){//한개라도 레코드 수가 있다면         여기에 endRow를 적으면 안된다.(논리적인 에러 뜬다.)
		articleList=dbPro.getArticles(startRow, pageSize);//10개 고정
		System.out.println("list.jsp의 articleList=>"+articleList);
		
	}
	//			  122-(1-1)*10=122,121,120,,,,,
	//			  122-(2-1)*10=122-10=112,111,,,
	number=count-(currentPage-1)*pageSize;
	System.out.println("페이지별 number=>"+number);
	
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#e0ffff">
<center><b>글목록(전체 글:<%=count %>)</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#b0e0e6">
    <a href="writeForm.jsp">글쓰기</a>
    </td>
</tr>
</table>
<!-- 테이블에 출력(데이터의 유무) -->
<%
	if(count==0){
%>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center">게시판에 저장된 글이 없습니다.</td>
	</tr>
</table>
<%}else{ %>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#b0e0e6"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
    <!-- 실질적으로 레코드를 출력시켜주는 부분 -->
    <%
    	for(int i=0;i<articleList.size();i++){
    		//										articleList.elementAt(i)->옛날에 하던 방식
    	  BoardDTO article=(BoardDTO)articleList.get(i);
    %>
   <tr height="30"><!-- 하나씩 감소시키면서 출력하는 게시물 번호 -->
    <td align="center"  width="50" ><%=number-- %></td>
    <td  width="250" >
	   <!-- 답변글경우 먼저 답변이미지를 부착시키는 코드 -->
	   <%
	    int wid=0;//공백을 계산하기 위한 변수선언
	    if(article.getRe_level() > 0){ //1이상 답변글이라면
	    	wid=7*(article.getRe_level());//7,14,21,, ->들여쓰기가 점점 심해진다.
	   %>
	  <img src="images/level.gif" width="<%=wid %>" height="16">
	  <img src="images/re.gif">
	  <% }else { %>
	  	<img src="images/level.gif" width="<%=wid %>" height="16">
	  <% } %><!-- num(게시물번호),pageNum(페이지번호) 이 둘은 항상 따라다닌다. -->
      <a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
      		<%=article.getSubject() %></a>
      	<%if(article.getReadcount()>=20) { %>
         <img src="images/hot.gif" border="0"  height="16"> </td>
         <%} %>
    <td align="center"  width="100"> 
       <a href="mailto:<%=article.getEmail()%>"><%=article.getWriter()%></a></td>
    <td align="center"  width="150"><%=sdf.format(article.getReg_date())%></td>
    <td align="center"  width="50"><%=article.getReadcount() %></td>
    <td align="center" width="100" ><%=article.getIp() %></td>
  </tr>
  <% }//for %>
</table>
<%} //else %>
<!-- 페이징처리(계산) -->
<%
  if(count > 0){ //레코드가 한개이상 이라면
   //1.총페이지수 구하기
   //                    122/10=12.2+1.0=13.2=13,(122%10)=1
   int pageCount=count/pageSize+(count%pageSize==0?0:1);
   //2.시작페이지
   int startPage=0;
   if(currentPage%blockSize!=0){//1~9,11~19,21~29
      startPage=currentPage/blockSize*blockSize+1;
   }else{//10%10=0,(10,20,30,40~)
		             //((10/10)-1)*10+1=1, 20=>11
	  startPage=((currentPage/blockSize)-1)*blockSize+1; 
   }
   //종료페이지
   int endPage=startPage+blockSize-1;//1+10-1=10,11+10-1=20
   System.out.println
    ("startPage=>"+startPage+",endPage="+endPage);
   //블럭별로 구분해서 링크걸어서 출력
   //     11     >          10      //마지막페이지=총페이지수
   if(endPage > pageCount)  endPage=pageCount;
   //1) 이전블럭(11>10) 16>15---------->11-10=1
    if(startPage > blockSize){%>
   <a href="list.jsp?pageNum=<%=startPage-blockSize%>">[이전]</a>
   <% } 
   //2)현재블럭(1,2,[3],4~10)
     for(int i=startPage;i<=endPage;i++) {%>
   <a href="list.jsp?pageNum=<%=i%>">[<%=i%>]</a>
   <% }
   //3) 다음블럭  1~14 < 15
     if(endPage < pageCount){%>
     <a href="list.jsp?pageNum=<%=startPage+blockSize%>">[다음]</a>
     <% 
        } //다음블럭 
  }//if(count > 0){
%>
</center>
</body>
</html>