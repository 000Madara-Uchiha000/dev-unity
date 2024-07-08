package uz.pdp.devunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;
import uz.pdp.devunity.projection.AdminDtoProjection;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link uz.pdp.devunity.entity.Admin}
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDto implements Serializable, AdminDtoProjection {
    UUID adminId;
    ADMIN_ROLE adminRole;
    String roleDesc;
}