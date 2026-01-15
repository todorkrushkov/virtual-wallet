package krushkov.project.com.virtualwallet.repositories;

import krushkov.project.com.virtualwallet.models.Role;
import krushkov.project.com.virtualwallet.models.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

}
