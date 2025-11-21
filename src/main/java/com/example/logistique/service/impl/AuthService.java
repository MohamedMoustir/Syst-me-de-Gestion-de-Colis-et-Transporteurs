package com.example.logistique.service.impl;

import com.example.logistique.dto.SignupRequest;
import com.example.logistique.enums.Role;
import com.example.logistique.mapper.UserMapper;
import com.example.logistique.model.User;
import com.example.logistique.repository.UserRepository;
import com.example.logistique.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder ;
    private final JwtUtil jwtUtil ;
private final UserMapper userMapper;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    public String signup(SignupRequest request){
        if(userRepository.existsByLogin(request.getLogin())) {
            throw new RuntimeException("Login already exists");
        }

        User user = User.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? Role.valueOf(request.getRole()) : Role.TRANSPORTEUR)
                .active(true)
                .build();
              userRepository.save(user);
        return jwtUtil.generateToken(user.getLogin());
    }

    public String login(String login, String password) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isActive()) throw new RuntimeException("User is inactive");

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid password");

        return jwtUtil.generateToken(user.getLogin());
    }
}
