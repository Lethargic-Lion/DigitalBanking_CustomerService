package com.example.customer_service.controller;


import com.example.customer_service.dto.CustomerRequest;
import com.example.customer_service.dto.CustomerResponse;
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
    public CustomerResponse create(@Valid @RequestBody CustomerRequest request) {
        return service.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        return service.updateCustomer(id, request);
    }
}

