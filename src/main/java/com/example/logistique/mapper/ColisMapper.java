package com.example.logistique.mapper;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import com.example.logistique.model.Colis;

public class ColisMapper {

    public static ColisDTO toDTO(Colis entity) {
        if (entity == null) return null;

        return ColisDTO.builder()
                .id(entity.getId())
                .type(entity.getType() != null ? entity.getType().name() : null)
                .poids(entity.getPoids())
                .adresseDestination(entity.getAdresseDestination())
                .statut(entity.getStatut() != null ? entity.getStatut().name() : null)
                .transporteurId(entity.getTransporteurId())
                .instructionsManutention(entity.getInstructionsManutention())
                .temperatureMin(entity.getTemperatureMin())
                .temperatureMax(entity.getTemperatureMax())
                .build();
    }


    public static Colis toEntity(ColisDTO dto) {
        if (dto == null) return null;

        return Colis.builder()
                .id(dto.getId())
                .type(dto.getType() != null ? ColisType.valueOf(dto.getType()) : null)
                .poids(dto.getPoids())
                .adresseDestination(dto.getAdresseDestination())
                .statut(dto.getStatut() != null ? StatutColis.valueOf(dto.getStatut()) : StatutColis.EN_ATTENTE)
                .transporteurId(dto.getTransporteurId())
                .instructionsManutention(dto.getInstructionsManutention())
                .temperatureMin(dto.getTemperatureMin())
                .temperatureMax(dto.getTemperatureMax())
                .build();
    }
}
