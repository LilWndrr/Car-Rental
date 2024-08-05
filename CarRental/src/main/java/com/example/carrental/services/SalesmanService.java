package com.example.carrental.services;

import com.example.carrental.models.Car;
import com.example.carrental.models.Salesman;
import com.example.carrental.repositories.CarRepo;
import com.example.carrental.repositories.SalesmanRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SalesmanService {
    private final SalesmanRepo salesmanRepo;
    private final CarRepo carRepo;
    public List<Salesman> getAll() {
        return salesmanRepo.findAll();
    }

    public Salesman getById(Long id) {
        return salesmanRepo.findById(id).get();
    }

    public void save(Salesman salesman) {
        salesmanRepo.save(salesman);
    }

    public void updateSalesman(Long id, Salesman salesman) {
       Salesman existingSalesman = salesmanRepo.findById(id).get();
       existingSalesman.setName(salesman.getName());
       existingSalesman.setEmail(salesman.getEmail());
        existingSalesman.setPhoneNumber(salesman.getPhoneNumber());
        salesmanRepo.save(existingSalesman);
    }

    public void delete(Long id) {
        Car car= carRepo.findCarBySalesmanSalesmanId(id);
        if(car!=null){
        car.setSalesman(null);
        carRepo.save(car);
        }
        salesmanRepo.deleteById(id);
    }
}
