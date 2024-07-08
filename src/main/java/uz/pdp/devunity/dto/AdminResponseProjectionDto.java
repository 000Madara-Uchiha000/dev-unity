package uz.pdp.devunity.dto;

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
    private String email;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    private List<AdminDto> adminDtos=new ArrayList<>();

    public AdminResponseProjectionDto(String lastName, String firstName, String email, UUID userId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.userId = userId;
    }
}
