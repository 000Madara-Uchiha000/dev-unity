package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clazz")
public class Clazz extends BaseEntity {
    private String name;
}