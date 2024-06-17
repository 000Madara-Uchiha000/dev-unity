package uz.pdp.devunity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clazz")
public class Clazz extends BaseEntity {
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;
}