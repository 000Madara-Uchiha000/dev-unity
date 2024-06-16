package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    private ROLE_ENUM roleEnum;

    @Override
    public String getAuthority() {
        return roleEnum.name();
    }
}