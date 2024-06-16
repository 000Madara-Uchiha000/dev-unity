package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}