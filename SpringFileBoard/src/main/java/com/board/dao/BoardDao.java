package com.board.dao;

//자료실의 목록보기
//List(레코드 여러개 담을 객체),검색분야,검색어(Map객체 이용) 
import java.util.List;
import java.util.Map;

import com.board.domain.BoardCommand;
public interface BoardDao {
	//1.글목록보기
	public List<BoardCommand> list (Map<String,Object> map);
	
	//2.총레코드수(검색어에 맞는 레코드수까지 계산)
	public int getRowCount(Map<String,Object> map);
	
	//3.최대값 번호 구하기( 글 번호를 최신글의 번호에서 +1해서 들어가도록)
	public int getNewSeq();
	
	//4.자료실이 글쓰기 및 업로드까지
	public void insert(BoardCommand board);

	//5.글상세보기
	public BoardCommand selectBoard(Integer seq);//~(int seq)
	
	//6.자료실번호에 해당하는 조회수 증가
	public void updateHit(Integer seq);//~(int seq)
	
}
