package com.example.logistique.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColisDTO {

    private String id;
    private String type;
    private double poids;
    private String adresseDestination;
    private String statut;

    private String transporteurId;

    private String instructionsManutention;

    private Double temperatureMin;
    private Double temperatureMax;
}
