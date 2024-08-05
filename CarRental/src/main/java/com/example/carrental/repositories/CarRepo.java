package com.example.carrental.repositories;

import com.example.carrental.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,Long> {
        public List<Car> findAllByRentedTrue();
        public List<Car> findAllByRentedFalse();
        public Car getCarBySalesmanSalesmanId(Long salesman_salesmanId);
        public Car findCarBySalesmanSalesmanId(Long salesman_salesmanId);
}
