package ymj.board;

// DBConnectionMgr (DB접속)
// BoardDTO (매개변수, 반환형으로 사용 또는 데이터를 담는역할(출력))

import java.sql.*;
import java.util.*; // ArrayList
public class BoardDAO {
	// 10개 생성-> 커넥션풀(pool)
	private DBConnectionMgr pool = null; // 1.연결할 클래스객체 선언
	// 공통
	private Connection con = null; // 대여
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // select
	private String sql = ""; // 실행시킬 sql 구문 저장용
	
	// 2. 생성자를 통해서 연결 -> 서로 연결
	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		}catch (Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}finally {
			
		}
	}
	// 3. 웹상에서 호출할 메서드를 요구분석
	// 1) 페이징 처리를 위한 전체 레코드수를 구해와야 한다.
	// select count(*) from board; // 반환형 O (int), where조건 X (매개변수가없다) 
	public int getArticleCount() { // getMemberCount()->MemberDAO
		int x = 0; // 총레코드수
		
		try {
			con = pool.getConnection(); // pool객체를 얻어와야만 getConnection 메서드 호출가능
			System.out.println("con=>"+con);
			sql = "select count(*) from board"; // "select count(*) from member"; 필드명만 바꾸면 됨
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
				// 변수명=rs.get자료형(필드명 or 인덱스번호)
				// count(*)은 그룹함수지 필드명이 아니다. 필드명이 없기때문에 인덱스번호로 불러와야된다. select와 from 사이의 값을 인덱스번호로 매길수있음
			}
		}catch (Exception e) {
			System.out.println("getArticleCount 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//------------------------------------------------------------------------------------
	// 게시판의 레코드수를 검색어에 띠른 메서드작성 (검색분야,검색어)
	public int getArticleSearchCount(String search, String searchtext) {
		int x = 0; // 총레코드수
		
		try {
			con = pool.getConnection(); // pool객체를 얻어와야만 getConnection 메서드 호출가능
			System.out.println("con=>"+con);
			//-----------------------------------
			if(search==null || search=="") { // 검색분야 선택 X라면
				sql = "select count(*) from board";
			}else { // 검색분야 선택 O라면 (제목,작성자,제목+본문)
				if(search.equals("subject_content")) {
					sql = "select count(*) from board where subject like '%"+searchtext+"%' or content like '%"+searchtext+"%'";
				}else { // 제목,작성자 -> 매개변수를 이용해서 하나의 sql 통합
					sql = "select count(*) from board where "+search+" like '%"+searchtext+"%' ";
				}
			}
			System.out.println("getArticleSearchCount의 검색어 sql=>"+sql);
			//-----------------------------------
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
				// 변수명=rs.get자료형(필드명 or 인덱스번호)
				// count(*)은 그룹함수지 필드명이 아니다. 필드명이 없기때문에 인덱스번호로 불러와야된다. select와 from 사이의 값을 인덱스번호로 매길수있음
			}
		}catch (Exception e) {
			System.out.println("getArticleCount 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//------------------------------------------------------------------------------------
	// 2) 글목록보기에 대한 메서드구현 => 레코드한개이상 -> 한페이지당 10개씩
	// "select * from board order by ref desc, re_step limit ?,?"
	// 첫번째 물음표값은 레코드 시작번호, 두번째 물음표값은 불러올 레코드의 갯수
	public List getArticles (int start, int end) {
		List articleList = null; // ArrayList<BoardDTO> articleList=null; 이렇게 써도됨
		/*
		 * ref(그룹번호) => num와 같은 기능
		 * 그룹번호가 가장 최근인 글을 중심으로 정렬하되,   
		 * 답변글이 나오는 순서는 오름차순으로 정렬하라
		 * 몇번째 레코드번호를 기준으로 해서 몇개까지 정렬할것인가를 지정해주는 SQL 구문
		 */
		try {
			con = pool.getConnection();
			sql="select * from board order by ref desc, re_step limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start-1); // mysql은 레코드순번 내부적으로 0부터시작
			pstmt.setInt(2, end); // 불러와서 담을 갯수(10,15,20,,,)
			rs = pstmt.executeQuery();
			// 누적의 개념
			if (rs.next()) {// 보여주는 레코드가 이미 있다면
				articleList = new ArrayList(end); // end 갯수만큼 데이터공간 생성
				do {
					/*
					BoardDTO article = new BoardDTO(); // MemberDTO와 같은개념
					article.setNum(rs.getInt("num")); // 게시물번호
					article.setWriter(rs.getString("writer")); 
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					
					article.setReg_date(rs.getTimestamp("reg_date")); // 날짜
					article.setReadcount(rs.getInt("readcount")); // 조회수 0
					article.setRef(rs.getInt("ref")); // 그룹번호
					article.setRe_step(rs.getInt("re_step")); // 답변글이 나오는 순서
					article.setRe_level(rs.getInt("re_level")); // 들여쓰기(답변의 깊이depth)
					
					article.setContent(rs.getString("content")); // 글내용
					article.setIp(rs.getString("ip")); // 글쓴이 ip주소
					*/
					BoardDTO article = makeArticleFromResult();
					// 추가
					articleList.add(article); // 생략시 데이터저장불가 꼭 넣을것! for문에서 null값 떨어짐
					
				}while (rs.next());
			}
		}catch (Exception e) {
			System.out.println("getArticles() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return articleList; // 최대 10개=> list.jsp => for문=> 필드출력
	}
	// 게시판의 글쓰기 및 답변달기
	// insert into board values(?,,,
	public void insertArticle(BoardDTO article) {
		// 1.article -> 신규글인지 답변글인지 구분
		int num = article.getNum(); // 0->신규글, 0이아닌경우->답변글
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		
		int number = 0; // 데이터를 저장하기위한 게시물번호 (초기값을 넣어줘야함)
		System.out.println("insertArticle의 내부 num=>"+num);
		System.out.println("ref=>"+ref+", re_step=>"+re_step+", re_level"+re_level);
		
		try {
			con = pool.getConnection();
			sql = "select max(num) from board"; // 입력x 계산 o
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1)+1; // max(num) 그룹함수라 값을 인덱스번호로 넣어줌 / 최대값+1
			}else { // 데이터가 없는 경우 0
				number = 1;
			}
			// 답변글인지 신규글인지
			if (num != 0) { // 양수이면서 1이상 (답변글)
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				// 나와 그룹번호는 같으면서 나보다 re_step값이 큰값을 찾아서 스텝값을 하나 증가 (ex) 2->3, 4->5, 5->6...)
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref); // 같은 그룹번호
				pstmt.setInt(2, re_step);
				int update = pstmt.executeUpdate(); // executedQuery 는 select인경우에만 사용함 / 1 성공 0 실패
				System.out.println("댓글수정유무(update)=>"+update);
				// 답변글 ///////////////
				re_step = re_step+1;
				re_level = re_level+1;
				/////////////////////// 이부분 빼먹으면 답변글안생김
			}else { // num==0 신규글인경우
				ref = number; // 1,2,3,,,
				re_step = 0; // 답변순서
				re_level = 0; // 들여쓰기 X
			}
			// 12개 필드 -> num, reg_date, readcoundt(생략)->0
			// 작성날짜 -> 오라클은 sysdate, mysql은 now()
			sql = "insert into board(writer,email,subject,passwd,";
			sql += " reg_date,ref,re_step,re_level,content,ip) ";
			sql += "values(?,?,?,?,?,?,?,?,?,?)";
			//sql += "values(?,?,?,?,now(),?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter()); // 웹상에서 이미 저장된 상태
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date()); // sql문장 5번째에 아예 now()를 쓰면 이 문장이 생략가능함.
			
			//pstmt.setInt(6, article.getRef()); 이 구문은 사용 XXX 기존의 데이터를가져오는게아님
			// 기존의 입력받은 데이터를 가져오는건 article.~ 으로 데이터 불러옴
			pstmt.setInt(6, ref); // 계산해서 넣어주는 ref 사용. 현재시점에서 게시물번호 5
			pstmt.setInt(7, re_step); // 0
			pstmt.setInt(8, re_level); // 0
			//-------------------------------------요 윗부분 잘 알아두기
			pstmt.setString(9, article.getContent()); // 내용
			pstmt.setString(10, article.getIp()); // request.getRemoteAddr();
			
			int insert = pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기성공유무(insert)=>"+insert);
			
		}catch (Exception e) {
			System.out.println("insertArticle()에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
		// insertArticle
		
		// 게시판의 글상세보기 => 하나의 레코드를 찾기
		// content.jsp?num=3&pageNum=1
		// 형식) select * from board where num=3
		public BoardDTO getArticle (int num) { // 반환값 여러개인경우 DTO 사용해서 하나로 담는다
			BoardDTO article = null; // ArrayList<BoardDTO> articleList = null; 한개이상
			try {
				con = pool.getConnection();
				// 1.조회수증가
				sql = "update board set readcount=readcount+1 where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				int update = pstmt.executeUpdate();
				System.out.println("조회수 증가유무(update)=>"+update); // 1
				// 2.조회수증가된것을 찾아서 웹에 출력
				sql = "select * from board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				// select는 executeQuery (테이블구조 영향X) <-> 테이블구조 영향 O executeUpdate-> update,alter,drop,,,
				
				if (rs.next()) { // 찾았다면 레코드한개를 담기
					article = makeArticleFromResult();
					
					/*
					article = new BoardDTO(); // MemberDTO와 같은개념
					article.setNum(rs.getInt("num")); // 게시물번호
					article.setWriter(rs.getString("writer")); 
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					
					article.setReg_date(rs.getTimestamp("reg_date")); // 날짜
					article.setReadcount(rs.getInt("readcount")); // 조회수 0
					article.setRef(rs.getInt("ref")); // 그룹번호
					article.setRe_step(rs.getInt("re_step")); // 답변글이 나오는 순서
					article.setRe_level(rs.getInt("re_level")); // 들여쓰기(답변의 깊이depth)
					
					article.setContent(rs.getString("content")); // 글내용
					article.setIp(rs.getString("ip")); // 글쓴이 ip주소
					*/
				}
					
			}catch (Exception e) {
				System.out.println("getArticle() 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return article; // content.jsp에서 getArticle() 호출
		}
		// -------중복된 레코드 한개를 담을수있는 메서드를 따로 만들어서 처리-------
		// 접근지정자 private => 왜? 외부에서 호출목적 X 내부에서 호출목적 O
		private BoardDTO makeArticleFromResult () throws Exception {
			BoardDTO article = new BoardDTO(); // MemberDTO와 같은개념
			article.setNum(rs.getInt("num")); // 게시물번호
			article.setWriter(rs.getString("writer")); 
			article.setEmail(rs.getString("email"));
			article.setSubject(rs.getString("subject"));
			article.setPasswd(rs.getString("passwd"));
			
			article.setReg_date(rs.getTimestamp("reg_date")); // 날짜
			article.setReadcount(rs.getInt("readcount")); // 조회수 0
			article.setRef(rs.getInt("ref")); // 그룹번호
			article.setRe_step(rs.getInt("re_step")); // 답변글이 나오는 순서
			article.setRe_level(rs.getInt("re_level")); // 들여쓰기(답변의 깊이depth)
			
			article.setContent(rs.getString("content")); // 글내용
			article.setIp(rs.getString("ip")); // 글쓴이 ip주소
			
			return article;
		}
		
		// 게시판의 글수정하기위한 하나의 레코드를 찾기
		// 1)select * from board where num=?
		// 글상세보기 메서드와 동일 => 조회수증가 부분만 삭제
		public BoardDTO updateGetArticle (int num) {
			BoardDTO article = null; // ArrayList<BoardDTO> articleList = null; 한개이상
			try {
				con = pool.getConnection();
				
				sql = "select * from board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				// 소스코드의 재사용성 (중복된 코드를 메서드로 만들어서 불러오는 경우)
				if (rs.next()) { // 찾았다면 레코드한개를 담기
					article = makeArticleFromResult();
				}
				}catch (Exception e) {
					System.out.println("updateGetArticle() 에러유발=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return article; // ${article.num},,,
		}
		// 게시판의 실질적인 글수정하기 => 본인인증확인절차 => 암호맞으면 수정
		// update board set writer=?,subject=?,email=?,,, where num=?
		public int updateArticle (BoardDTO article) {
			//boolean check = false;
			String dbpasswd = ""; // db에서 저장한 글의 암호를 저장
			int x = -1; // 게시물의 수정성공유무
			
			try {
				con = pool.getConnection();
				sql = "select passwd from board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, article.getNum()); // num
				rs = pstmt.executeQuery();
				if (rs.next()) { // 찾는 암호가 존재한다면
					dbpasswd = rs.getString("passwd");
					System.out.println("dbpasswd=>"+dbpasswd);
					// db상의 암호 == 웹에서 입력한암호(input box) 같은지 확인
					if (dbpasswd.equals(article.getPasswd())) {
						sql = "update board set writer=?,subject=?,email=?,content=?,passwd=? where num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, article.getWriter());
						pstmt.setString(2, article.getSubject());
						pstmt.setString(3, article.getEmail());
						pstmt.setString(4, article.getContent());
						pstmt.setString(5, article.getPasswd());
						pstmt.setInt(6, article.getNum());
						int update = pstmt.executeUpdate();
						System.out.println("글수정 성공유무(update)=>"+update);
						x = 1;// 수정성공유무
					}else {
						x = 0;// 암호틀린경우
					}
				} // if (rs.next())
			}catch (Exception e) {
				System.out.println("updateArticle() 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x; // updatePro.jsp에서 updateArticle() 호출
		}
		
		// 게시판의 글삭제하기 => 암호찾아서 비교
		// delete from board where num=?
		public int deleteArticle(int num, String passwd) {
			
			String dbpasswd = ""; // db에서 저장한 글의 암호를 저장
			int x = -1; // 게시물의 삭제성공유무
			
			try {
				con = pool.getConnection();
				sql="select passwd from board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) { // 찾는 암호가 존재한다면
					dbpasswd = rs.getString("passwd");
					System.out.println("dbpasswd=>"+dbpasswd);
					// db상의 암호 == 웹에서 입력한암호(input box) 같은지 확인
					if(dbpasswd.equals(passwd)) {
						sql="delete from board where num=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, num);
						int delete = pstmt.executeUpdate();
						System.out.println("글삭제 성공유무(delete)=>"+delete);
						x = 1;// 삭제성공유무
					}else {
						x = 0;// 암호틀린경우
					}
				} // if (rs.next())
			}catch (Exception e) {
				System.out.println("deleteArticle() 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x; // deletePro.jsp에서 deleteArticle() 호출
		}
			
	}
	
	

