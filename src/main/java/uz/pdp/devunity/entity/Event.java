package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseUserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event extends BaseUserEntity {
    private String title;
    private String body;
    private Integer participationLimit;
    @OneToOne(fetch = FetchType.LAZY)
    private Photo photo;

    private LocalDateTime startTime;
    private Integer probableHours;
    private String place;
}