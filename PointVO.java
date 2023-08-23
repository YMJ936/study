package com.dogwalker.domain;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("Coinpoint")
public class PointVO {

	private int p_num; //PK
	private String id; //포인트 보유자 아이디
	private Timestamp p_time; //포인트가 변동된 시간
	private String p_type; //증가 'p' 감소 'm' 충전 'c'
	private int p_amount; //변동액수
	private int p_balance;	//잔액
	//matching 테이블의 컬럼
	private int m_duration; //산책시간
	//테이블의 컬럼X
	private int p_camount; //충전액수
	
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getP_time() {
		return p_time;
	}
	public void setP_time(Timestamp p_time) {
		this.p_time = p_time;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public int getP_amount() {
		return p_amount;
	}
	public void setP_amount(int p_amount) {
		this.p_amount = p_amount;
	}
	public int getP_balance() {
		return p_balance;
	}
	public void setP_balance(int p_balance) {
		this.p_balance = p_balance;
	}
	public int getM_duration() {
		return m_duration;
	}
	public void setM_duration(int m_duration) {
		this.m_duration = m_duration;
	}
	public int getP_camount() {
		return p_camount;
	}
	public void setP_camount(int p_camount) {
		this.p_camount = p_camount;
	}
	
	@Override
	public String toString() {
		return "PointVO [p_num=" + p_num + ", id=" + id + ", p_time=" + p_time + ", p_type=" + p_type + ", p_amount="
				+ p_amount + ", p_balance=" + p_balance + ", m_duration=" + m_duration + ", p_camount=" + p_camount
				+ "]";
	}
	
	
	
}
