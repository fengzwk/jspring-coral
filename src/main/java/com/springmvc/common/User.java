package com.springmvc.common;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonView;

public class User implements Serializable{
	
	public interface BaseView{};
	
	public interface SubView extends BaseView{}
	
	private String username;
	
	private int age;
	
	private String address;
	
	private Date birthday;

	@JsonView(BaseView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(BaseView.class)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@JsonView(BaseView.class)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
 
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", address=" + address + "]";
	}
}
