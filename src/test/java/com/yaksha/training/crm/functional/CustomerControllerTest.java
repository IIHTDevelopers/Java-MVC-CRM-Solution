package com.yaksha.training.crm.functional;

import com.yaksha.training.crm.controller.CustomerController;
import com.yaksha.training.crm.entity.Customer;
import com.yaksha.training.crm.service.CustomerService;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.crm.utils.MasterData.getCustomer;
import static com.yaksha.training.crm.utils.MasterData.getCustomerList;
import static com.yaksha.training.crm.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.crm.utils.TestUtils.businessTestFile;
import static com.yaksha.training.crm.utils.TestUtils.currentTest;
import static com.yaksha.training.crm.utils.TestUtils.testReport;
import static com.yaksha.training.crm.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerListCustomersHome() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Customer> customers = getCustomerList(5);
        Page<Customer> customerPage = new PageImpl<>(customers);
        when(customerService.searchCustomers(null, pageable)).thenReturn(customerPage);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        when(customerService.getCustomers()).thenReturn(getCustomerList(5));
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-customers"), businessTestFile);
    }

    @Test
    public void testControllerListCustomers() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Customer> customers = getCustomerList(5);
        Page<Customer> customerPage = new PageImpl<>(customers);
        when(customerService.searchCustomers(null, pageable)).thenReturn(customerPage);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-customers"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/addCustomerForm")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-customer-form"), businessTestFile);
    }

    @Test
    public void testControllerSaveCustomer() throws Exception {
        Customer customer = getCustomer();
        MvcResult result = this.mockMvc.perform(post("/saveCustomer")
                .flashAttr("customer", customer)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/customer/list"), businessTestFile);
    }

    @Test
    public void testControllerSaveCustomerHasError() throws Exception {
        Customer customer = getCustomer();
        customer.setFirstName(null);
        MvcResult result = this.mockMvc.perform(post("/saveCustomer")
                .flashAttr("customer", customer)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-customer-form"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Customer customer = getCustomer();
        when(customerService.getCustomer(customer.getId())).thenReturn(customer);
        MvcResult result = this.mockMvc.perform(get("/updateCustomerForm")
                .param("customerId", customer.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-customer-form"), businessTestFile);
    }

    @Test
    public void testControllerDeleteCustomer() throws Exception {
        Customer customer = getCustomer();
        MvcResult result = this.mockMvc.perform(get("/delete")
                .param("customerId", customer.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/customer/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchCustomersWithSearchKey() throws Exception {
        String key = randomStringWithSize(2);
        Pageable pageable = PageRequest.of(0, 5);
        List<Customer> customers = getCustomerList(5);
        Page<Customer> customerPage = new PageImpl<>(customers);
        when(customerService.searchCustomers(key, pageable)).thenReturn(customerPage);
        MvcResult result = this.mockMvc.perform(get("/search")
                .param("theSearchName", key)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-customers"), businessTestFile);
    }

    @Test
    public void testControllerSearchCustomersWithNullKey() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Customer> customers = getCustomerList(5);
        Page<Customer> customerPage = new PageImpl<>(customers);
        when(customerService.searchCustomers(null, pageable)).thenReturn(customerPage);
        MvcResult result = this.mockMvc.perform(get("/search")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-customers"), businessTestFile);
    }

    @Test
    public void testControllerUpdateVerified() throws Exception {
        Customer customer = getCustomer();
        MvcResult result = this.mockMvc.perform(get("/updateVerified")
                        .param("id", customer.getId().toString()))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/customer/list"), businessTestFile);
    }
}
