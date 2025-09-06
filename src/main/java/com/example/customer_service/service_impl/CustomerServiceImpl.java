package com.example.customer_service.service_impl;

import com.example.customer_service.dto.CustomerRequest;
import com.example.customer_service.dto.CustomerResponse;
import com.example.customer_service.exception.ResourceNotFoundException;
import com.example.customer_service.model.Customer;
import com.example.customer_service.repository.CustomerRepository;
import com.example.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
        Customer saved = repository.save(customer);
        return mapToResponse(saved);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        Customer updated = repository.save(customer);
        return mapToResponse(updated);
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .kycStatus(customer.getKycStatus())
                .build();
    }
}
