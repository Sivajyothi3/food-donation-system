package com.fooddonation.service;

import com.fooddonation.entity.NGO;
import com.fooddonation.entity.User;
import com.fooddonation.enums.NGOStatus;
import com.fooddonation.repository.NGORepository;
import com.fooddonation.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private NGORepository ngoRepository;

    @Autowired
    private UserRepository userRepository;

    // Pending
    public List<NGO> getPendingNGOs() {
        return ngoRepository.findByStatus(NGOStatus.PENDING);
    }

    // Approved
    public List<NGO> getApprovedNGOs() {
        return ngoRepository.findByStatus(NGOStatus.APPROVED);
    }

    // Rejected
    public List<NGO> getRejectedNGOs() {
        return ngoRepository.findByStatus(NGOStatus.REJECTED);
    }

    // Approve
    public NGO approveNGO(Long id) {
        NGO ngo = ngoRepository.findById(id).orElseThrow();
        ngo.setStatus(NGOStatus.APPROVED);
        ngo.setVerified(true);
        return ngoRepository.save(ngo);
    }

    // Reject
    public NGO rejectNGO(Long id) {
        NGO ngo = ngoRepository.findById(id).orElseThrow();
        ngo.setStatus(NGOStatus.REJECTED);
        ngo.setVerified(false);
        return ngoRepository.save(ngo);
    }

    // DELETE NGO COMPLETELY
    public void deleteNGO(Long id) {

    NGO ngo = ngoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NGO not found"));

    // 🔥 DELETE RELATED DONATIONS FIRST (IMPORTANT)
    if (ngo.getDonations() != null) {
        ngo.getDonations().clear();
    }

    // 🔥 DELETE USER LOGIN
    userRepository.findByEmail(ngo.getEmail())
            .ifPresent(userRepository::delete);

    // 🔥 DELETE NGO
    ngoRepository.deleteById(id);
}
}