package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Bio;

import java.util.UUID;

public interface BioRepository extends JpaRepository<Bio, UUID> {
}