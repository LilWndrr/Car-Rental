package com.example.carrental.controllers;

import com.example.carrental.models.Car;
import com.example.carrental.models.Customer;
import com.example.carrental.models.Rent;
import com.example.carrental.models.Salesman;
import com.example.carrental.services.CarService;
import com.example.carrental.services.CustomerService;
import com.example.carrental.services.RentService;
import com.example.carrental.services.SalesmanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RentController {
    private final CarService carService;
    private final CustomerService customerService;
    private final SalesmanService salesmanService;
    private final RentService rentService;

    @GetMapping("/cars/lease/{id}")
    public String createRentForm(@PathVariable Long id, Model model) {
        Rent rent = new Rent();
        Car car = carService.getCarById(id);
        rent.setCar(car);
        model.addAttribute("rent", rent);
        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("salesmen", salesmanService.getAll());
        model.addAttribute("car",car);
        return "rent_create";
    }
    @GetMapping("/rents")
    public String getAllRents(Model model){
        model.addAttribute("rents",rentService.getAllRents());
        return "rents";
    }

    @PostMapping("/rents")
    public String saveRent(@ModelAttribute("rent") Rent rent,@RequestParam("salesmanId") Long salesmanId) {

            rentService.saveRent(rent,salesmanId);
            return "redirect:/cars";

    }
    @GetMapping("/rent/delete/{id}")
    public String endRent(@PathVariable Long id){
        rentService.deleteRent(id);
        return "redirect:/cars/rented";
    }
    @GetMapping("/rent/{id}")
    public String getRentDetails(@PathVariable Long id ,Model model){
            Rent rent=rentService.getRentById(id);
            model.addAttribute("rent",rent);
            return "rent_details";
    }
    @GetMapping("/rents/edit/{id}")
    public String editRentForm(@PathVariable Long id,Model model){
        Rent rent= rentService.getRentById(id);
        Customer customer=rent.getCustomer();
        Salesman salesman= (rent.getCar().getSalesman());
        model.addAttribute("rent",rent);
        model.addAttribute("cars",carService.getAllAvailableCars());
        model.addAttribute("customer",customer);
        model.addAttribute("salesman",salesman);
        return "rent_edit";
    }

    @PostMapping("/rents/{id}")
    public String updateRent(@PathVariable Long id,@ModelAttribute Rent rent){
        rentService.updateRent(id,rent);
        return "redirect:/cars/rented";
    }
    @GetMapping("/rents/delete/{id}")
    public String deleteRent(@PathVariable Long id){
        rentService.deleteRent(id);
        return "redirect:/rents";
    }
}
