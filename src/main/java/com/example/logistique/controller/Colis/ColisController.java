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

    @Autowired
    private ColisServiceImpl colisService;

    @PostMapping("/admin/colis")
    public ColisDTO createColis(@RequestBody ColisDTO colisDTO) {
        return colisService.createColis(colisDTO);
    }

    @PutMapping("/admin/colis/{id}")
    public ColisDTO updateColis(@PathVariable String id, @RequestBody ColisDTO colisDTO) {
        return colisService.updateColis(id, colisDTO);
    }

    @DeleteMapping("/admin/colis/{id}")
    public void deleteColis(@PathVariable String id) {
        colisService.deleteColis(id);
    }

    @PatchMapping("/admin/colis/{id}/assign")
    public ColisDTO assignTransporteur(@PathVariable String id, @RequestParam String transporteurId) {
        return colisService.assignTransporteur(id, transporteurId);
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
