package com.example.carrental.config;

import com.example.carrental.models.Car;
import com.example.carrental.models.Customer;
import com.example.carrental.models.Salesman;
import com.example.carrental.repositories.CarRepo;
import com.example.carrental.repositories.CustomerRepo;
import com.example.carrental.repositories.SalesmanRepo;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SampleDataConfig {
    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    private final SalesmanRepo salesmanRepo;



    @Bean
    public CommandLineRunner populateSampleData() {
        return args -> {
            // Create sample authors using builder (if builder is added)
            Car car1 = Car.builder()
                    .model("Civic")
                    .brand("Honda")
                    .year(2022)
                    .licensePlate("AK2023PH")
                    .rented(false)
                    .image("honda_civic.jpg")
                    .build();
            carRepo.save(car1);

            Car car2 = Car.builder()
                    .model("Camry")
                    .brand("Toyota")
                    .year(2021)
                    .licensePlate("BK2022TX")
                    .rented(false)
                    .image("601909f6dfa015049615db25.jpg")
                    .build();
            carRepo.save(car2);

            Car car3 = Car.builder()
                    .model("Model 3")
                    .brand("Tesla")
                    .year(2023)
                    .licensePlate("CK2024CA")
                    .rented(false)
                    .image("TopGear - Tesla Model 3 - Facelift -1.jpg")
                    .build();
            carRepo.save(car3);

            Car car4 = Car.builder()
                    .model("Accord")
                    .brand("Honda")
                    .year(2020)
                    .licensePlate("DK2021FL")
                    .rented(false)
                    .image("promo.webp")
                    .build();
            carRepo.save(car4);

            Car car5 = Car.builder()
                    .model("Mustang")
                    .brand("Ford")
                    .year(2022)
                    .licensePlate("EK2023NY")
                    .rented(false)
                    .image("2022-ford-mustang-stealth-edition-02-1633475393.jpg")
                    .build();
            carRepo.save(car5);

            Car car6 = Car.builder()
                    .model("Impreza")
                    .brand("Subaru")
                    .year(2019)
                    .licensePlate("FK2020OR")
                    .rented(false)
                    .image("subaru_impreza_965641.jpg")
                    .build();
            carRepo.save(car6);

            Car car7 = Car.builder()
                    .model("Corolla")
                    .brand("Toyota")
                    .year(2022)
                    .licensePlate("GK2023WA")
                    .image("0c846d7641fbfd5c95980fde254b88655aca7820.jpeg")
                    .rented(false)
                    .build();
            carRepo.save(car7);

            Car car8 = Car.builder()
                    .model("A4")
                    .brand("Audi")
                    .year(2021)
                    .licensePlate("HK2022NJ")
                    .rented(false)
                    .image("2021-audi-a4-45-tfsi-quattro-106-1607927016.jpg")
                    .build();
            carRepo.save(car8);

            Car car9 = Car.builder()
                    .model("G63")
                    .brand("Mercedes")
                    .year(2023)
                    .licensePlate("IK2024CO")
                    .image("mercedes.jpg")
                    .rented(false)
                    .build();
            carRepo.save(car9);

            Car car10 = Car.builder()
                    .model("CX-5")
                    .brand("Mazda")
                    .year(2020)
                    .image("2020_Mazda_CX-5_Test_Drive_Review_summaryImage.jpeg")
                    .licensePlate("JK2021NV")
                    .rented(false)
                    .build();
            carRepo.save(car10);
            for(int i=0;i<10;i++){
                Faker faker=new Faker();
                Customer customer= Customer.builder()
                        .name(faker.name().fullName())
                        .age(faker.number().numberBetween(18,60))
                        .email(faker.internet().emailAddress())
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .build();
                customerRepo.save(customer);
            }
            for(int i=0;i<10;i++){
                Faker faker=new Faker();
                Salesman salesman=Salesman.builder()
                        .name(faker.name().fullName())
                        .email(faker.internet().emailAddress())
                        .phoneNumber(faker.phoneNumber().phoneNumber())
                        .build();
                salesmanRepo.save(salesman);
            }

            System.out.println("Sample Authors added successfully!");
        };
    }
}