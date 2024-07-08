package uz.pdp.devunity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.devunity.dto.AdminResponseProjectionDto;
import uz.pdp.devunity.entity.base.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SqlResultSetMapping(
        name = "AdminResponseProjectionDtoMapping",
        classes = @ConstructorResult(
                targetClass = AdminResponseProjectionDto.class,
                columns = {
                        @ColumnResult(name = "userId", type = UUID.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "firstName", type = String.class),
                        @ColumnResult(name = "lastName", type = String.class)
                }
        )
)
public class User extends BaseEntity implements UserDetails {
    @NotBlank
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "bio_id",referencedColumnName = "id")
    private Bio bio;

    @ManyToMany
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }




}