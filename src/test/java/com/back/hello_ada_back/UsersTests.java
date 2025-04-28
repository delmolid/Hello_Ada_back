package com.back.hello_ada_back;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.back.hello_ada_back.Models.Users;
import com.back.hello_ada_back.repositories.UsersRepository;
import com.back.hello_ada_back.services.UsersService;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
public class UsersTests {

    @InjectMocks
    UsersService service;

    @Mock
    UsersRepository repository;

    @Test
    void testFindAllUsers() {
        List<Users> listOfUsers = new ArrayList<Users>();
        Users userOne = new Users("Clara","ClarasProfilePictureUrl","Description de Clara", "clara@mail.com", "ClarasPassword");
        Users userTwo = new Users("Louise","LouisesProfilePictureUrl","Description de Louise", "Louise@mail.com", "LouisesPassword");
        Users userThree = new Users("Logan","LogansProfilePictureUrl","Description de Logan", "Logan@mail.com", "LogansPassword");

        listOfUsers.add(userOne);
        listOfUsers.add(userTwo);
        listOfUsers.add(userThree);

        Mockito.when(repository.findAll()).thenReturn(listOfUsers);
        List<Users> listOfResult = service.findAll();
        assertEquals(3, listOfResult.size());
        verify(repository, times(1)).findAll();
        Assertions.assertThat(listOfResult).isEqualTo(listOfUsers);
    }

    @Test
    void testCreateUser() {
        Users userFour = new Users("Logan", "LogansProfilePictureUrl","Description de Logan", "Logan@mail.com", "LogansPassword");
        service.createUser(userFour);
        verify(repository, times(1)).save(userFour);
    }

    @Test
    void testGetUserById() {
        Users userFive = new Users("Hélène", "HélènesProfilePictureUrl","Description d'Hélène", "Hélène@mail.com", "HélènesPassword");
        userFive.setId(2L);
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(userFive));
        
        Users userFound = service.findById(2L);
        Assertions.assertThat(userFound).isEqualTo(userFive);
    }

    @Test
    void testDeletingUser() {
        Users userSix = new Users("Charlotte", "CharlottesProfilePictureUrl","Description de Charlotte", "Charlotte@mail.com", "CharlottesPassword");
        userSix.setId(8L);
        service.deleteUser(8L);
        Mockito.verify(repository, Mockito.times(1)).deleteById(8L);
    }

    @Test
    void testUpdateUser() {
        Users userSeven = new Users("Molid", "Molid'sProfilePictureUrl","Description de Molid", "Molid@mail.com", "MolidsPassword");
        userSeven.setId(21L);
        when(repository.findById(21L)).thenReturn(Optional.of(userSeven));
        when(repository.save(any(Users.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Users userSevenUpdate = service.updateUserProfile(21L, "Molid", "MolidsNewProfilUrl", "Nouvelle description de Molid");
        
        assertNotNull(userSevenUpdate);
        assertEquals("Molid", userSevenUpdate.getUsername());
        assertEquals("MolidsNewProfilUrl", userSevenUpdate.getProfilPicture());
        assertEquals("Nouvelle description de Molid", userSevenUpdate.getDescription());

        verify(repository).findById(21L);
        verify(repository).save(any(Users.class));

    }

}
