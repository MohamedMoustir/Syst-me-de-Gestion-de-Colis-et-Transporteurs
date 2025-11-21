package com.example.logistique.controller.admin;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.service.impl.ColisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class ColisAdminController {

    @Autowired
    private ColisServiceImpl colisService;

    @PostMapping("/colis")
    public ColisDTO createColis(@RequestBody ColisDTO colisDTO) {
        return colisService.createColis(colisDTO);
    }

    @PutMapping("/colis/{id}")
    public ColisDTO updateColis(@PathVariable String id, @RequestBody ColisDTO colisDTO) {
        return colisService.updateColis(id, colisDTO);
    }

    @DeleteMapping("/colis/{id}")
    public void deleteColis(@PathVariable String id) {
        colisService.deleteColis(id);
    }

    @PatchMapping("/colis/{id}/assign")
    public ColisDTO assignTransporteur(@PathVariable String id, @RequestParam String transporteurId) {
        return colisService.assignTransporteur(id, transporteurId);
    }
}
