package com.dogwalker.service;

//List(레코드 여러개 담을 객체),Map(검색분야,검색어)
import java.util.List;
import java.util.Map;

import com.dogwalker.domain.BoardVO;

public interface BoardService {

	//1.글목록보기
	public List<BoardVO> list(Map<String,Object>map);
	
	//2.총레코드수(검색어에 맞는 레코드수까지 포함)
	public int getRowCount(Map<String,Object>map);
	
	//3.최대값 번호 구하기
	public int getNewSeq();
	
	//4.자료실의 글쓰기
	public void insert(BoardVO board);
	
	//5.글상세보기
	public BoardVO selectBoard(Integer seq);//~(int seq)
	
	//6.게시물번호에 해당하는 조회수 증가
	public void updateHit(Integer seq);
	
	//7.글수정하기
	public void update(BoardVO board);
	
	//8.글삭제하기
	public void delete(Integer seq);
}
