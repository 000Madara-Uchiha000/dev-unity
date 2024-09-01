package uz.pdp.devunity.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.devunity.dto.event_prize.PrizeRequestDto;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationDto {

    private String title;
    private String body;
    private Integer participationLimit;
    private LocalDateTime startTime;
    private String place;
    private MultipartFile eventPhoto;
    private LocalDateTime lastRegisterTime;

    //prize part
    private List<PrizeRequestDto> prizeRequestDtos;
}