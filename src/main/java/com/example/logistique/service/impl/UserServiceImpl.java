package com.example.logistique.service.impl;

import com.example.logistique.dto.UserDTO;
import com.example.logistique.enums.Role;
import com.example.logistique.enums.Specialite;
import com.example.logistique.enums.StatutTransporteur;
import com.example.logistique.mapper.UserMapper;
import com.example.logistique.model.User;
import com.example.logistique.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> listTransporteurs(Optional<Specialite> specialite, int page, int size) {
        Page<User> users;
        if (specialite.isPresent()) {
            users = userRepository.findByRoleAndTransporteurInfoSpecialite(Role.ROLE_TRANSPORTEUR, specialite.get(), PageRequest.of(page, size));
        } else {
            users = userRepository.findByRole(Role.ROLE_TRANSPORTEUR, PageRequest.of(page, size));
        }
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public UserDTO updateUser(String id, UserDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existing.setLogin(dto.getLogin());
        existing.setActive(dto.isActive());
        if (existing.getTransporteurInfo() != null && dto.getStatut() != null)
            existing.getTransporteurInfo().setStatut(dto.getStatut() != null ? StatutTransporteur.valueOf(dto.getStatut()) : null);
        if (existing.getTransporteurInfo() != null && dto.getSpecialite() != null)
            existing.getTransporteurInfo().setSpecialite(dto.getSpecialite() != null ? Specialite.valueOf(dto.getSpecialite()) : null);

        return UserMapper.toDTO(userRepository.save(existing));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public UserDTO toggleActive(String id, boolean active) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(active);
        return UserMapper.toDTO(userRepository.save(user));
    }
}
