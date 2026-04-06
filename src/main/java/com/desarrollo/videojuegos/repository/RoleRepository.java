package com.desarrollo.videojuegos.repository;

import com.desarrollo.videojuegos.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Optional<Role> findByName(String name);
}
