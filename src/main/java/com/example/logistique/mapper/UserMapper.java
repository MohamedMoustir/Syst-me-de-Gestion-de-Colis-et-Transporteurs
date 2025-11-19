package com.example.logistique.mapper;

import com.example.logistique.dto.UserDTO;
import com.example.logistique.enums.Role;
import com.example.logistique.enums.Specialite;
import com.example.logistique.enums.StatutTransporteur;
import com.example.logistique.model.User;

public class UserMapper {

    public static UserDTO toDTO(User entity) {
        if (entity == null) return null;

        return UserDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .role(entity.getRole() != null ? entity.getRole().name() : null)
                .active(entity.isActive())

                // TRANSPORTEUR ONLY
                .statut(entity.getTransporteurInfo() != null ?
                        entity.getTransporteurInfo().getStatut().name() : null)

                .specialite(entity.getTransporteurInfo() != null ?
                        entity.getTransporteurInfo().getSpecialite().name() : null)

                .build();
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User.UserBuilder builder = User.builder()
                .id(dto.getId())
                .login(dto.getLogin())
                .role(dto.getRole() != null ? Role.valueOf(dto.getRole()) : null)
                .active(dto.isActive());

        if (dto.getStatut() != null || dto.getSpecialite() != null) {
            builder.transporteurInfo(
                    new User.TransporteurInfo(
                            dto.getStatut() != null ? StatutTransporteur.valueOf(dto.getStatut()) : null,
                            dto.getSpecialite() != null ? Specialite.valueOf(dto.getSpecialite()) : null
                    )
            );
        }

        return builder.build();
    }
}
