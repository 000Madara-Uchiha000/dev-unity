package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media extends BaseEntity {
    @ManyToOne
    private Event event;
    @OneToOne
    private Photo photo;
    private String url;
}