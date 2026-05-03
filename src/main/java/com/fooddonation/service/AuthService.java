package com.fooddonation.service;

import com.fooddonation.dto.LoginRequest;
import com.fooddonation.entity.NGO;
import com.fooddonation.entity.User;
import com.fooddonation.enums.NGOStatus;
import com.fooddonation.enums.Role;
import com.fooddonation.repository.NGORepository;
import com.fooddonation.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final NGORepository ngoRepository;

    // REGISTER USER
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    // LOGIN
    public User login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // NGO APPROVAL CHECK
        if (user.getRole().name().equals("NGO")) {

            NGO ngo = ngoRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("NGO not found"));

            if (ngo.getStatus() != NGOStatus.APPROVED) {
                throw new RuntimeException("NGO not approved by admin");
            }
        }

        return user;
    }

    // CHANGE PASSWORD
    public String changePassword(String email, String oldPassword, String newPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        return "Password updated successfully";
    }
    // ✅ UPDATE PROFILE
public User updateProfile(Long id, String name, String phone) {

    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    user.setName(name);
    user.setPhone(phone);

    return userRepository.save(user);
}
}