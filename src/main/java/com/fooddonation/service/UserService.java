package com.fooddonation.service;

import com.fooddonation.entity.NGO;
import com.fooddonation.entity.User;
import com.fooddonation.repository.NGORepository;
import com.fooddonation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private NGORepository ngoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<NGO> getAllVerifiedNGOs() {
        return ngoRepository.findByVerifiedTrue();
    }

    public List<NGO> getNGOsByState(String state) {
        return ngoRepository.findByStateAndVerifiedTrue(state);
    }

    public NGO getNGOById(Long id) {
        return ngoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NGO not found"));
    }

    public User updateUser(Long userId, String name, String profilePic) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(name);
        user.setProfilePic(profilePic);

        return userRepository.save(user);
    }
    public User changePassword(Long userId, String oldPass, String newPass) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getPassword().equals(oldPass)) {
        throw new RuntimeException("Old password incorrect");
    }

    user.setPassword(newPass);

    return userRepository.save(user);
}
}