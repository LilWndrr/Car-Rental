package com.example.carrental.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salesman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesmanId;
    private String name;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "salesman")
    private List<Car> cars;

    @ManyToMany
    @JoinTable(
            name = "Sales",
            joinColumns = @JoinColumn(name = "salesman_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }
    public void deleteCustomer(Customer customer){
        this.customers.remove(customer);
    }
    // Getters and Setters
}
