package uz.pdp.devunity.dto.admin;

import lombok.*;
import uz.pdp.devunity.projection.AdminResponseDtoProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@NoArgsConstructor
public class AdminResponseProjectionDto implements AdminResponseDtoProjection {
    @Setter
    private UUID userId;
    @Setter
    private String username;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    private List<AdminDto> adminDtos=new ArrayList<>();

    public AdminResponseProjectionDto(UUID userId, String username, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
