package com.back.hello_ada_back.services;

import com.back.hello_ada_back.Models.Posts;
import com.back.hello_ada_back.Models.Users;
import com.back.hello_ada_back.repositories.UsersRepository;
import com.back.hello_ada_back.repositories.PostsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users createUser(Users user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return usersRepository.save(user);
    }

    public Users findById(Long id) {
        return usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
    
} 