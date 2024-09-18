package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Media;

import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {
}