package com.krushkov.virtualwallet.repositories;

import com.krushkov.virtualwallet.models.Role;
import com.krushkov.virtualwallet.models.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

}
