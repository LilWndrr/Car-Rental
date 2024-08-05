package com.example.carrental.controllers;

import com.example.carrental.models.Customer;
import com.example.carrental.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public String getAllCustomers(Model model ){
        model.addAttribute("customers",customerService.getAll());
        return "customers";
    }
    @GetMapping("/customers/new")
    public String addCustomerForm(Model model){
        model.addAttribute("customer",new Customer());
        return "customer_create";
    }
    @PostMapping("/customers")
    public String addCustomer(@ModelAttribute Customer customer){
        customerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model){
        Customer existingCustomer= customerService.getById(id);
        model.addAttribute("customer", existingCustomer);
        return "customer_edit";
    }
    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id,@ModelAttribute Customer customer){
        customerService.updateCustomer(id,customer);
        return "redirect:/customers";
    }
   /* @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){
        customerService.delete(id);
        return "redirect:/customers";
    }*/

}
