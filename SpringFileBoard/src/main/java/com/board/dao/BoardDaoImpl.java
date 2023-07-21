package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.board.domain.BoardCommand;
//getSqlSession()->SqlSession객체를 자동으로 반환
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {

	public List<BoardCommand> list(Map<String, Object> map) {
		//selectList("실행시킬 sql구문 id",매개변수)
		List <BoardCommand> list=getSqlSession().selectList("selectList",map);
		return list;
	}

	public int getRowCount(Map<String, Object> map) {
		//selectOne -> 레코드 한개만가져올때도 쓰지만,특정필드(String,int,,,,)를 가져올때도 씀
		return getSqlSession().selectOne("selectCount",map);
	}

	public int getNewSeq() {
		//Object->Integer->int
		return getSqlSession().selectOne("getNewSeq");
	}
	//자료실 글쓰기
	public void insert(BoardCommand board) {
		getSqlSession().insert("insertBoard",board);	
		
	}
	public BoardCommand selectBoard(Integer seq) {
		//(BoardCommand)getSq~로 형변환해서 반환해야되지만 이제는 자동으로 변환해서 안해줘도됨
		return getSqlSession().selectOne("selectBoard",seq);
	}
	
	public void updateHit(Integer seq) {
		
		getSqlSession().update("updateHit",seq);
	}
}
