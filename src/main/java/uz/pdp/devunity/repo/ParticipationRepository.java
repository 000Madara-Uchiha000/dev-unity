package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.entity.Participation;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    @Query(nativeQuery = true,value = "select count(*) from participation where is_attended=true")
    Integer countAttendedParticipation();

    @Query(nativeQuery = true,value="select count(*) from participation where event_id=?")
    Integer countBookedSeatsByEventId(UUID id);

    @Query(nativeQuery = true,value = "select count(distinct(team_id)) from participation p where event_id=? and team_id is not null")
    Integer countBookedTeamPlacesByEventId(UUID eventId);
    
}