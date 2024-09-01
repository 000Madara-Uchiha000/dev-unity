package uz.pdp.devunity.dto.event_prize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizeRequestDto {
    private String name;
    private String description;
    private MultipartFile photo;
}
