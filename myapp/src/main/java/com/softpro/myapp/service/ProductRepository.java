package com.softpro.myapp.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softpro.myapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
