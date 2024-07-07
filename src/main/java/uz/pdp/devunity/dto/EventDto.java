package uz.pdp.devunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.projection.EventDtoProjection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto implements EventDtoProjection {
    private Integer count;
    private Integer month;
}
