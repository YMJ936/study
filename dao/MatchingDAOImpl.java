package com.dogwalker.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dogwalker.domain.MatchingVO;

@Repository
public class MatchingDAOImpl implements MatchingDAO {

	@Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "Matching";
    
	@Override
	public List<MatchingVO> mList(Map<String, Object> map) {
		return sqlSession.selectList(NAMESPACE + ".selectList", map);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE + ".selectCount", map);
	}

	@Override
	public MatchingVO selectMatching(Integer m_num) {
		return sqlSession.selectOne(NAMESPACE + ".selectLine", m_num);
	}

	@Override
	public int getNewM_num() {
		return sqlSession.selectOne(NAMESPACE + ".getNewM_num");
	}

	@Override
	public void mRequest(MatchingVO matching) {
		sqlSession.insert(NAMESPACE + ".requestMatching", matching);
	}

	@Override
	public void mLevelUp(MatchingVO matching) {
		sqlSession.update(NAMESPACE + ".levelUp", matching);
	}

	@Override
	public void mReject(Integer m_num) {
		sqlSession.delete(NAMESPACE + ".rejectMatching", m_num);
	}

	@Override
	public void mCancel(MatchingVO matching) {
		sqlSession.update(NAMESPACE + ".cancelWalking", matching);
	}
	
	@Override
	public void mTruc(MatchingVO matching) {
		sqlSession.delete(NAMESPACE + ".trucMatching", matching);
	}
	
	@Override
	public int idtodog_id(String id) {
		return sqlSession.selectOne(NAMESPACE + ".idtodog_id", id);
	}

}
