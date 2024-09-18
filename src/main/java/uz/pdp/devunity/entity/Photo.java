package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@Table(name = "photo")
public class Photo extends BaseEntity {

    private byte[] photo;


    public Photo(byte[] photo) {
        this.photo = photo;
    }

}