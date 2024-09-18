package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.About;

import java.util.UUID;

public interface AboutRepository extends JpaRepository<About, UUID> {
}