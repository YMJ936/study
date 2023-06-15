package action;

import java.sql.Timestamp;//DB에 관련된 날짜,시간

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//값 저장, 메서드 호출,
import ymj.board.BoardDAO;
import ymj.board.BoardDTO;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		//1.writePro.jsp가 처리해야 할 자바코드 복사
		request.setCharacterEncoding("utf-8");
		
		BoardDTO article=new BoardDTO();
		article.setNum(Integer.parseInt(request.getParameter("num")));//num
		
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setPasswd(request.getParameter("passwd"));
	
		//readcount(0)->default,날짜,원격주소 ip
		article.setReg_date(new Timestamp(System.currentTimeMillis()));//오늘날짜 계산해서 수동으로 저장
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		article.setContent(request.getParameter("content"));
		article.setIp(request.getRemoteAddr());//원격ip주소
		
		BoardDAO dbPro=new BoardDAO();
		dbPro.insertArticle(article);//입력+계산한 값을 전부 저장한 값
		//response.sendRedirect("/list.jsp")
		//3.이동할 페이지 지정
		return "/writePro.jsp";
	}

}
