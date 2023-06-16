package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//ctrl+shift+o
import ymj.board.BoardDAO;
import ymj.board.BoardDTO;
/*
 * 1.공통의 메서드로 작성하기 위해서
 * 2.상속기법->형변환을 사용하기 위해서->소스코드를 줄일 수 있기 때문에
 */
public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		  //content.jsp=>글수정버튼 클릭->updtaeFrorm.do?num=3&pageNum=1
		  int num=Integer.parseInt(request.getParameter("num"));
			String pageNum=request.getParameter("pageNum");
			
			BoardDAO dbPro=new BoardDAO();
			BoardDTO article=dbPro.updateGetArticle(num);//조회수X

			System.out.println("updateFormAction의 매개변수 확인");//안써도 무방하지만 쓰는게 에러를 잡기에 편한 디버깅 코딩
			System.out.println("num=>"+num+",pageNum=>"+pageNum);
		
		//2.서버의 메모리에 저장해서 공유시킨다.
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
			
		//3.이동시켜서 불러와서 출력
		return "/updateForm.jsp";
	}

}
