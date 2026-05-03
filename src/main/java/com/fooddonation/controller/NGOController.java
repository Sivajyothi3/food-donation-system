package com.fooddonation.controller;

import com.fooddonation.entity.NGO;
import com.fooddonation.service.NGOService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/ngos")
@RequiredArgsConstructor
@CrossOrigin
public class NGOController {

    private final NGOService ngoService;

    // ✅ GET ALL NGOs
    @GetMapping("/approved")
public List<NGO> getApprovedNGOs() {
    return ngoService.getApprovedNGOs();
}
@GetMapping("/email")
public NGO getByEmail(@RequestParam String email) {
    return ngoService.getByEmail(email);
}
// ✅ UPDATE NGO PROFILE
@PutMapping("/{id}")
public NGO updateNGO(@PathVariable Long id, @RequestBody NGO ngo) {
    return ngoService.updateNGO(id, ngo);
}
    // ✅ REGISTER NGO
    @PostMapping("/register")
    public ResponseEntity<?> registerNGO(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,   // ✅ FIXED
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String state,
            @RequestParam String registrationNumber,
            @RequestParam String description,
            @RequestParam String contactPersonName,
            @RequestParam MultipartFile govtCertificate,
            @RequestParam MultipartFile panCard
    ) {

        ngoService.registerNGO(
                name, email, password, phone, address, state,
                registrationNumber, description, contactPersonName,
                govtCertificate, panCard
        );

        return ResponseEntity.ok("NGO Registered Successfully");
    }
}