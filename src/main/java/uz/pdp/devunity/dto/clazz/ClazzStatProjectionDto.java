package uz.pdp.devunity.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzStatProjectionDto  {
    private String clazzName;
    private Long studentCount;
}
