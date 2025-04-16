package com.back.hello_ada_back.controllers;

import com.back.hello_ada_back.Models.Posts;
import com.back.hello_ada_back.Models.Users;
import com.back.hello_ada_back.services.UsersService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    
    @Autowired
    private UsersService usersService;


    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Users createUser(@Valid @RequestBody Users user) {
        try {
            logger.info("Tentative de création d'utilisateur avec les données : {}", user);
            if (user.getEmail() == null || user.getPassword() == null) {
                throw new IllegalArgumentException("Email et mot de passe sont requis");
            }
            return usersService.createUser(user);
        } catch (Exception e) {
            logger.error("Erreur lors de la création de l'utilisateur : {}", e.getMessage());
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "Erreur lors de la création de l'utilisateur : " + e.getMessage()
            );
        }
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable Long id) {
        return usersService.findById(id);
    }

    @GetMapping
    public List<Users> findAll() {
        return usersService.findAll();
    }
    

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
    
} 