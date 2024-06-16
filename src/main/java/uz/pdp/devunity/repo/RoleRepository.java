package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}