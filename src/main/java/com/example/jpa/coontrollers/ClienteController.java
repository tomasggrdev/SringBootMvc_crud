package com.example.jpa.coontrollers;

import com.example.jpa.modles.dao.CustomerRepository;
import com.example.jpa.modles.entity.Customer;
import com.example.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;


@Controller
@RequestMapping("/api/v1")
@SessionAttributes("customer")
public class ClienteController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("title","Customer list");

        model.addAttribute("customers",customerService.findAll());
        return "list";
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("title","Agregar cliente");
        model.addAttribute("customer", new Customer());
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid Customer customer,BindingResult result){
        if(result.hasErrors()){
            return "form";
        }
        customerService.save(customer);

        return  "redirect:/api/v1/list";
    }

    @GetMapping("/save/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("title","Editar cliente");
        if(id > 0) {
            Optional<Customer> customer = customerService.findById(id);
            model.addAttribute("customer", customer);
        } else {
            return "redirect:/api/v1/list";
        }
        return "form";
    }

    @PostMapping("/save/{id}")
    public String save(Customer customer, SessionStatus status) {
        if(customer.getId() != null && customer.getId() > 0){
            customerService.save(customer);
        }
        status.setComplete();
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if(id != null && id > 0){
            customerService.deleteById(id);
        }

        return "redirect:/api/v1/list";
    }



}
