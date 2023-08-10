package com.dogwalker.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalker.dao.MatchingDAO;
import com.dogwalker.domain.MatchingVO;

@Service("matchingService")
public class MatchingServiceImpl implements MatchingDAO {

	@Autowired
	private MatchingDAO matchingDAO;
	
	
	/*@Autowired 
	public MatchingServiceImpl(MatchingDAO matchingDAO) {
		 this.matchingDAO=matchingDAO; 
	}*/
	 
	@Override
	public List<MatchingVO> mList(Map<String, Object> map) {
		return matchingDAO.mList(map);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return matchingDAO.getRowCount(map);
	}

	@Override
	public MatchingVO selectMatching(Integer m_num) {
		return matchingDAO.selectMatching(m_num);
	}

	@Override
	public int getNewM_num() {
		return matchingDAO.getNewM_num();
	}

	@Override
	public void mRequest(MatchingVO matching) {
		matchingDAO.mRequest(matching);
	}

	@Override
	public void mLevelUp(MatchingVO matching) {
		matchingDAO.mLevelUp(matching);
	}

	@Override
	public void mReject(Integer m_num) {
		matchingDAO.mReject(m_num);
	}

	@Override
	public void mCancel(MatchingVO matching) {
		matchingDAO.mCancel(matching);
	}
	
	@Override
	public void mTruc(MatchingVO matching) {
		matchingDAO.mTruc(matching);
	}
	
	@Override
	public int idtodog_id(String id) {
		return matchingDAO.idtodog_id	(id);
	}
	
}
