package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo extends BaseEntity {

    private byte[] photo;


    public Photo(byte[] photo) {
        this.photo = photo;
    }

}