package com.back.hello_ada_back.services;

import com.back.hello_ada_back.Models.Users;
import com.back.hello_ada_back.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(Users user) {
        // Validation basique si nécessaire
        return usersRepository.save(user);
    }

    public Users findById(Long id) {
        return usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
} 