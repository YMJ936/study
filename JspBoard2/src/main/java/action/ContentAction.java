package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import ymj.board.BoardDAO;
import ymj.board.BoardDTO;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		
		//1.content.do?num=3&pageNum=1
		//글상세보기=>상품의 정보를 자세히(sangDetail.jsp)
		//계산목적이 아닌 메서드의 매개변수가 int이기 때문에 형변환 시킨 것이다.
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		BoardDAO dbPro=new BoardDAO();
		BoardDTO article=dbPro.getArticle(num);
		//링크문자열의 길이를 줄이기 위해서
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		System.out.println("content.do의 매개변수 확인");//안써도 무방하지만 쓰는게 에러를 잡기에 편한 디버깅 코딩
		System.out.println("ref=>"+ref+",re_step=>"+re_step+",re_level=>"+re_level+",num=>"+num+",pageNum=>"+pageNum);

		//2.request객체에 공유
		request.setAttribute("num", num);//${num}
		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("article", article);
		//request.setAttribute("re_step", re_step); ->article 객체 안에 re_step, re_level이 이미 들어있으므로 굳이 보낼 필요없다.
		//request.setAttribute("re_level", re_level);
		
		//3.이동할 페이지 지정
		return "/content.jsp";
	}

}
