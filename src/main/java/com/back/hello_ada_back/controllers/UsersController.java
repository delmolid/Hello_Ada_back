package com.back.hello_ada_back.controllers;

import com.back.hello_ada_back.Models.Users;
import com.back.hello_ada_back.services.UsersService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //Routes permettant la modification de l'email et du mdp, ne marchent pas car pas encore de fonction connexion/déconnexion (password encoder)

    // @PutMapping("/{id}/change-email")
    // public ResponseEntity<?> changeEmail(@PathVariable Long id, @Valid @RequestBody EmailChangeDTO emailDTO) {
    //     Users user = usersService.findById(id);
        
    //     // Vérifier le mot de passe pour sécuriser le changement d'email
    //     if (!passwordEncoder.matches(emailDTO.getPassword(), user.getPassword())) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
    //     }
        
    //     // Mettre à jour l'email
    //     user.setEmail(emailDTO.getNewEmail());
    //     usersService.updateUser(user);
        
    //     return ResponseEntity.ok().body("Email modifié");
    // }
    
    // @PutMapping("/{id}/change-password")
    // public ResponseEntity<?> changePassword(@PathVariable Long id, @Valid @RequestBody PasswordChangeDTO passwordDTO) {
    //     Users user = usersService.findById(id);
        
    //     // Vérifier que l'ancien mot de passe est correct
    //     if (!passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword())) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le mot de passe est incorrect");
    //     }
        
    //     // Vérifier que les nouveaux mots de passe correspondent
    //     if (!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
    //         return ResponseEntity.badRequest().body("Les nouveaux mots de passe ne correspondent pas");
    //     }
        
    //     // Mettre à jour le mot de passe
    //     user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
    //     usersService.updateUser(user);
        
    //     return ResponseEntity.ok().body("Mot de passe modifié");
    // }

    @PutMapping("/{id}/updateProfile")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Users userUpdate) {
        try {
            Users currentUserProfil = usersService.findById(id);

            Users updatedUser = usersService.updateUserProfile(id, 
            userUpdate.getUsername(),
            userUpdate.getProfilPicture(),
            userUpdate.getDescription());
            return ResponseEntity.ok().body(updatedUser);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur de la mise à jour du profil : " + e.getMessage());
        }
    }
}