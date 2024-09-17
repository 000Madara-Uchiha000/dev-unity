package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseUserEntity;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media extends BaseUserEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
    @OneToOne(fetch = FetchType.LAZY)
    private Photo photo;

    private String url;
}