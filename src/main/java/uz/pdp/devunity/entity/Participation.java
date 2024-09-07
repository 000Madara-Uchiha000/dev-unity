package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "participation")
public class Participation extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @ManyToOne
    private User user;

    @Column(columnDefinition = "boolean default false")
    private boolean isAttended;

    @ManyToOne
    private Team team;

    private boolean isLeader;
}