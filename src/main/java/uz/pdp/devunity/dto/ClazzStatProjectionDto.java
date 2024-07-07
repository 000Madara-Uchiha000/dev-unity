package uz.pdp.devunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.projection.ClazzStatProjection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzStatProjectionDto implements ClazzStatProjection {
    private String clazzName;
    private Integer studentCount;
}
