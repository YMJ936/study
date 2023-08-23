package com.dogwalker.service;

import java.util.List;
import java.util.Map;

import com.dogwalker.domain.PointVO;

public interface PointService {

	//리스트띄우기
		public List<PointVO> pointlist(Map<String,Object>map);
		
		//총레코드수 구하기
		public int pointCount(String id);
		
		//한행만 가져오기
		public PointVO getline(int m_num);
		
		//p_num의 최대값 구하기(가장 최신 레코드 가져오기)
		public int getmaxp_num(String id);
		
		//레코드 추가
		public void newline(PointVO point);
		
		//id로 잔액 구하기
		public int getnowbalance(String id);
		
}
