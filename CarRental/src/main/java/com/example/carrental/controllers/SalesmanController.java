package com.example.carrental.controllers;

import com.example.carrental.models.Salesman;
import com.example.carrental.services.SalesmanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SalesmanController {
    private final SalesmanService salesmanService;

    @GetMapping("/salesmen")
    public String getAllSalesmen(Model model){
        model.addAttribute("salesmen",salesmanService.getAll() );
        return "salesmen";
    }

    @GetMapping("/salesmen/new")
    public String getSalesmanForm(Model model){
        Salesman salesman=new Salesman();
        model.addAttribute("salesman",salesman);
        return "salesmen_create";
    }
    @PostMapping("/salesmen")
    public String saveSalesman(@ModelAttribute Salesman salesman){
        salesmanService.save(salesman);
        return "redirect:/salesmen";
    }


    @GetMapping("/salesmen/edit/{id}")
    public String editSalesman(@PathVariable Long id, Model model){
        Salesman salesman=salesmanService.getById(id);
        model.addAttribute("salesman",salesman);
        return "salesman_edit";
    }
    @PostMapping("/salesmen/{id}")
    public String updateSalesman(@PathVariable Long id, @ModelAttribute Salesman salesman){
        salesmanService.updateSalesman(id,salesman);
        return "redirect:/salesmen";
    }
    @GetMapping("/salesmen/delete/{id}")
    public String deleteSalesman(@PathVariable Long id){
        salesmanService.delete(id);
        return "redirect:/salesmen";
    }


}
