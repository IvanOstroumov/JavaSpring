package ch.samt.customers.controller;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/customers")
@Controller
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping
    public String loadCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customerList";
    }

    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Customer customer) {
        return "insertCustomer";
    }

    @PostMapping("/insert")
    public String saveCustomers(@Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "insertCustomer";
        }
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{surnameToFilter}")
    public String loadInsertPage(Model model, @PathVariable String surnameToFilter) {
        List<Customer> filteredCustomers = customerRepository.findBySurnameIgnoreCase(surnameToFilter);
        model.addAttribute("customers", filteredCustomers);
        return "customerList";
    }

    @GetMapping("/customersbycity")
    public String loadCustomersByCity(Model model, @RequestParam(value = "city", required = true) String city) {
        List<Customer> filteredCustomers = customerRepository.findByCityIgnoreCase(city);
        model.addAttribute("customers", filteredCustomers);
        return "customerList";
    }

    @GetMapping("/customersbyage")
    public String loadCustomersByAge(Model model, @RequestParam(value = "maxage", required = true) Integer age) {
        List<Customer> filteredCustomers = customerRepository.findByAgeLessThan(age);
        model.addAttribute("customers", filteredCustomers);
        return "customerList";
    }
}