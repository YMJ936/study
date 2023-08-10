package com.dogwalker.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;
// 날짜 오류 뜨면 유틸 데이트로 바꾸기
@Alias("matching")
public class MatchingVO {

	private int m_num;
	private String id;
	private String name;
	private int dog_id;
	private String dog_name;
	private LocalDateTime m_start;
	private LocalDateTime m_end;
	private Date date;
	private int m_level;
	
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDog_id() {
		return dog_id;
	}
	public void setDog_id(int dog_id) {
		this.dog_id = dog_id;
	}
	public String getDog_name() {
		return dog_name;
	}
	public void setDog_name(String dog_name) {
		this.dog_name = dog_name;
	}
	public LocalDateTime getM_start() {
		return m_start;
	}
	public void setM_start(LocalDateTime m_start) {
		this.m_start = m_start;
	}
	public LocalDateTime getM_end() {
		return m_end;
	}
	public void setM_end(LocalDateTime m_end) {
		this.m_end = m_end;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getM_level() {
		return m_level;
	}
	public void setM_level(int m_level) {
		this.m_level = m_level;
	}
	
	public String toString() {
		return "MatchingVO[m_num="+m_num+",id="+id+",name="+name+",dog_id="
				   +dog_id+",dog_name="+dog_name+",m_start="+m_start+",m_end="+m_end+",date="+date+",m_level="+m_level+"]";
	}
}
