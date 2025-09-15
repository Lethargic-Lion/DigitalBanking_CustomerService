package com.example.customer_service.dto;

import com.example.customer_service.enums.KycStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;

    @Size(min = 3, max = 100, message = "Full name must have at least 3 characters and must not exceed 100 characters!")
    @NotBlank(message = "Full name is mandatory!")
    private String fullName;

    @Email(message = "Email should be valid!")
    @NotBlank(message = "Email is mandatory!")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone Number must contain 10 digits!")
    @NotBlank(message = "Phone number is mandatory!")
    private String phone;

    @Size(max = 255, message = "Address must not exceed 255 characters!")
    @NotBlank(message = "Address is mandatory!")
    private String address;

    private KycStatus kycStatus;
}

