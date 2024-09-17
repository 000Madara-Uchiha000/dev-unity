package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Photo;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}