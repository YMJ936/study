package com.dogwalker.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dogwalker.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSession session;
	
	private String namespace = "Member";
	
	@Override
	public String login(String id) throws Exception {

		String pw = session.selectOne(namespace+".login",id);//해당 아이디가 가진 암호를 가져옴
		return pw;
	}
	
	 @Override
	  public void signup(MemberVO vo) throws Exception {
	  
	  session.insert(namespace+".signup",vo);//회원가입을 위해 아이디 생성 
	  };
	 
}
