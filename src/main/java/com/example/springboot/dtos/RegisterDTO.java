package com.example.springboot.dtos;

import com.example.springboot.models.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
