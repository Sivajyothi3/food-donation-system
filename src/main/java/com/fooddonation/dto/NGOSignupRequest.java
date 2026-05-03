package com.fooddonation.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NGOSignupRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phone;

    @NotBlank
    private String address;

    @NotBlank
    private String state; // IMPORTANT

    @NotBlank
    private String registrationNumber;

    private String description;

    @NotBlank
    private String contactPersonName;

    // REAL FILE UPLOAD (IMPORTANT)
    private MultipartFile govtCertificate;
    private MultipartFile panCard;
}