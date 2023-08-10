package com.dogwalker.dao;

import org.springframework.stereotype.Repository;

import com.dogwalker.domain.MemberVO;

@Repository
public interface MemberDAO {
	public String login(String id)throws Exception;//로그인 위해서 비밀번호 일치 확인

	public void signup(MemberVO vo)throws Exception; //회원가입 위해서 회원가입정보 전달
	
	
}

