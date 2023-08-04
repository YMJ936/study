package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;

@Service
public class BoardDAOImpl extends SqlSessionDaoSupport implements BoardDAO {
	//게시글 리스트 보기
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		List<BoardVO> list=getSqlSession().selectList("selectList");
		System.out.println("ListDAOImpl 테스트중입니다.~");
		return list;
	}
	//글쓰기
	public void insert(BoardVO vo) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertBoard",vo);
	}
	//조회수 증가
	public void updateHit(Integer seq) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateHit",seq);
	}
	//게시글 하나보기
	public BoardVO selectBoard(Integer seq) {
		// TODO Auto-generated method stub
		return (BoardVO)getSqlSession().selectOne("selectBoard",seq);
	}
	//게시글 삭제
	public void delete(Integer seq) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteBoard",seq);
	}

}
