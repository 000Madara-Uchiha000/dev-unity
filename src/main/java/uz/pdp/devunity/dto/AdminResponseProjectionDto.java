package uz.pdp.devunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.projection.AdminResponseDtoProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseProjectionDto implements AdminResponseDtoProjection {
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
    private List<AdminDto> adminDtos=new ArrayList<>();
}
