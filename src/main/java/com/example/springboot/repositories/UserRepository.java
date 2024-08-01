package com.example.springboot.repositories;

import com.example.springboot.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, String> {
    // findBy + coluna do banco de dados
    UserDetails findByLogin(String login);
}
