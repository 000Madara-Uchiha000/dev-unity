package uz.pdp.devunity.dto;

import lombok.Value;
import uz.pdp.devunity.entity.Clazz;
import uz.pdp.devunity.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.devunity.entity.Bio}
 */
@Value
public class AdminUserBioDto implements Serializable {
    User user;
    String firstname;
    String lastname;
    Clazz clazz;
}