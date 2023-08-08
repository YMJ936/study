package com.dogwalker.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dogwalker.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "Board"; // BoardMapper의 namespace와 일치해야 함

    @Override
    public List<BoardVO> list(Map<String, Object> map) {
        return sqlSession.selectList(NAMESPACE + ".selectList", map);
    }

    @Override
    public int getRowCount(Map<String, Object> map) {
        return sqlSession.selectOne(NAMESPACE + ".selectCount", map);
    }

    @Override
    public int getNewSeq() {
        return sqlSession.selectOne(NAMESPACE + ".getNewSeq");
    }

    @Override
    public void insert(BoardVO board) {
        sqlSession.insert(NAMESPACE + ".insertBoard", board);
    }

    @Override
    public BoardVO selectBoard(Integer seq) {
        return sqlSession.selectOne(NAMESPACE + ".selectBoard", seq);
    }

    @Override
    public void updateHit(Integer seq) {
        sqlSession.update(NAMESPACE + ".updateHit", seq);
    }

    @Override
    public void update(BoardVO board) {
        sqlSession.update(NAMESPACE + ".updateBoard", board);
    }

    @Override
    public void delete(Integer seq) {
        sqlSession.delete(NAMESPACE + ".deleteBoard", seq);
    }
}
