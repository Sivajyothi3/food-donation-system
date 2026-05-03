package com.fooddonation.controller;

import com.fooddonation.entity.Donation;
import com.fooddonation.enums.NGOStatus;
import com.fooddonation.service.DonationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
@CrossOrigin
public class DonationController {

    private final DonationService donationService;

    // ✅ CREATE
    @PostMapping
    public Donation create(
            @RequestParam Long userId,
            @RequestParam Long ngoId,
            @RequestBody Donation donation
    ) {
        return donationService.createDonation(userId, ngoId, donation);
    }

    // ✅ USER HISTORY
    @GetMapping("/user/{userId}")
    public List<Donation> getUser(@PathVariable Long userId) {
        return donationService.getUserDonations(userId);
    }

    // ✅ NGO PENDING ONLY
    @GetMapping("/ngo/pending/{ngoId}")
    public List<Donation> getPending(@PathVariable Long ngoId) {
        return donationService.getPendingDonations(ngoId);
    }

    // ✅ NGO HISTORY
    @GetMapping("/ngo/history/{ngoId}")
    public List<Donation> getHistory(@PathVariable Long ngoId) {
        return donationService.getHistoryDonations(ngoId);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Donation update(
            @PathVariable Long id,
            @RequestParam NGOStatus status,
            @RequestParam String message
    ) {
        return donationService.updateDonation(id, status, message);
    }
}