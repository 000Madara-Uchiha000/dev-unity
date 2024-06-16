package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bio")
public class Bio extends BaseEntity {
    @OneToOne(mappedBy = "bio")
    private User user;
    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;


    private String bio;

    @OneToOne
    private Photo photo;
    @ManyToOne(fetch = FetchType.LAZY)
    private Clazz clazz;
}