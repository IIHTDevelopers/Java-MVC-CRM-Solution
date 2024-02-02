package com.yaksha.training.crm.repository;

import com.yaksha.training.crm.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "Select c from Customer c "
            + "where (:keyword IS NULL "
            + "OR lower(firstName) like %:keyword% "
            + "OR lower(lastName) like %:keyword%)")
    Page<Customer> findByFirstNameAndLastName(@Param("keyword") String keyword, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE customer c set c.verify = 1 where c.id = :id",
            nativeQuery = true)
    void updateIsVerified(@Param("id") Long id);

}
