package com.dogwalker.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dogwalker.domain.PointVO;

@Repository
public class PointDAOImpl implements PointDAO {

	@Autowired
    private SqlSession sqlSession;
	
	private static final String NAMESPACE = "DogcoinPoint";
	
	@Override
	public List<PointVO> pointlist(Map<String, Object> map) {
		return sqlSession.selectList(NAMESPACE + ".pointlist", map);
	}

	@Override
	public int pointCount(String id) {
		return sqlSession.selectOne(NAMESPACE + ".pointCount", id);
	}

	@Override
	public PointVO getline(int m_num) {
		return sqlSession.selectOne(NAMESPACE + ".getline", m_num);
	}

	@Override
	public int getmaxp_num(String id) {
		return sqlSession.selectOne(NAMESPACE + ".getmaxp_num", id);
	}

	@Override
	public void newline(PointVO point) {
		sqlSession.selectOne(NAMESPACE + ".newline", point);
	}
	
	@Override
	public int getnowbalance(String id) {
		return sqlSession.selectOne(NAMESPACE + ".getnowbalance", id);
	}
	
	
}
