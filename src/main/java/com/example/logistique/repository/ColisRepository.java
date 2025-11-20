package com.example.logistique.repository;

import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import com.example.logistique.model.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColisRepository extends MongoRepository<Colis, String> {

    Page<Colis> findByType(ColisType type, Pageable pageable);
  Page<Colis> findByStatut(StatutColis statut, Pageable pageable);
//
 Page<Colis> findByAdresseDestinationContainingIgnoreCase(String adresse, Pageable pageable);
//
//    Page<Colis> findByTransporteurId(String transporteurId, Pageable pageable);
//
//    Page<Colis> findByTransporteurIdAndType(String transporteurId, ColisType type, Pageable pageable);
}
