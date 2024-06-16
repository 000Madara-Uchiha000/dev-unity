package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User from;
    private String message;
}