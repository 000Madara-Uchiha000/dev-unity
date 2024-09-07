package uz.pdp.devunity.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamEventRegisterRequest {
    private String teamName;
    private LinkedHashSet<UUID> teamMemberIds;
}
