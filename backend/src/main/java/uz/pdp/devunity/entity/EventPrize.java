package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseUserEntity;

@Getter
@Setter
@Entity
public class EventPrize extends BaseUserEntity {
    @Column(length = 500)
    private String name;
    @Column(length = 10000)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Photo photo;
}
