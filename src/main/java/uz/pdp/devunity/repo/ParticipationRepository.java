package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.entity.Participation;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    @Query(nativeQuery = true,value = "select count(*) from participation where is_attended=true")
    Integer countAttendedParticipation();
}