package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "about")
public class About extends BaseEntity {
    @NotBlank
    @NotNull
    private String quote;
    @ManyToOne
    private User user;
}