package com.powerledger.powerplant.repository;

import com.powerledger.powerplant.enums.RoleEnum;
import com.powerledger.powerplant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
