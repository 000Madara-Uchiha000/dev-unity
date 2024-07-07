package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.dto.AdminDto;
import uz.pdp.devunity.entity.Admin;
import uz.pdp.devunity.entity.User;

import java.util.List;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Admin findByUser(User user);

    void deleteAllByUser(User user);
    @Query(nativeQuery = true,value = "select a.id as adminId, a.admin_role as adminRole, a.role_desc as roleDesc from admins a where a.user_id=?")
    List<AdminDto> findAllByUserId(UUID userId);
}