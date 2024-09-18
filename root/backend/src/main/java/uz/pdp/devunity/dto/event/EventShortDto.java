package uz.pdp.devunity.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDto {
    private UUID id;
    private String title;
    private byte[] photo;
}
