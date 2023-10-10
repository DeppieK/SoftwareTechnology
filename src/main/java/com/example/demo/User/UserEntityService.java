package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    private final UserRepository userRepository;

    @Autowired
    public UserEntityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUserByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
