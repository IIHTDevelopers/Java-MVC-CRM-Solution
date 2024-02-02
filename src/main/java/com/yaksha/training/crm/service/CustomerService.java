package com.yaksha.training.crm.service;

import com.yaksha.training.crm.entity.Customer;
import com.yaksha.training.crm.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }

    public boolean deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return true;
    }

    public Page<Customer> searchCustomers(String theSearchName, Pageable pageable) {
        theSearchName = theSearchName != null && theSearchName.trim().length() > 0 ? theSearchName : null;
        return customerRepository.findByFirstNameAndLastName(theSearchName, pageable);
    }

    public boolean updateIsVerified(Long id) {
        customerRepository.updateIsVerified(id);
        return true;
    }

}
