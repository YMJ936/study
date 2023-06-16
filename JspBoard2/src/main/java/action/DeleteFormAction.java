package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		//1.
		//요청을 받아서 처리하는 대상자->jsp(모델1)->모델2(jsp(X)명령어처리 클래스=액션클래스(O)) <--jsp(데이터를 공유받아서 출력만 한다.)
		  //deleteForm.do?num=2&pageNum=1
		  int num=Integer.parseInt(request.getParameter("num"));
		  String pageNum=request.getParameter("pageNum");
		  System.out.println("deleteForm.do의 매개변수확인");
		  System.out.println("num=>"+num+",pageNum=>"+pageNum);
		//2.deleteForm.jsp이 사용할 수 있도록 서버의 메모리에 저장
		  request.setAttribute("pageNum", pageNum);//${pageNum} == request.getAttribute(pageNum) 둘은 같다
		  request.setAttribute("num", num);
		
		return "/deleteForm.jsp";//404에러페이지(경로,파일명오타)
	}

}
