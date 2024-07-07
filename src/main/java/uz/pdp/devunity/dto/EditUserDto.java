package uz.pdp.devunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.entity.Photo;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDto {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String bio;
    private byte[] photo;
}
