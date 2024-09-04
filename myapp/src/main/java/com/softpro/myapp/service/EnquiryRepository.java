package com.softpro.myapp.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softpro.myapp.model.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
	
}
