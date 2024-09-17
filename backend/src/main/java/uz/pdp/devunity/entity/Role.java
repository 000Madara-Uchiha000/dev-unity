package uz.pdp.devunity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.devunity.entity.base.BaseEntity;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    private ROLE_ENUM roleEnum;

    @Override
    public String getAuthority() {
        return roleEnum.name();
    }
}