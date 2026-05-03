package com.fooddonation.controller;

import com.fooddonation.entity.NGO;
import com.fooddonation.entity.User;
import com.fooddonation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // Get all NGOs
    @GetMapping("/ngos")
    public List<NGO> getAllNGOs() {
        return userService.getAllVerifiedNGOs();
    }

    // Search NGOs by state
    @GetMapping("/ngos/state")
    public List<NGO> getNGOsByState(@RequestParam String state) {
        return userService.getNGOsByState(state);
    }

    // NGO details
    @GetMapping("/ngos/{id}")
    public NGO getNGO(@PathVariable Long id) {
        return userService.getNGOById(id);
    }

    // Update profile
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String profilePic) {
        return userService.updateUser(id, name, profilePic);
    }
    @PutMapping("/{id}/change-password")
    public User changePassword(
        @PathVariable Long id,
        @RequestParam String oldPassword,
        @RequestParam String newPassword) {

    return userService.changePassword(id, oldPassword, newPassword);
    }
}