package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;

@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends BaseEntity {
  @ManyToOne
  private User user;
  @Enumerated(EnumType.STRING)
  private ADMIN_ROLE adminRole;
  private String roleDesc;
}