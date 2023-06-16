package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ymj.board.BoardDAO;

//num,page,passwd->삭제->check=1?
public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		int num=Integer.parseInt(request.getParameter("num"));
		   String pageNum=request.getParameter("pageNum");
		   //암호만 추가
		   String passwd=request.getParameter("passwd");
		   System.out.println("deletePro.do의 매개변수확인");
		   System.out.println("num=>"+num+",passwd=>"+passwd+",pageNum=>"+pageNum);
			
			BoardDAO dbPro=new BoardDAO();
			int check=dbPro.deleteArticle(num,passwd);
		//2.공유
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("check", check);
		
		return "/deletePro.jsp";//updatePro.jsp와 소스코드가 동일
	}

}
