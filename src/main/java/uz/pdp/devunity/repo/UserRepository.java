package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.devunity.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByEmail(String email);
}