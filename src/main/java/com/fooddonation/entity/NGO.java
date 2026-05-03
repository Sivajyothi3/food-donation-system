package com.fooddonation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.fooddonation.enums.NGOStatus;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ngos")
public class NGO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String state;

    private String registrationNumber;

    @Column(length = 1000)
    private String description;

    private String contactPersonName;

    private boolean verified;

    @Enumerated(EnumType.STRING)
    private NGOStatus status;

    private String govtCertificatePath;

    private String panCardPath;

    // 🔴 Fix infinite loop
    @JsonIgnore 
    @OneToMany(mappedBy = "ngo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donations;
}