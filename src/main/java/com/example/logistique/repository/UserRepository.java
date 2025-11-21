package com.example.logistique.repository;

import com.example.logistique.enums.Role;
import com.example.logistique.enums.Specialite;
import com.example.logistique.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    Page<User> findByRole(Role role, Pageable pageable);

    Page<User> findByRoleAndTransporteurInfoSpecialite(
            Role role,
            Specialite specialite,
            Pageable pageable
    );
}
