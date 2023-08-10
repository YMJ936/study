package com.dogwalker.domain;

import java.util.Date;

public class ItemVO {
	
	private int it_num;
	private String it_name;
	private	String it_img;
	private String it_content;
	private int it_price;
	private Date it_rdate;
	private int it_cnt;
	private int it_deleted;
	private String catecode;
	
	public int getIt_num() {
		return it_num;
	}
	public void setIt_num(int it_num) {
		this.it_num = it_num;
	}
	public String getIt_name() {
		return it_name;
	}
	public void setIt_name(String it_name) {
		this.it_name = it_name;
	}
	public String getIt_img() {
		return it_img;
	}
	public void setIt_img(String it_img) {
		this.it_img = it_img;
	}
	public String getIt_content() {
		return it_content;
	}
	public void setIt_content(String it_content) {
		this.it_content = it_content;
	}
	public int getIt_price() {
		return it_price;
	}
	public void setIt_price(int string) {
		this.it_price = string;
	}
	public Date getIt_rdate() {
		return it_rdate;
	}
	public void setIt_rdate(Date it_rdate) {
		this.it_rdate = it_rdate;
	}
	public int getIt_cnt() {
		return it_cnt;
	}
	public void setIt_cnt(int it_cnt) {
		this.it_cnt = it_cnt;
	}
	public int getIt_deleted() {
		return it_deleted;
	}
	public void setIt_deleted(int it_deleted) {
		this.it_deleted = it_deleted;
	}
	
	public String getCatecode() {
		return catecode;
	}
	public void setCatecode(String catecode) {
		this.catecode = catecode;
	}
	
	@Override
	public String toString() {
		return "ItemVO [it_num=" + it_num + ", it_name=" + it_name + ", it_img=" + it_img + ", it_content=" + it_content
				+ ", it_price=" + it_price + ", it_rdate=" + it_rdate + ", it_cnt=" + it_cnt + ", it_deleted=" + it_deleted
				+ ", catecode=" + catecode + "]";
	}
}
