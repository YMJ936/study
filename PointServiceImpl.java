package com.dogwalker.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalker.dao.PointDAO;
import com.dogwalker.domain.PointVO;

@Service("pointService")
public class PointServiceImpl implements PointService {

	@Autowired
	private PointDAO pointDAO;
	
	@Override
	public List<PointVO> pointlist(Map<String, Object> map) {
		return pointDAO.pointlist(map);
	}

	@Override
	public int pointCount(String id) {
		return pointDAO.pointCount(id);
	}

	@Override
	public PointVO getline(int m_num) {
		return pointDAO.getline(m_num);
	}

	@Override
	public int getmaxp_num(String id) {
		return pointDAO.getmaxp_num(id);
	}

	@Override
	public void newline(PointVO point) {
		pointDAO.newline(point);
	}
	
	@Override
	public int getnowbalance(String id) {
		return pointDAO.getnowbalance(id);
	}
}
