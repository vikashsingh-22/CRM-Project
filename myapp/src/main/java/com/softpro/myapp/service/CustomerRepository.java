package com.softpro.myapp.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softpro.myapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
