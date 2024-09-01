package uz.pdp.devunity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.entity.Event;
import uz.pdp.devunity.entity.EventPrize;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationResponse {
    private Event savedEvent;
    private List<EventPrize> savedPrizes;
}
