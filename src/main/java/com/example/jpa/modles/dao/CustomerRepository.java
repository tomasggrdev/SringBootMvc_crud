package com.example.jpa.modles.dao;

import com.example.jpa.modles.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<Customer,Long> {




}
