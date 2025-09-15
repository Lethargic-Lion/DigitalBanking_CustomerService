package com.example.customer_service;

import com.example.customer_service.controller.CustomerController;
import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.enums.KycStatus;
import com.example.customer_service.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@Import(CustomerControllerTest.MockConfig.class) // ✅ Import mock configuration
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerService customerService; // ✅ injected from MockConfig

    @Autowired
    ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        CustomerService customerService() {
            return Mockito.mock(CustomerService.class); // ✅ manually create Mockito mock
        }
    }

    @Test
    void testGetAllCustomers() throws Exception {
        List<CustomerDto> responses = Arrays.asList(
                new CustomerDto(1L, "John Doe", "john.doe@example.com", "7894561230", "Bengaluru", KycStatus.PENDING),
                new CustomerDto(2L, "JaneSmith", "jane.smith@example.com" , "1234567890", "New York", KycStatus.REJECTED)
        );

        Mockito.when(customerService.getAllCustomers()).thenReturn(responses);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }
}
