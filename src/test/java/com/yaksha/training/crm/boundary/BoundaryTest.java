package com.yaksha.training.crm.boundary;


import com.yaksha.training.crm.entity.Customer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.crm.utils.MasterData.getCustomer;
import static com.yaksha.training.crm.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.crm.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.crm.utils.TestUtils.currentTest;
import static com.yaksha.training.crm.utils.TestUtils.testReport;
import static com.yaksha.training.crm.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testFirstNameNotBlank() throws Exception {
        Customer customer = getCustomer();
        customer.setFirstName("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testFirstNameNotNull() throws Exception {
        Customer customer = getCustomer();
        customer.setFirstName(null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testFirstNameMinTwo() throws Exception {
        Customer customer = getCustomer();
        customer.setFirstName(randomStringWithSize(1));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testFirstNameMaxFourty() throws Exception {
        Customer customer = getCustomer();
        customer.setFirstName(randomStringWithSize(41));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameNotBlank() throws Exception {
        Customer customer = getCustomer();
        customer.setLastName("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameNotNull() throws Exception {
        Customer customer = getCustomer();
        customer.setLastName(null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameMinTwo() throws Exception {
        Customer customer = getCustomer();
        customer.setLastName(randomStringWithSize(1));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameMaxFourty() throws Exception {
        Customer customer = getCustomer();
        customer.setLastName(randomStringWithSize(41));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEmailNotBlank() throws Exception {
        Customer customer = getCustomer();
        customer.setEmail("");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEmailNotNull() throws Exception {
        Customer customer = getCustomer();
        customer.setEmail(null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEmailInvalidFormat() throws Exception {
        Customer customer = getCustomer();
        customer.setEmail(randomStringWithSize(10));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
