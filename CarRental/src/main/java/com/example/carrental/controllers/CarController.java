package com.example.carrental.controllers;

import com.example.carrental.DTO.CarDto;
import com.example.carrental.models.Car;
import com.example.carrental.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Controller
@AllArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public String getAllCars(Model model){
        model.addAttribute("cars",carService.getAllAvailableCars());
        return "cars";
    }
    @GetMapping("/cars/rented")
    public String getRentedCars(Model model){
        model.addAttribute("cars",carService.getRentedCars());
        return "cars_inrent";
    }

    @GetMapping("/cars/new")
    public String addCarForm(Model model){
        model.addAttribute("car", new CarDto());
        return "car_create";
    }
    @PostMapping("/cars")
    public String addNewCar(@ModelAttribute CarDto car){
        MultipartFile image= car.getImageFile();
        Date createdAt= new Date();
        String storageFileName=createdAt.getTime()+"_"+image.getOriginalFilename();
        try {
            String uploadDir="public/images/";
            Path uploadPath= Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream=image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir+storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (Exception ex){
            System.out.println("Exeption"+ex.getMessage());
        }

        Car car1=new Car();
        car1.setBrand(car.getBrand());
        car1.setModel(car.getModel());
        car1.setYear(car.getYear());
        car1.setRented(false);
        car1.setLicensePlate(car.getLicencePlate());
        car1.setImage(storageFileName);
        carService.saveCar(car1);
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCar(@PathVariable Long id,Model model){
        model.addAttribute("car",carService.getCarById(id));
        return "car_edit";
    }
    @PostMapping("/cars/{id}")
    public String saveCar(@PathVariable Long id, @ModelAttribute Car car){
        carService.updateCar(id,car);
        return "redirect:/cars";
    }
    @GetMapping("cars/delete/{id}")
    public String deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return "redirect:/cars";
    }



}
