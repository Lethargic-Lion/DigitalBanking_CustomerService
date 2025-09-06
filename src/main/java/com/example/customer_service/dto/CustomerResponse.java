package com.example.customer_service.dto;

import com.example.customer_service.enums.KycStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private KycStatus kycStatus;
}

