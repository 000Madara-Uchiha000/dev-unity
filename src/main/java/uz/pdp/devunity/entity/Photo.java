package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo extends BaseEntity {

    private byte[] photo;
}