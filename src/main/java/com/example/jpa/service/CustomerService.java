package com.example.jpa.service;

import com.example.jpa.modles.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    void deleteById(Long id);
    void save(Customer customer);
}
