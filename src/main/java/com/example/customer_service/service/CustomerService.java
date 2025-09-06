package com.example.customer_service.service;


import com.example.customer_service.dto.CustomerRequest;
import com.example.customer_service.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Long id);
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
}
