package com.fundoo.notes.service;

import com.fundoo.notes.dto.LoginRequest;
import com.fundoo.notes.dto.RegisterRequest;
import com.fundoo.notes.entity.User;
import com.fundoo.notes.exception.ResourceNotFoundException;
import com.fundoo.notes.repository.UserRepository;
import com.fundoo.notes.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final RedisService redisService;

    @Override
    public String register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        repository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        redisService.save(user.getEmail(), token);

        return token;
    }
}