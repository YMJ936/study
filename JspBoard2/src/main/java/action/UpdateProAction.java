package action;

import java.sql.Timestamp;//DB에 관련된 날짜,시간

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//값 저장, 메서드 호출,
import ymj.board.BoardDAO;
import ymj.board.BoardDTO;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		//1.updatePro.jsp가 처리해야 할 자바코드 복사
		request.setCharacterEncoding("utf-8");
		//추가(수정된 게시물로 이동시키기 위해서 필요)
		String pageNum=request.getParameter("pageNum");
		System.out.println("UpdateProAction에서의 pageNum=>"+pageNum);
		//----수정된 데이터를 담을 객체 생성----------
		BoardDTO article=new BoardDTO();
		article.setNum(Integer.parseInt(request.getParameter("num")));//num
		
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		
		BoardDAO dbPro=new BoardDAO();
		int check=dbPro.updateArticle(article);//입력+계산한 값을 전부 저장한 값
		//2.공유해서 전달
		request.setAttribute("pageNum", pageNum);//수정페이지
		request.setAttribute("check", check);//수정성공유무
		
		//3.이동할 페이지 지정
		return "/updatePro.jsp";
	}
}
