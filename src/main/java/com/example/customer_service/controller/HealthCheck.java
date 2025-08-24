package com.example.customer_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
public class HealthCheck {

    @GetMapping("/health")
    public String health() {
        LinkedList<String> healthCheckList = new LinkedList<>();
        return "OK";
    }
}
