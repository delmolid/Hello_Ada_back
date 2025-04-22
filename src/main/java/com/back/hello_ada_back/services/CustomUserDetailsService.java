package com.back.hello_ada_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Arrays;
import java.util.List;

import com.back.hello_ada_back.repositories.UsersRepository;
import com.back.hello_ada_back.Models.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService {

 @Autowired 
  private UsersRepository usersRepository;   

@Override 
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users user = usersRepository.findByEmail(email);
    
    // Créer une liste d'autorités avec un rôle par défaut
    List<SimpleGrantedAuthority> authorities = Arrays.asList(
        new SimpleGrantedAuthority("ROLE_USER")
    );

    return new User(user.getEmail(), user.getPassword(), authorities);
}

}
