package uz.pdp.devunity.entity;

import jakarta.persistence.*;
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
    private Integer participationLimit;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Photo photo;
    private LocalDateTime lastRegisterTime;
    private LocalDateTime startTime;
    private String place;
}