package com.dogwalker.domain;

import org.apache.ibatis.type.Alias;

@Alias("dog")
public class DogVO {

	private String id;
	private int dog_id;
	private String dog_name;
	private int dog_age;
	private String dog_breed;
	private String dog_favtime;
	private String dog_photo;
	//dw_member 테이블 변수들 -> 마커를 찍기 위함
	private String address;
	private String address_detail;
	private String latitude;
	private String longitude;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getDog_age() {
		return dog_age;
	}
	public void setDog_age(int dog_age) {
		this.dog_age = dog_age;
	}
	public String getDog_breed() {
		return dog_breed;
	}
	public void setDog_breed(String dog_breed) {
		this.dog_breed = dog_breed;
	}
	public String getDog_favtime() {
		return dog_favtime;
	}
	public void setDog_favtime(String dog_favtime) {
		this.dog_favtime = dog_favtime;
	}
	public String getDog_photo() {
		return dog_photo;
	}
	public void setDog_photo(String dog_photo) {
		this.dog_photo = dog_photo;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String toString() {
		return "DogVO[id="+id+",dog_id="+dog_id+",dog_name="+dog_name+",dog_age="
				   +dog_age+",dog_breed="+dog_breed+",dog_favtime="+dog_favtime+",dog_photo="+dog_photo+
				   ",address="+address+",address_detail="+address_detail+",latitude="+latitude+",longitude="+longitude+"]";
	}
}
