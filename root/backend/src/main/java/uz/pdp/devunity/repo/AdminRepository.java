package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.dto.admin.AdminDto;
import uz.pdp.devunity.entity.Admin;
import uz.pdp.devunity.entity.User;

import java.util.List;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Admin findByUser(User user);

    void deleteAllByUser(User user);

//    @Query(value = "a.id as adminId, a.admin_role as adminRole, a.role_desc as roleDesc from Ad a where a.user_id=?")
    @Query(value = "select new uz.pdp.devunity.dto.admin.AdminDto(a.id,a.adminRole,a.roleDesc) from Admin a where a.user.id=?1")
//    @Query(nativeQuery = true,value = "select id as adminId,admin_role as adminRole, role_desc as roleDesc from admins where user_id=?;")
    List<AdminDto> findAllByUserId(UUID userId);
}