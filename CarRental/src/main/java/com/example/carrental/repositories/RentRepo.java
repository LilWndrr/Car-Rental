package com.example.carrental.repositories;

import com.example.carrental.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepo extends JpaRepository<Rent,Long> {
    public Rent findByCar_CarId(Long id);
    public Rent findByCustomerCustomerId(Long customer_customerId);
}
