package com.dogwalker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogwalker.dao.MemberDAO;
import com.dogwalker.domain.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;
	
	@Override
	public String login(String id) throws Exception {

		return dao.login(id);
	}
	
	
	  @Override public void signup(MemberVO vo) throws Exception {
	  
	  dao.signup(vo); 
	  }
	 
}
