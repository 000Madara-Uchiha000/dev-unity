package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Clazz;

import java.util.UUID;

public interface ClazzRepository extends JpaRepository<Clazz, UUID> {
}