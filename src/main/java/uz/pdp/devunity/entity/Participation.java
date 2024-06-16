package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "participation")
public class Participation extends BaseEntity {
    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAttended;
}