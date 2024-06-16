package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "about")
public class About extends BaseEntity {
    private String quote;
    @ManyToOne
    private User user;
}