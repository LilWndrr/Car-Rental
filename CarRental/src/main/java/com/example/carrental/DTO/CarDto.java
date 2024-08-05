package com.example.carrental.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class CarDto {

    private String brand;
    private String model;
    private int year;
    private String licencePlate;
    private MultipartFile imageFile;



}
