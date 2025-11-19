package com.example.logistique.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String id;
    private String login;
    private String role;
    private boolean active;

    private String statut;
    private String specialite;
}
