package com.softpro.myapp.dao;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CustomerDao {   // data access object

	@NotEmpty(message="name is required")
	private String name;
	
	private String gender;
	
	@Size(min=20, message="the address should be atleast 20 chars")
	@Size(max=500, message ="the address can not exceed 500 chars")
	private String address;
	
	@NotEmpty(message="contact us required")
	private String contactno;
	
	@NotEmpty(message="Email address is reqiured ")
	private String emailaddress;
	
	@NotEmpty(message="password is required")
	@Size(min=8, message="the address should be atleast 8 chars")
	@Size(max=13, message ="the address can not exceed 13 chars")
	private String password;

	
	private String regdate;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContactno() {
		return contactno;
	}


	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	public String getEmailaddress() {
		return emailaddress;
	}


	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	

}
