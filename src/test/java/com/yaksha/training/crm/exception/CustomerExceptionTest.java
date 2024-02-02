package com.yaksha.training.crm.exception;

import com.yaksha.training.crm.controller.CustomerController;
import com.yaksha.training.crm.entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.crm.utils.MasterData.getCustomer;
import static com.yaksha.training.crm.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.crm.utils.TestUtils.currentTest;
import static com.yaksha.training.crm.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.crm.utils.TestUtils.testReport;
import static com.yaksha.training.crm.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CustomerExceptionTest {

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSaveCustomerFirstNameAsNull() throws Exception {
        Customer customer = getCustomer();
        customer.setFirstName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveCustomer")
                .flashAttr("customer", customer)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-customer-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveCustomerLastNameAsNull() throws Exception {
        Customer customer = getCustomer();
        customer.setLastName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveCustomer")
                .flashAttr("customer", customer)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-customer-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveCustomerEmailAsNull() throws Exception {
        Customer customer = getCustomer();
        customer.setEmail(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveCustomer")
                .flashAttr("customer", customer)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-customer-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveCustomerEmailInvalidFormat() throws Exception {
        Customer customer = getCustomer();
        customer.setEmail(randomStringWithSize(10));
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveCustomer")
                .flashAttr("customer", customer)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-customer-form")), exceptionTestFile);
    }


}
