package com.codecool.cookpad.service;

import com.codecool.cookpad.model.entity.User;
import com.codecool.cookpad.service.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findUserByName(String userName){
        return this.userRepository.findByName(userName).orElse(null);
    }
}
