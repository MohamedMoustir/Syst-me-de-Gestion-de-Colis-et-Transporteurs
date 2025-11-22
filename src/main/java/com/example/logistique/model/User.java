package com.example.logistique.model;

import com.example.logistique.enums.Role;
import com.example.logistique.enums.Specialite;
import com.example.logistique.enums.StatutTransporteur;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="users")
public  class User {

    @Id
    private String id ;
    @NotBlank
    private String login ;

    @NotBlank
    private String password;

    private Role role;
    private boolean active = true;

    private TransporteurInfo transporteurInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransporteurInfo {
        private StatutTransporteur statut;
        private Specialite specialite;
    }

}
