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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    private Integer age;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "customer")
    private List<Rent> rents;
    @ManyToMany(mappedBy = "customers")
    private List<Salesman> salesmen;


    // Getters and Setters
}
