package com.example.logistique.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping("/github-webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
        System.out.println("\n===== WEBHOOK RECEIVED =====");
        return ResponseEntity.ok("OK");
    }
}
