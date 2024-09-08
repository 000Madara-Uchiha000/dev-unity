package uz.pdp.devunity.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.dto.event_prize.PrizeResponseDto;
import uz.pdp.devunity.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {
    private UUID id;
    private Integer participation_limit;
    private byte[] photo;
    private LocalDateTime start_time;
    private String title;
    private String place;
    private String body;
    private LocalDateTime lastRegisterTime;
    private Integer availability;

    private Boolean isRegistered;
    private Integer teamNumber;
    private Integer teamSize;
    private List<PrizeResponseDto> prizeDtos;
    public EventFullDto(Event event) {
        this.id = event.getId();
        this.participation_limit = event.getParticipationLimit();
        this.title= event.getTitle();
        this.place = event.getPlace();
        this.body = event.getBody();
        this.start_time = event.getStartTime();
        this.photo=event.getPhoto().getPhoto();
        this.lastRegisterTime=event.getLastRegisterTime();
        this.teamSize=event.getTeamSize();
        this.teamNumber=event.getTeamNumber();
    }
}
