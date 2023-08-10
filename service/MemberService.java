package com.dogwalker.service;

import com.dogwalker.domain.MemberVO;

public interface MemberService {
	
	public String login(String id)throws Exception;//로그인 기능
	
	 public void signup(MemberVO vo)throws Exception; //회원가입 기능
}
