package uz.pdp.devunity.projection;

import uz.pdp.devunity.entity.enums.ADMIN_ROLE;

import java.util.UUID;

public interface AdminDtoProjection {
    UUID getAdminId();
    ADMIN_ROLE getAdminRole();
    String getRoleDesc();
}
