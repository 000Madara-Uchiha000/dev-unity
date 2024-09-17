package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseUserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event extends BaseUserEntity {
    @Column(length = 500)
    private String title;
    @Column(length = 10000)
    private String body;
    @Min(value = 2, message = "Participation limit must be greater than 1")  // Validate that teamSize is greater than 1
    private Integer participationLimit;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Photo photo;
    private LocalDateTime lastRegisterTime;
    private LocalDateTime startTime;
    private String place;

    @Min(value = 2, message = "Team size must be greater than 1")  // Validate that teamSize is greater than 1
    private Integer teamSize;
    @Min(value = 2, message = "Team number must be greater than 1")  // Validate that teamSize is greater than 1
    private Integer teamNumber;
}