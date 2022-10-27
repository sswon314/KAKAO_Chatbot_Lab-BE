package com.example.goENC.repositories;

import com.example.goENC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    List<User> findAll();
}
