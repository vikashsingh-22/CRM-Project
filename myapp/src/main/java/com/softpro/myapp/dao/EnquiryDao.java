package com.softpro.myapp.dao;

import jakarta.validation.constraints.NotEmpty;

public class EnquiryDao {
	
	@NotEmpty(message="name is required")
	private String name;
	
	@NotEmpty(message="contactno is required")
	private String contactno;
	
	@NotEmpty(message="subject is required")
	private String subject;
	
	@NotEmpty(message="message is required")
	private String message;
	
	private String posteddate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPosteddate() {
		return posteddate;
	}

	public void setPosteddate(String posteddate) {
		this.posteddate = posteddate;
	}
	

}
