package com.fooddonation.dto;
import com.fooddonation.enums.*;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonationRequest {

    @NotNull
    private Long ngoId;

    @NotNull
    private DonationType donationType;

    @NotNull
    private DeliveryType deliveryType;

    private String pickupAddress;

    private LocalDateTime pickupDateTime;

    @NotBlank
    private String contactName;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String contactPhone;
}