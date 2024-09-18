package uz.pdp.devunity.projection;

import org.springframework.beans.factory.annotation.Value;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;

import java.util.UUID;

public interface AdminDtoProjection {
    @Value("#{target.adminId}")
    UUID getAdminId();
    @Value("#{target.adminRole}")
    ADMIN_ROLE getAdminRole();
    @Value("#{target.roleDesc}")
    String getRoleDesc();
}
