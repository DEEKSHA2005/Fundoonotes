package com.fundoo.notes.service;

import com.fundoo.notes.dto.LoginRequest;
import com.fundoo.notes.dto.RegisterRequest;

public interface UserService {
    String register(RegisterRequest request);
    String login(LoginRequest request);
}