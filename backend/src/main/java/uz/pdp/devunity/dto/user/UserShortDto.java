package uz.pdp.devunity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {
    private UUID userId;
    private String username;
    private String firstName;
    private String lastName;
    private String clazzName;

}
