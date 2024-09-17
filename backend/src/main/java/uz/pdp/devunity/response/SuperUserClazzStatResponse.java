package uz.pdp.devunity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.devunity.dto.clazz.ClazzStatProjectionDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperUserClazzStatResponse {
    private List<ClazzStatProjectionDto> clazzStats;
}
