package com.example.logistique.model;

import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "colis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colis {

    @Id
    private String id;

    @NotNull
    private ColisType type;
    @NotNull
    private double poids;
    @NotBlank
    private String adresseDestination;
    @NotNull
    private StatutColis statut =  StatutColis.EN_ATTENTE;
    private String transporteurId;

    private String instructionsManutention;
    private Double temperatureMin;
    private Double temperatureMax;

    private Instant createdAt;
    private Instant updatedAt;
}
