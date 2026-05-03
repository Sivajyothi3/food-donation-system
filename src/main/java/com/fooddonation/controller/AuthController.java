package com.fooddonation.controller;

import com.fooddonation.dto.LoginRequest;
import com.fooddonation.dto.SignupRequest;
import com.fooddonation.entity.User;
import com.fooddonation.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ✅ SIGNUP
    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return authService.registerUser(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // ✅ CHANGE PASSWORD (NEW API)
    @PutMapping("/change-password")
    public String changePassword(
            @RequestParam String email,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        return authService.changePassword(email, oldPassword, newPassword);
    }
    // ✅ UPDATE PROFILE
@PutMapping("/update-profile")
public User updateProfile(
        @RequestParam Long id,
        @RequestParam String name,
        @RequestParam String phone
) {
    return authService.updateProfile(id, name, phone);
}
}