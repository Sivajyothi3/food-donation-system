package com.fooddonation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NGOResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String state;
    private String description;
    private String contactPersonName;

    private boolean verified;
}