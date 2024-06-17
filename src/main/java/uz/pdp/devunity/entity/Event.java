package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    private String title;
    private String body;
    private Integer participationLimit;
    @OneToOne(fetch = FetchType.LAZY)
    private Photo photo;

    @CurrentTimestamp
    private LocalDateTime startTime;
    private String place;
}