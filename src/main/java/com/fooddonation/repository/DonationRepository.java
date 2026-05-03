package com.fooddonation.repository;

import com.fooddonation.entity.*;
import com.fooddonation.enums.NGOStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByUser(User user);

    List<Donation> findByUserOrderByCreatedAtDesc(User user);

    // ✅ PENDING ONLY
    List<Donation> findByNgoAndStatus(NGO ngo, NGOStatus status);

    // ✅ HISTORY (NOT PENDING)
    List<Donation> findByNgoAndStatusNot(NGO ngo, NGOStatus status);

}