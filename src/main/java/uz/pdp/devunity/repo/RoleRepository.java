package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Role;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByRoleEnum(ROLE_ENUM roleEnum);
}