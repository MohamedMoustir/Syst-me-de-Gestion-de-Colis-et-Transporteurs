package com.example.logistique.controller.admin;

import com.example.logistique.dto.UserDTO;
import com.example.logistique.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserAdminController {

    private UserServiceImpl userService;


    @GetMapping("/users")
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable String id, @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/users/{id}/active")
    public UserDTO toggleActive(@PathVariable String id, @RequestParam boolean active) {
        return userService.toggleActive(id, active);
    }
}
