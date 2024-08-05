package com.example.carrental.services;

import com.example.carrental.models.Car;
import com.example.carrental.repositories.CarRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepo carRepo;

    public List<Car> getAllCars(){
        return carRepo.findAll();
    }

    public Car saveCar(Car car){
        return carRepo.save(car);
    }

    public Car getCarById(Long id) {
        return carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public void updateCar(Long id, Car car) {
        Car existingCar = carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        existingCar.setLicensePlate(car.getLicensePlate());
        carRepo.save(existingCar); // Save the updated car
    }

    public void deleteCar(Long id) {
        carRepo.deleteById(id);
    }

    public List<Car> getAllAvailableCars() {
        return carRepo.findAllByRentedFalse();
    }

    public List<Car> getRentedCars() {
        return carRepo.findAllByRentedTrue();
    }
}
