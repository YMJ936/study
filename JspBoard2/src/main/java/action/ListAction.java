package action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ymj.board.BoardDAO;

//요청명령어에 해당되는 명령어처리클래스=액션클래스=컨트롤러 클래스
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		// TODO Auto-generated method stub
		//1./list.jsp에서 처리했던 자바코드를 대신처리-결과를 request객체에 담아서 list.jsp에 전달해주면 끝
		 int pageSize=10;//numPerPage->페이지당 보여주는 게시물수(=레코드수)
		 int blockSize=3;//pagePerBlock->블럭당 보여주는 페이지수 10
		 //작성날짜=> 우리나라 스타일 년월일 시분초->SimpleDateFormat
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");


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
			//2.처리한 결과를 공유(서버메모리에 저장) ${currentPage}
			request.setAttribute("currentPage",currentPage); //이 때 가능한한 키값과 벨류값을 같게 준다. ${}를 통해 출력하고도 모름
			request.setAttribute("startRow",startRow);
			request.setAttribute("count",count);
			request.setAttribute("pageSize",pageSize);
			request.setAttribute("blockSize",blockSize);
			request.setAttribute("number",number);
			request.setAttribute("articleList",articleList);
			
			//3.공유해서 이동할 수 있도록 페이지를 지정
		 return "/list.jsp"; //컨트롤러가 이동시키면서 공유시켜준다.
	}

}
