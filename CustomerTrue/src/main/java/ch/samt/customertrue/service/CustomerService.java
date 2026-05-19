package ch.samt.customertrue.service;

import ch.samt.customertrue.data.CustomerRepository;
import ch.samt.customertrue.domain.Customer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    @CacheEvict(value = "customerBySurname", key = "#customer.surname.toLowerCase()")
    public void save(@Valid Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (RuntimeException ex) {
            log.error("Error saving customer" + customer.getId(), ex);
            throw new RuntimeException("Errore durante il save del customer in DB: " + customer, ex);
        }
        log.info("Customer " + customer.getName() + " " + customer.getSurname() + " saved with ID: " + customer.getId());
    }

    @Cacheable(value = "customerBySurname", key = "#surnameToFilter.toLowerCase()")
    public List<Customer> findBySurnameIgnoreCase(String surnameToFilter) throws InterruptedException {
        Thread.sleep(5000);
        return customerRepository.findBySurnameIgnoreCase(surnameToFilter);
    }

    public List<Customer> findByAgeLessThan(Integer integer) {
        return customerRepository.findByAgeLessThan(integer);
    }

    public Customer findById(long customerId) {
        return customerRepository.findById(customerId);
    }
}