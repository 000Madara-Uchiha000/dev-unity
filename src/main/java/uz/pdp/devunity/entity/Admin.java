package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.devunity.entity.base.BaseEntity;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;

@Getter
@Setter
@Entity
@Builder
@Table(name = "admins")
public class Admin extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
  @Enumerated(EnumType.STRING)
  private ADMIN_ROLE adminRole;
  private String roleDesc;


  public Admin(User user, ADMIN_ROLE adminRole, String roleDesc) {
    this.user = user;
    this.adminRole = adminRole;
    this.roleDesc = roleDesc;
  }

  public Admin() {

  }
}