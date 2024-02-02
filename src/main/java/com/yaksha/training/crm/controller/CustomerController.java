package com.yaksha.training.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yaksha.training.crm.entity.Customer;
import com.yaksha.training.crm.service.CustomerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = { "/customer", "/" })
public class CustomerController {

	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = { "/list", "/", "/search" })
	public String listCustomers(@RequestParam(value = "theSearchName", required = false) String theSearchName,
			@PageableDefault(size = 5) Pageable pageable, Model model) {
		Page<Customer> customers = customerService.searchCustomers(theSearchName, pageable);
		model.addAttribute("customers", customers.getContent());
		model.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
		model.addAttribute("totalPage", customers.getTotalPages());
		model.addAttribute("page", pageable.getPageNumber());
		model.addAttribute("sortBy",
				pageable.getSort().get().count() != 0 ? pageable.getSort().get().findFirst().get().getProperty() + ","
						+ pageable.getSort().get().findFirst().get().getDirection() : "");
		return "list-customers";
	}

	@GetMapping("/addCustomerForm")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "add-customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-customer-form";
		} else {
			customerService.saveCustomer(customer);
			return "redirect:/customer/list";
		}
	}

	@GetMapping("/updateCustomerForm")
	public String showFormForUpdate(@RequestParam("customerId") Long id, Model model) {
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		return "update-customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") Long id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}

	@GetMapping("/updateVerified")
	public String updateVerified(@RequestParam("id") Long id, Model theModel) {
		customerService.updateIsVerified(id);
		return "redirect:/customer/list";

	}
}
