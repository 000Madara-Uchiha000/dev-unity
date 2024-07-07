package uz.pdp.devunity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.entity.Photo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperUserProfileResponse {

    private String firstName;
    private String lastName;
    private byte[] photo;
}
