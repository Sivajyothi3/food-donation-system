package com.fooddonation.entity;

import com.fooddonation.enums.DeliveryType;
import com.fooddonation.enums.DonationType;
import com.fooddonation.enums.NGOStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private NGO ngo;

    @Enumerated(EnumType.STRING)
    private DonationType donationType;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    private String pickupAddress;

    private LocalDateTime pickupDateTime;

    private String contactName;

    private String contactPhone;

    // ✅ MONEY
    private Double amount;

    // ✅ NEW FOOD FIELDS
    private String foodType;   // COOKED / RAW
    private Integer quantity; // number of persons

    @Enumerated(EnumType.STRING)
    private NGOStatus status;

    private String responseMessage;

    private LocalDateTime createdAt;
}