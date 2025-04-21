package com.back.hello_ada_back.repositories;

import com.back.hello_ada_back.Models.Posts;
import com.back.hello_ada_back.Models.Users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    
}