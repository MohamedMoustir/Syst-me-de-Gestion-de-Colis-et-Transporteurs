package com.example.logistique.service.impl;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import com.example.logistique.mapper.ColisMapper;
import com.example.logistique.model.Colis;
import com.example.logistique.repository.ColisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColisServiceImpl  {

    @Autowired
    private ColisRepository colisRepository;

    public ColisDTO createColis(ColisDTO colisDTO) {
        Colis colis = ColisMapper.toEntity(colisDTO);
        colis = colisRepository.save(colis);
        return ColisMapper.toDTO(colis);
    }

    public ColisDTO updateColis(String id, ColisDTO colisDTO) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis not found"));
        colis.setAdresseDestination(colisDTO.getAdresseDestination());
        colis.setPoids(colisDTO.getPoids());
        if (colisDTO.getType() != null) {
            colis.setType(ColisType.valueOf(colisDTO.getType()));
        }
        colis.setInstructionsManutention(colisDTO.getInstructionsManutention());
        colis.setTemperatureMin(colisDTO.getTemperatureMin());
        colis.setTemperatureMax(colisDTO.getTemperatureMax());
        colis = colisRepository.save(colis);
        return ColisMapper.toDTO(colis);
    }


    public void deleteColis(String id) {
        colisRepository.deleteById(id);
    }

    public ColisDTO updateStatut(String id, StatutColis statut) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis not found"));
        colis.setStatut(statut);
        colis = colisRepository.save(colis);
        return ColisMapper.toDTO(colis);
    }

    public ColisDTO assignTransporteur(String id, String transporteurId) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis not found"));
        colis.setTransporteurId(transporteurId);
        colis = colisRepository.save(colis);
        return ColisMapper.toDTO(colis);
    }

    public List<ColisDTO> listColis(Optional<ColisType> type, Optional<StatutColis> statut, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        List<Colis> result;
        if (type.isPresent() && statut.isPresent()) {
            result = colisRepository.findByType(type.get(), pageable).getContent().stream()
                    .filter(c -> c.getStatut() == statut.get())
                    .collect(Collectors.toList());
        } else if (type.isPresent()) {
            result = colisRepository.findByType(type.get(), pageable).getContent();
        } else if (statut.isPresent()) {
            result = colisRepository.findByStatut(statut.get(), pageable).getContent();
        } else {
            result = colisRepository.findAll(pageable).getContent();
        }
        return result.stream().map(ColisMapper::toDTO).collect(Collectors.toList());
    }
//    public List<ColisDTO> searchColisByAdresse(String adresse, int page, int size) {
//        PageRequest pageable = PageRequest.of(page, size);
//        return colisRepository.findByAdresseDestinationContainingIgnoreCase(adresse, pageable)
//                .getContent()
//                .stream()
//                .map(ColisMapper::toDTO)
//                .collect(Collectors.toList());
//    }


}
