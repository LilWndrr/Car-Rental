package com.example.carrental.services;

import com.example.carrental.models.Customer;
import com.example.carrental.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;


    public List<Customer> getAll(){
        return customerRepo.findAll();
    }

    public void save(Customer customer) {
     customerRepo.save(customer);
    }

    public Customer getById(Long id) {
        return customerRepo.findById(id).get();
    }

    public void updateCustomer(Long id, Customer customer) {
        Customer existingCustomer= customerRepo.findById(id).get();
        existingCustomer.setName(customer.getName());
        existingCustomer.setAge(customer.getAge());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        customerRepo.save(existingCustomer);
    }


}
