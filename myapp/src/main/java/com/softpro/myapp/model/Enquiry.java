package com.softpro.myapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="enquiries")
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length=50)
	private String name;
	
	@Column(nullable = false, length=15)
	private String contactno;
	
	@Column(nullable = false, length=500)
	private String subject;
	
	@Column(nullable = false, length=5000)
	private String message;
	
	@Column(nullable = false, length=30)
	private String posteddate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
