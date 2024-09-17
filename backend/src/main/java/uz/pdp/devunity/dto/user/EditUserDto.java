package uz.pdp.devunity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDto {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String bio;
    private byte[] photo;
}
