package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.entity.Event;
import uz.pdp.devunity.projection.EventDtoProjection;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query(nativeQuery = true,value = "select count(*) from events")
    Integer countEvents();

    @Query(nativeQuery = true,value = "select EXTRACT(MONTH FROM start_time) as month, count(*) as count from events e group by EXTRACT(MONTH FROM start_time) order by  EXTRACT(MONTH FROM start_time) desc; ")
    EventDtoProjection countEventsGroupByMonthOrderByTimeDesc();
}