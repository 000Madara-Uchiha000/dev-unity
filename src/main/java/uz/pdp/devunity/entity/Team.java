package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Team extends BaseEntity {
    @ManyToOne
    private Event event;

    private String name;
}
