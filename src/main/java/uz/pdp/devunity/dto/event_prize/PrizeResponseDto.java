package uz.pdp.devunity.dto.event_prize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizeResponseDto {
    private String name;
    private String description;
    private byte[] photo;
}

