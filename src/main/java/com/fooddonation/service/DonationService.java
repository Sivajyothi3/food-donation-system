package com.fooddonation.service;

import com.fooddonation.entity.Donation;
import com.fooddonation.entity.NGO;
import com.fooddonation.entity.User;
import com.fooddonation.enums.NGOStatus;
import com.fooddonation.repository.DonationRepository;
import com.fooddonation.repository.NGORepository;
import com.fooddonation.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NGORepository ngoRepository;

    // ✅ CREATE
    public Donation createDonation(Long userId, Long ngoId, Donation donation) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        NGO ngo = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));

        donation.setUser(user);
        donation.setNgo(ngo);
        donation.setStatus(NGOStatus.PENDING);
        donation.setCreatedAt(LocalDateTime.now());

        return donationRepository.save(donation);
    }

    // ✅ USER HISTORY
    public List<Donation> getUserDonations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return donationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    // ✅ NGO PENDING ONLY
    public List<Donation> getPendingDonations(Long ngoId) {
        NGO ngo = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));

        return donationRepository.findByNgoAndStatus(ngo, NGOStatus.PENDING);
    }

    // ✅ NGO HISTORY
    public List<Donation> getHistoryDonations(Long ngoId) {
        NGO ngo = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));

        return donationRepository.findByNgoAndStatusNot(ngo, NGOStatus.PENDING);
    }

    // ✅ UPDATE STATUS + MESSAGE
    public Donation updateDonation(Long id, NGOStatus status, String message) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        donation.setStatus(status);
        donation.setResponseMessage(message);

        return donationRepository.save(donation);
    }
}