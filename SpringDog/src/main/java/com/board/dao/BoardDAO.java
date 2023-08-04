package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.BoardVO;

public interface BoardDAO {

	//1.글 목록보기
	public List<BoardVO> list();
	
	//2.글쓰기
	public void insert(BoardVO vo);
	
	//3.조회수 증가하기
	public void updateHit(Integer seq);
	
	//4.글 상세보기
	public BoardVO selectBoard(Integer seq);
	
	//5.글 삭제하기
	public void delete(Integer seq);
	
}
