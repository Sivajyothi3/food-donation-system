package com.fooddonation.service;

import com.fooddonation.entity.NGO;
import com.fooddonation.entity.User;
import com.fooddonation.enums.Role;
import com.fooddonation.enums.NGOStatus;
import com.fooddonation.repository.NGORepository;
import com.fooddonation.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NGOService {

    private final NGORepository ngoRepository;
    private final UserRepository userRepository;

    // ✅ GET APPROVED NGOs
    public List<NGO> getApprovedNGOs() {
        return ngoRepository.findByStatus(NGOStatus.APPROVED);
    }

    // ✅ REGISTER NGO WITH REAL FILE STORAGE
    public void registerNGO(String name, String email, String password,
                       String phone, String address, String state,
                       String regNo, String desc, String contact,
                       MultipartFile govtFile, MultipartFile panFile) {

    try {
        String uploadDir = "uploads/";

        // ✅ CREATE FOLDER IF NOT EXISTS
        java.io.File dir = new java.io.File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // ✅ UNIQUE FILE NAMES
        String govtFileName = System.currentTimeMillis() + "_" + govtFile.getOriginalFilename();
        String panFileName = System.currentTimeMillis() + "_" + panFile.getOriginalFilename();

        // ✅ SAVE FILES
        java.nio.file.Files.copy(
                govtFile.getInputStream(),
                java.nio.file.Paths.get(uploadDir + govtFileName),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
        );

        java.nio.file.Files.copy(
                panFile.getInputStream(),
                java.nio.file.Paths.get(uploadDir + panFileName),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
        );

        // ✅ SAVE NGO
        NGO ngo = new NGO();
        ngo.setName(name);
        ngo.setEmail(email);
        ngo.setPhone(phone);
        ngo.setAddress(address);
        ngo.setState(state);
        ngo.setRegistrationNumber(regNo);
        ngo.setDescription(desc);
        ngo.setContactPersonName(contact);

        ngo.setVerified(false);
        ngo.setStatus(NGOStatus.PENDING);

        ngo.setGovtCertificatePath(govtFileName);
        ngo.setPanCardPath(panFileName);

        ngoRepository.save(ngo);

        // ✅ CREATE LOGIN USER
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.NGO);

        userRepository.save(user);

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("File upload failed");
    }
}

    // ✅ GET NGO BY EMAIL
    public NGO getByEmail(String email) {
        return ngoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("NGO not found"));
    }

    // ✅ UPDATE NGO
    public NGO updateNGO(Long id, NGO updatedNGO) {

        NGO ngo = ngoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NGO not found"));

        ngo.setName(updatedNGO.getName());
        ngo.setPhone(updatedNGO.getPhone());
        ngo.setAddress(updatedNGO.getAddress());
        ngo.setState(updatedNGO.getState());
        ngo.setDescription(updatedNGO.getDescription());

        return ngoRepository.save(ngo);
    }
}