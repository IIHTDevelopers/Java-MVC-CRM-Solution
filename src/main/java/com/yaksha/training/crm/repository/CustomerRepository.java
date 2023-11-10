package com.yaksha.training.crm.repository;

import com.yaksha.training.crm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "Select c from Customer c where lower(firstName) like %:keyword% or lower(lastName) like %:keyword%")
    List<Customer> findByFirstNameAndLastName(@Param("keyword") String keyword);
}
