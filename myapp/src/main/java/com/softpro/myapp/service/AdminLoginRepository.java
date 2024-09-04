package com.softpro.myapp.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softpro.myapp.model.AdminLogin;

public interface AdminLoginRepository extends JpaRepository<AdminLogin, String> {

}
