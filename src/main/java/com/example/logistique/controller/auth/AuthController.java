package com.example.logistique.controller.auth;

import com.example.logistique.dto.SignupRequest;
import com.example.logistique.service.impl.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request){
        String token = authService.signup(request);
        return ResponseEntity.ok("JWT Token: " + token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignupRequest request) {
        String token = authService.login(request.getLogin(), request.getPassword());
        return ResponseEntity.ok("JWT Token: " + token);
    }

}