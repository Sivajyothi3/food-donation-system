package com.fooddonation.repository;

import com.fooddonation.entity.NGO;
import com.fooddonation.enums.NGOStatus;   // ✅ FIXED
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NGORepository extends JpaRepository<NGO, Long> {

    Optional<NGO> findByEmail(String email);

    List<NGO> findByVerifiedTrue();

    List<NGO> findByStatus(NGOStatus status);

    List<NGO> findByStateAndVerifiedTrue(String state);

    List<NGO> findByNameContainingIgnoreCaseAndVerifiedTrue(String name);

    List<NGO> findByStateAndNameContainingIgnoreCaseAndVerifiedTrue(String state, String name);
}