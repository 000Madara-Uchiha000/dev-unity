package uz.pdp.devunity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private Object data;
    private Map<String, Object> meta = new LinkedHashMap<>();
}
