package com.example.logistique.controller.admin;

import com.example.logistique.dto.UserDTO;
import com.example.logistique.enums.Specialite;
import com.example.logistique.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class TransporteurAdminController {

    private UserServiceImpl userService;

    @GetMapping("/transporteurs")
    public List<UserDTO> listTransporteurs(
            @RequestParam Optional<Specialite> specialite,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return userService.listTransporteurs(specialite, page, size);
    }
}
