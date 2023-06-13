<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<% 
   java.util.HashMap mapData=new java.util.HashMap();
   mapData.put("name","홍길동");
   mapData.put("today",new java.util.Date());//날짜객체
   int intArray[]={1,2,3,4,5}; //배열은 앞에 있는 자료형과 상관없이 무조건 객체이다!
%>
<c:set var="intArray" value="<%=new int[]{1,2,3,4,5} %>" />
<!-- map도 주소를 가진 동일한 객체(객체명은 다르지만 주소는 같다) 아래 구문은 주소 값을 넘겨주는 것-->
<c:set var="map" value="<%=mapData%>" />
<html>
<head>
<meta charset="UTF-8">
<title>forEach구문</title>
</head>
<body>
<%
  //1~100까지의 합 중에서 홀수 값을 출력하시오
  int sum=0;
  for(int i=1;i<=100;i+=2){//c:forEach var="초기변수명"
	//if(i%2==1)				   // begin="시작값" end="종료값"
		sum+=i; //sum=sum+i //수정				   // step="증가치"(default step=1) ->디폴트 스탭인 경우 생략 가능
  }
  out.println(sum);
%>
<h4>1~100까지의 홀수합</h4>
<c:set var="sum" value="0" />
<c:forEach var="i" begin="1" end="100" step="2" >
	<c:set var="sum" value="${sum+i}" />
</c:forEach>
결과:${sum}
<hr>
결과2:<c:out value="${sum}" />
<hr>
<h4>배열의 값,컬렉션의 값을 출력</h4>
<%-- 
<c:set var="intArray" value="<%=new int[]{1,2,3,4,5} %>" items="${외부에서 불러올 객체명}"  />
--%>
<c:forEach var="i" items="${intArray}" begin="2" end="4">
	<h1>[${i }]</h1>
</c:forEach>
<h4>Map(key,value)</h4>
<!-- 
	var(객체명) items(배열 또는 컬렉션객체)
	i는 hashmap객체에서 꺼낸 각각의 객체를 의미
	key,value값은 hashmap객체가 가지고 있는 기본 속성명
 -->
 <c:forEach var="i" items="${map}">
 	<h1>${i.key}=${i.value}</h1>
 </c:forEach>
 
 
</body>
</html>