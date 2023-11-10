package com.yaksha.training.crm.service;

import com.yaksha.training.crm.entity.Customer;
import com.yaksha.training.crm.repository.CustomerRepository;
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

    public List<Customer> searchCustomers(String theSearchName) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return customerRepository.findByFirstNameAndLastName(theSearchName);
        } else {
            return customerRepository.findAll();
        }
    }
}
