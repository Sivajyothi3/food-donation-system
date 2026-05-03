package com.fooddonation.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @Size(min = 6, max = 20, message = "Password must be at least 6 characters")
    private String password;
}