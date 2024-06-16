package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Participation;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
}