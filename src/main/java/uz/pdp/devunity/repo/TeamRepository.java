package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Team;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}