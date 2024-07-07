package uz.pdp.devunity.projection;

import java.util.UUID;

public interface AdminResponseDtoProjection {
    UUID getUserId();
    String getEmail();
    String getFirstName();
    String getLastName();

}
