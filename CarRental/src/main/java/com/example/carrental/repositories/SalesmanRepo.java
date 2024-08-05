package com.example.carrental.repositories;

import com.example.carrental.models.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesmanRepo extends JpaRepository<Salesman,Long> {
}
