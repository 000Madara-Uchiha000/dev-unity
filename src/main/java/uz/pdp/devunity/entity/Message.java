package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User from;

    @NotNull
    @NotBlank
    private String message;
}