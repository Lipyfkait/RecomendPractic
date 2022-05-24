package ru.ithub.examination.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ithub.examination.domain.entity.ERole;
import ru.ithub.examination.domain.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    boolean existsByName(ERole name);
}

