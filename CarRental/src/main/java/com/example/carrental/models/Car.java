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
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brand;
    private String model;
    private int year;
    private String licensePlate;
    private boolean rented;
    @OneToMany(mappedBy = "car")
    private List<Rent> rents;
    @ManyToOne
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;
    private String image;

    public Car (boolean rented){
        this.rented=rented;
    }

    // Getters and Setters
}
