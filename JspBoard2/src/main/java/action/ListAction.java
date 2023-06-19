package action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ymj.board.BoardDAO;

//요청명령어에 해당되는 명령어처리클래스=액션클래스=컨트롤러 클래스
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		
		String pageNum=request.getParameter("pageNum");
		//추가(검색분야,검색어)
		String search=request.getParameter("search");
		String searchtext=request.getParameter("searchtext");
		System.out.println("ListAction의 매개변수 확인");
		System.out.println("pageNum=>"+pageNum+"search=>"+search+",searchtext=>"+searchtext);
		
		int count=0;//총레코드수
		List articleList=null;//화면에 출력할 레코드를 저장할 변수
			
			BoardDAO dbPro=new BoardDAO();
			count=dbPro.getArticleSearchCount(search,searchtext);//select count(*) from bo~
			System.out.println("현재의 레코드수(count)=>"+count);
			//1.화면에 출력할 페이지, 2.출력할 레코드갯수
			Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
			
			if(count >0){//한개라도 레코드 수가 있다면         여기에 endRow를 적으면 안된다.(논리적인 에러 뜬다.)
				articleList=dbPro.getBoardArticles(pgList.get("startRow"),//첫번째 레코드번호
																pgList.get("pageSize"),//불러올 갯수 -> 보통 10개
																search,searchtext);//10개 고정
				System.out.println("list.jsp의 articleList=>"+articleList);
				System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			}else {//count=0
				articleList=Collections.EMPTY_LIST;//비어있는 객체 -> 이거 안써도 되긴 함 하지만 세련되게 코딩하는 방법
			}
			//			  122-(1-1)*10=122,121,120,,,,,
			//			  122-(2-1)*10=122-10=112,111,,,

			//2.처리한 결과를 공유(서버메모리에 저장) ${currentPage}
			request.setAttribute("search",search); //이 때 가능한한 키값과 벨류값을 같게 준다. ${}를 통해 출력하고도 모름
			request.setAttribute("searchtext",searchtext);
			request.setAttribute("pgList",pgList);//페이징처리 10개
			request.setAttribute("articleList",articleList);
			
			//3.공유해서 이동할 수 있도록 페이지를 지정
		 return "/list.jsp"; //컨트롤러가 이동시키면서 공유시켜준다.
	}

}
