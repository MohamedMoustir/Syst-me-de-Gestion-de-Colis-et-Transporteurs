package com.example.logistique.repository;

import com.example.logistique.enums.Role;
import com.example.logistique.enums.Specialite;
import com.example.logistique.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByLogin(String login);
    Page<User> findAllByRole(Role role, Pageable pageable);
    List<User> findAllByTransporteurProfile_Specialite(Specialite specialite, Pageable pageable);
}
