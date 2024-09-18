package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.Message;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}