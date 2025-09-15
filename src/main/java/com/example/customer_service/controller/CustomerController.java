package com.example.customer_service.controller;


import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping
    public CustomerDto create(@Valid @RequestBody CustomerDto request) {
        return service.createCustomer(request);
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @Valid @RequestBody CustomerDto request) {
        return service.updateCustomer(id, request);
    }
}

