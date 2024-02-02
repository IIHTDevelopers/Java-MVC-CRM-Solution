package com.yaksha.training.crm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.training.crm.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    public static Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(randomStringWithSize(10));
        customer.setLastName(randomStringWithSize(10));
        customer.setEmail(randomStringWithSize(20) + "@gmail.com");
        return customer;
    }

    public static List<Customer> getCustomerList(int size) {
        Long id = 0L;
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Customer customer = new Customer();
            customer.setId(1L);
            customer.setFirstName(randomStringWithSize(10));
            customer.setLastName(randomStringWithSize(10));
            customer.setEmail(randomStringWithSize(20) + "@gmail.com");
            customers.add(customer);
        }
        return customers;
    }

    private static Random rnd = new Random();

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
