package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class writeFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		//1.writeForm.jsp가 처리할 소스코드
		
		 //list.jsp(글쓰기)->신규글,content.jsp(글상세보기)->답변글
		   int num=0,ref=1,re_step=0,re_level=0;
		   
		   //content.jsp에서 매개변수가 전달되면 달라진다.
		   if(request.getParameter("num")!=null){
			   num=Integer.parseInt(request.getParameter("num"));//"3"
			   ref=Integer.parseInt(request.getParameter("ref"));
			   re_step=Integer.parseInt(request.getParameter("re_step"));
			   re_level=Integer.parseInt(request.getParameter("re_level"));
			   System.out.println("content.do에서 넘어온 매개변수");
			   System.out.println("num=>"+num+",ref=>"+ref+",re_step=>"+re_step+",re_level=>"+re_level);
		   }
		
		//2.처리한 결과->request에 저장
		 request.setAttribute("num", num);//${num}
		 request.setAttribute("ref", ref);
		 request.setAttribute("re_step", re_step);
		 request.setAttribute("re_level", re_level);
		 
		//3.페이지로 공유시키면서 이동
		return "/writeForm.jsp";//404에러유발
	}

}
