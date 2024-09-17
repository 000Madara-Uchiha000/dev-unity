package uz.pdp.devunity.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.projection.EventDtoProjection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventStatisticsDto implements EventDtoProjection {
    private Integer count;
    private Integer month;
}
