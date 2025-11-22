package com.example.logistique.controller.Colis;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import com.example.logistique.service.impl.ColisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ColisController {


    private final ColisServiceImpl colisService;

    public ColisController(ColisServiceImpl colisService) {
        this.colisService = colisService;
    }


    @PatchMapping("/colis/{id}/statut")
    public ColisDTO updateStatut(@PathVariable String id, @RequestParam StatutColis statut) {
        return colisService.updateStatut(id, statut);
    }

    @GetMapping("/colis")
    public List<ColisDTO> listColis(
            @RequestParam Optional<ColisType> type,
            @RequestParam Optional<StatutColis> statut,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return colisService.listColis(type, statut, page, size);
    }

    @GetMapping("/colis/search")
    public List<ColisDTO> searchColis(@RequestParam String adresse,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return colisService.searchColisByAdresse(adresse, page, size);
    }



}
