package com.softpro.myapp.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softpro.myapp.model.Response;

public interface ResponseRepository extends JpaRepository<Response, Integer>{
	@Query("SELECT r FROM Response r WHERE r.responsetype = :responsetype")
	List<Response> findResponseByResponseType(@Param("responsetype")String responsetype);
}
