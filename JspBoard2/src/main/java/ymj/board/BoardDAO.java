package ymj.board;

//DBConnectionMgr(DB접속)
//BoardDTO(매개변수,반환형으로 사용 또는 데이터를 담는 역할)->출력할 때마다 사용하기 위에, 웹에 뿌리기 위해

import java.sql.*;
import java.util.*;//ArrayList

public class BoardDAO {
	//10개가 이미 생성되어 있다.(connection)-> 커넥션 풀(pool)
	private DBConnectionMgr pool=null;//1.연결할 클래스 객체선언
	//공통
	private Connection con=null; //대여
	private PreparedStatement pstmt=null;//속도향상
	private ResultSet rs=null;// select
	private String sql="";//실행시킬 sql구문 저장용
	
	//2.생성자를 통해서 연결->서로 연결
	public BoardDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	//3.웹상에서 호출할 메서드를 요구분석
	//1.페이징 처리를 위한 전체 레코드 수를 구해와야 한다.
	//select count(*) from board;// 반환형 int(갯수로 표현하기 때문) O where X(where조건이 없다= 매개변수가 없다)
	public int getArticleCount() {//getMemberCount()->MemberDAO
		int x=0;//총레코드수를 기억할 지역변수
		
		try {//DB연결하기 때문에 예외처리 필수
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from board";//select count(*) from member
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);//변수명=rs.get자료형(필드명or인덱스번호)
			}						//필드명이 없기 때문에 인덱스번호로 불러와야 한다. select와 from 사이에서 1번
		}catch(Exception e) {
			System.out.println("getArticleCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//2.글목록보기에 대한 메서드구현 => 레코드 한개 이상 -> 한페이지당 10개씩
	//"select * from board order by ref desc, re_step (asc) limit ?,?
	// 1)레코드 시작번호 2)불러올 레코드의 갯수
	public List getArticles(int start,int end) {
		List articleList=null;//ArrayList<BoardDTO> articleList=
		/*
		 * ref(그룹번호)=>num와 같은 기능
		 * 그룹번호가 가장 최근의 글을 중심으로 정렬하되, 만약에
		 * 답변글이 나오는 순서는 오름차순으로 정렬하라
		 * 몇번째 레코드 번호를 기준으로해서 몇개까지 정렬할 것인가를 정해주는 SQL구문
		 */
		try {
			con=pool.getConnection();
			sql="select * from board order by ref desc, re_step limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start-1);//mysql은 레코드순번이 내부적 0부터 시작
			pstmt.setInt(2, end);//불러와서 담을 갯수(10,15,20,,,)
			rs=pstmt.executeQuery();
			//누적의 개념
			if(rs.next()) {//보여주는 레코드가 이미 있다면
				articleList=new ArrayList(end);//end갯수만큼 데이터공간 생성
				do {
					/*
					BoardDTO article=new BoardDTO();//MemberDTO
					article.setNum(rs.getInt("num"));//게시물번호
					article.setWriter(rs.getString("writer"));//작성자
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));//0
					article.setRef(rs.getInt("ref"));//그룹번호
					article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
					article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이)
					
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));//글쓴이의 ip
					*/
					BoardDTO article=makeArticleFromResult();
					//추가
					articleList.add(article);//생략O =>데이터저장X for문(null)
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getArticles()에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	return articleList;//최대 10개의 데이터가 들어감=>list.jsp->for문=>필드출력
	}
	//게시판의 글쓰기 및 답변달기
	//insert into board values(?,,,,)
	public void insertArticle(BoardDTO article) {
		//1.article->신규글인지 답변글인지 구분
		int num=article.getNum();//0->신규글,0이 아닌경우->답변글
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		
		int number=0;//데이터를 저장하기 위한 게시물 번호
		System.out.println("insertArticle의 내부 num=>"+num);
		System.out.println("ref=>"+ref+",re_step=>"+re_step+",re_level=>"+re_level);
		try {
			con=pool.getConnection();
			sql="select max(num) from board";//입력 X 계산 O
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				number=rs.getInt(1)+1;//최대값+1 //인덱스번호로 구별 select 와 from 사이 1부터 시작
			}else {//데이터가 없는 경우 0
				number=1;
			}
			//답변글인지 신규글인지
			if(num!=0) {//양수이면서 1이상(답변글)
				/*
				 * 그룹번호는 같은면서 나보다 re_step값이 큰 값을 찾아서
				 * 하나 re_step값을 하나 증가(ex 2->3,4->5,5->6)
				 */
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, ref);//같은 그룹번호
				pstmt.setInt(2, re_step);
				int update=pstmt.executeUpdate();
				//1 성공 0 실패
				System.out.println("댓글수정유무(update)=>"+update);
				//답변글
				////////////////////////->빼먹으면 답변글이 안뜬다.
				re_step=re_step+1;
				re_level=re_level+1;
				//////////////////////////
			}else {//num==0 신규글인 경우
				ref=number;//1,2,3,,,
				re_step=0;//답변순서
				re_level=0;//들여쓰기X
			}
			//12개 필드->num,reg_date,readcount(생략)->0
			//작성날짜->sysdate,now() (mysql)
			sql="insert into board(writer,email,subject,passwd,";
			sql+=" reg_date,ref,re_step,re_level,content,ip) ";
			sql+="values(?,?,?,?,?,?,?,?,?,?)";
			//sql+="values(?,?,?,?,now(),?,?,?,?,?)"; => 이렇게 하면 5번 안써도 됨
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,article.getWriter());//웹상에서 이미저장
			pstmt.setString(2,article.getEmail());
			pstmt.setString(3,article.getSubject());
			pstmt.setString(4,article.getPasswd());
			pstmt.setTimestamp(5,article.getReg_date());//writePro.jsp(계산)
			
			//pstmt.setInt(6, article.getRef()); 기존의 데이터X
			pstmt.setInt(6,ref);//(게시물번호)
			pstmt.setInt(7,re_step);//0
			pstmt.setInt(8,re_level);//0
			//---------------------------------------------
			pstmt.setString(9,article.getContent());//내용
			pstmt.setString(10,article.getIp());//request.getRemoteAddr();
			int insert=pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>"+insert);
			
		}catch(Exception e) {
			System.out.println("insertArticle()에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}//insertArticle


	//게시판의 글 상세보기=>하나의 레코드를 찾기
	//content.jsp?num=3&pageNum=1
	//형식)select * from board where num=3
	public BoardDTO getArticle(int num) {
		BoardDTO article=null;//여러개 얻어오려면? => ArrayList<BoardDTO>articleList=null 한개이상
		try {
			con=pool.getConnection();
			//1.조회수를 먼저 증가시킨다.
			sql="update board set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int update=pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>"+update);//1
			//2.조회수가 증가 된 것을 찾아서 웹에 출력
			sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {//찾았다면 레코드 한개를 담기
				article=this.makeArticleFromResult();
				/*
				article=new BoardDTO();//MemberDTO
				article.setNum(rs.getInt("num"));//게시물번호
				article.setWriter(rs.getString("writer"));//작성자
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));//0
				article.setRef(rs.getInt("ref"));//그룹번호
				article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
				article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이)
				
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));//글쓴이의 ip
				*/
			}
		}catch(Exception e) {
			System.out.println("getArticle()에서 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);//메모리해제 -> 개발자는 메모리를 효율적으로 사용해야 한다.
		}
		return article;//content.jsp에서 getArticle()호출
	}
	//-------------------------중복된 레코드 한개를 담을 수 있는 메서드를 따로 만들어서 처리----------------------
	//접근지정자 private => 왜? 외부에서 호출할 목적이 아니기 때문이다. -> 내부에서 호출할 목적이다.
	private BoardDTO makeArticleFromResult() throws Exception {
		BoardDTO article=new BoardDTO();//MemberDTO            => 왜 BoardDTO를 앞에 적어야만 하는지?
		article.setNum(rs.getInt("num"));//게시물번호
		article.setWriter(rs.getString("writer"));//작성자
		article.setEmail(rs.getString("email"));
		article.setSubject(rs.getString("subject"));
		article.setPasswd(rs.getString("passwd"));
		
		article.setReg_date(rs.getTimestamp("reg_date"));
		article.setReadcount(rs.getInt("readcount"));//0
		article.setRef(rs.getInt("ref"));//그룹번호
		article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
		article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이)
		
		article.setContent(rs.getString("content"));
		article.setIp(rs.getString("ip"));
		
		return article;
	}
	//게시판의 글 수정하기 위한 하나의 레코드를 찾기
	//1)select * from board where num=?
	//글 상세보기 메서드와 동일 => 조회수 증가 부분만 삭제
	public BoardDTO updateGetArticle(int num) {
		BoardDTO article=null;//여러개 얻어오려면? => ArrayList<BoardDTO>articleList=null 한개이상
		try {
			con=pool.getConnection();
			
			sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//찾았다면 레코드 한개를 담기
				article=this.makeArticleFromResult(); //이게 소스코드의 재사용성이다.(중복된 코드를 메서드로 만들어서 불러오는 경우)
			}
		}catch(Exception e) {
			System.out.println("getArticle()에서 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);//메모리해제 -> 개발자는 메모리를 효율적으로 사용해야 한다.
		}
		return article;
	}

	//게시판의 실질적인 글 수정하기 =>본인인증확인절차=>암호맞으면 수정
	//update board set writer=?,subject=?,email=?,,where num=?
	public int updateArticle(BoardDTO article) {
		//boolean check=false;
		String dbpasswd="";//db에서 저장한 글의 암호를 저장
		int x=-1;//게시물의 수정성공유무
		
		try {
			con=pool.getConnection();
			sql="select passwd from board where num=?";
			pstmt=con.prepareStatement(sql); //pstmt sql문장을 돌리기 위해 필요한 객체
			pstmt.setInt(1, article.getNum());//위에서 변수 선언했으면? 그냥 num
			rs=pstmt.executeQuery();
			if(rs.next()) {//찾는 암호가 존재한다면
				dbpasswd=rs.getString("passwd");
				System.out.println("dbpasswd=>"+dbpasswd); //디버깅용 -> 전부 고치고나서 지워야한다.
				//db상의 암호==웹에서 입력한 암호(input box)
				if(dbpasswd.equals(article.getPasswd())) {
					sql="update board set writer=?,subject=?,email=?,content=?,passwd=? where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getEmail());
					pstmt.setString(4, article.getContent());
					pstmt.setString(5, article.getPasswd());
					pstmt.setInt(6, article.getNum());
					int update=pstmt.executeUpdate();
					System.out.println("글수정 성공유무(update)=>"+update);
					x=1;//수정성공유무
					}else {//update=0
					x=0;//암호가 틀린경우
					}
			}//if(rs.next()) 
		}catch(Exception e) {
			System.out.println("updateArticle() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;//updatePro.jsp에서 updateArticle.jsp()호출
	}


	//게시판의 글 삭제하기=>암호를 찾아서 비교(select)
	//delete from border where num=?
	public int deleteArticle(int num,String passwd) {
		
		String dbpasswd="";//db에서 저장한 글의 암호를 저장
		int x=-1;//게시물의 삭제성공유무
		
		try {
			con=pool.getConnection();
			sql="select passwd from board where num=?";
			pstmt=con.prepareStatement(sql); //pstmt sql문장을 돌리기 위해 필요한 객체
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery(); //일반적으로 1이 성공 0이 실패
			if(rs.next()) {//찾는 암호가 존재한다면
				dbpasswd=rs.getString("passwd");
				System.out.println("dbpasswd=>"+dbpasswd); //디버깅용 -> 전부 고치고나서 지워야한다.
				//db상의 암호==웹에서 입력한 암호(input box)
				if(dbpasswd.equals(passwd)) {
					sql="delete from board where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,num);
					int delete=pstmt.executeUpdate();
					System.out.println("글삭제 성공유무(delete)=>"+delete);
					x=1;//삭제성공유무
					}else {//update=0
					x=0;//암호가 틀린경우
					}
			}//if(rs.next()) 
		}catch(Exception e) {
			System.out.println("deleteArticle() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;//deletePro.jsp에서 deleteArticle.jsp()호출
	}
}