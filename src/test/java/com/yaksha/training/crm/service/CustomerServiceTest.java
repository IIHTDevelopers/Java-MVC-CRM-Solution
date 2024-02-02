package com.yaksha.training.crm.service;

import com.yaksha.training.crm.entity.Customer;
import com.yaksha.training.crm.repository.CustomerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.crm.utils.MasterData.asJsonString;
import static com.yaksha.training.crm.utils.MasterData.getCustomer;
import static com.yaksha.training.crm.utils.MasterData.getCustomerList;
import static com.yaksha.training.crm.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.crm.utils.TestUtils.businessTestFile;
import static com.yaksha.training.crm.utils.TestUtils.currentTest;
import static com.yaksha.training.crm.utils.TestUtils.testReport;
import static com.yaksha.training.crm.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetCustomers() throws Exception {
        List<Customer> actual = getCustomerList(5);
        when(customerRepository.findAll()).thenReturn(actual);
        List<Customer> expected = customerService.getCustomers();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveCustomer() throws Exception {
        Customer actual = getCustomer();
        when(customerRepository.save(actual)).thenReturn(actual);
        Customer expected = customerService.saveCustomer(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetCustomer() throws Exception {
        Customer actual = getCustomer();
        when(customerRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Customer expected = customerService.getCustomer(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceDeleteCustomer() throws Exception {
        Customer actual = getCustomer();
        boolean expected = customerService.deleteCustomer(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

    @Test
    public void testServiceSearchCustomerWithNull() throws Exception {
        List<Customer> customers = getCustomerList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Customer> expected = new PageImpl<>(customers);
        when(customerRepository.findByFirstNameAndLastName(null, pageable)).thenReturn(expected);
        Page<Customer> actual = customerService.searchCustomers(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchCustomerWithSearchName() throws Exception {
        String theSearchName = randomStringWithSize(5);
        List<Customer> customers = getCustomerList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Customer> expected = new PageImpl<>(customers);
        when(customerRepository.findByFirstNameAndLastName(theSearchName, pageable)).thenReturn(expected);
        Page<Customer> actual = customerService.searchCustomers(theSearchName, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceUpdateIsVerified() throws Exception {
        Customer actual = getCustomer();
        boolean expected = customerService.updateIsVerified(actual.getId());
        yakshaAssert(currentTest(), (expected), businessTestFile);
    }

}
