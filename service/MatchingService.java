package com.dogwalker.service;

import java.util.List;
import java.util.Map;

import com.dogwalker.domain.MatchingVO;

public interface MatchingService {

	//테이블전체 가져오기
	public List<MatchingVO> mList(Map<String,Object>map); 
	
	//총 레코드 수 구하기
	public int getRowCount(Map<String,Object>map);
	
	//한행만 가져오기
	public MatchingVO selectMatching(Integer m_num);
	
	//최대값 구하기
	public int getNewM_num();
	
	//예약신청
	public void mRequest(MatchingVO matching);
	
	//예약승인, 산책시작, 산책완료
	public void mLevelUp(MatchingVO matching);
	
	//확정된 산책 취소하기
	public void mCancel(MatchingVO matching);
	
	//예약신청 거절하기
	public void mReject(Integer m_num);
	
	//예약시 같은날 나머지 예약 취소시키는 SQL문
	public void mTruc(MatchingVO matching);
	
	//아이디를 가지고 도그아이디를 불러오기
	public int idtodog_id(String id);
	
}
