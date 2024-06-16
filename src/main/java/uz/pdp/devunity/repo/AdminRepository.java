package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Admin;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}