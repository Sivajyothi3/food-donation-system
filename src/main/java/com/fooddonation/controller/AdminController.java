package com.fooddonation.controller;

import com.fooddonation.entity.NGO;
import com.fooddonation.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ✅ PENDING
    @GetMapping("/ngos/pending")
    public List<NGO> getPendingNGOs() {
        return adminService.getPendingNGOs();
    }

    // ✅ APPROVED
    @GetMapping("/ngos/approved")
    public List<NGO> getApprovedNGOs() {
        return adminService.getApprovedNGOs();
    }

    // ✅ REJECTED
    @GetMapping("/ngos/rejected")
    public List<NGO> getRejectedNGOs() {
        return adminService.getRejectedNGOs();
    }

    // ✅ APPROVE NGO (🔥 MISSING BEFORE)
    @PutMapping("/ngos/{id}/approve")
    public NGO approveNGO(@PathVariable Long id) {
        return adminService.approveNGO(id);
    }

    // ✅ REJECT NGO (🔥 MISSING BEFORE)
    @PutMapping("/ngos/{id}/reject")
    public NGO rejectNGO(@PathVariable Long id) {
        return adminService.rejectNGO(id);
    }

    // ✅ DELETE NGO
    @DeleteMapping("/ngos/{id}")
    public String deleteNGO(@PathVariable Long id) {
        adminService.deleteNGO(id);
        return "NGO Deleted Successfully";
    }
}