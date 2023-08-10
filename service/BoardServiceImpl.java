package com.dogwalker.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalker.dao.BoardDAO;
import com.dogwalker.domain.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;
	
	@Autowired
	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO=boardDAO;
	}
	
	@Override
	public List<BoardVO> list(Map<String, Object> map) {
		return boardDAO.list(map);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return boardDAO.getRowCount(map);
	}

	@Override
	public int getNewSeq() {
		return boardDAO.getNewSeq();
	}

	@Override
	public void insert(BoardVO board) {
		boardDAO.insert(board);
	}

	@Override
	public BoardVO selectBoard(Integer seq) {
		return boardDAO.selectBoard(seq);
	}

	@Override
	public void updateHit(Integer seq) {
		boardDAO.updateHit(seq);
	}

	@Override
	public void update(BoardVO board) {
		boardDAO.update(board);
	}

	@Override
	public void delete(Integer seq) {
		boardDAO.delete(seq);
	}

}
