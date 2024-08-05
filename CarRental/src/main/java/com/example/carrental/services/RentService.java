package com.example.carrental.services;

import com.example.carrental.models.Car;
import com.example.carrental.models.Rent;
import com.example.carrental.models.Salesman;
import com.example.carrental.repositories.CarRepo;
import com.example.carrental.repositories.RentRepo;
import com.example.carrental.repositories.SalesmanRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RentService {
    private final CarRepo carRepo;
    private final RentRepo rentRepo;
    private final SalesmanRepo salesmanRepo;

    public Rent getRentById(Long id) {
        return rentRepo.findById(id).orElse(null);
    }

    public Rent saveRent(Rent rent, Long salesmanId) {
        Car car = rent.getCar();
        Salesman salesman = salesmanRepo.findById(salesmanId).orElseThrow(() -> new RuntimeException("Salesman not found"));
        Car existingCar = carRepo.findById(car.getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));

        car.setRented(true);
        existingCar.setRented(car.isRented());
        existingCar.setSalesman(salesman);

        salesman.addCustomer(rent.getCustomer());

        carRepo.save(existingCar);
        return rentRepo.save(rent);
    }

    public void deleteRent(Long id) {
        Rent rent = rentRepo.findByCar_CarId(id);
        if (rent != null) {
            Car car = rent.getCar();
            Car existingCar = carRepo.findById(car.getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));

            car.setRented(false);
            car.setSalesman(null);
            existingCar.setRented(car.isRented());
            existingCar.setSalesman(car.getSalesman());

            carRepo.save(existingCar);
            rentRepo.deleteById(id);
        } else {
            throw new RuntimeException("Rent not found");
        }
    }

    public void updateRent(Long id, Rent rent) {
        Rent existingRent = rentRepo.findById(id).orElseThrow(() -> new RuntimeException("Rent not found"));
        Salesman salesman = rent.getCar().getSalesman();
        Car newRentedCar = carRepo.findById(rent.getCar().getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));

        newRentedCar.setRented(true);
        newRentedCar.setSalesman(salesman);
        carRepo.save(newRentedCar);

        Car unrentedCar = carRepo.findById(existingRent.getCar().getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));
        unrentedCar.setSalesman(null);
        unrentedCar.setRented(false);
        carRepo.save(unrentedCar);

        existingRent.setCar(rent.getCar());
        existingRent.setRentDate(rent.getRentDate());
        existingRent.setReturnDate(rent.getReturnDate());
        existingRent.setCustomer(rent.getCustomer());
        existingRent.setCost(rent.getCost());

        rentRepo.save(existingRent);
    }

    public List<Rent> getAllRents() {
        return rentRepo.findAll();
    }
}
