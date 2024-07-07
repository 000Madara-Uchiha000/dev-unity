package uz.pdp.devunity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.devunity.entity.Clazz;
import uz.pdp.devunity.entity.Role;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;
import uz.pdp.devunity.repo.ClazzRepository;
import uz.pdp.devunity.repo.RoleRepository;
import uz.pdp.devunity.repo.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ClazzRepository clazzRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) {
        if (ddl.equals("create")) {
            Role roleUser=Role.builder()
                    .roleEnum(ROLE_ENUM.ROLE_USER)
                    .build();
            Role roleAdmin=Role.builder()
                    .roleEnum(ROLE_ENUM.ROLE_ADMIN)
                    .build();
            Role roleSuperAdmin=Role.builder()
                    .roleEnum(ROLE_ENUM.ROLE_SUPER_ADMIN)
                    .build();

            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleSuperAdmin);

            User user = User.builder()
                    .email("user@gmail.com")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(roleUser))
                    .build();
            User user1 = User.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(roleUser,roleAdmin))
                    .build();
            User user2 = User.builder()
                    .email("super@gmail.com")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(roleUser,roleAdmin,roleSuperAdmin))
                    .build();

            userRepository.save(user);
            userRepository.save(user1);
            userRepository.save(user2);

            Clazz clazz=Clazz.builder()
                    .name("8-02")
                    .build();
            clazzRepository.save(clazz);


            Clazz clazz1=Clazz.builder()
                    .name("10-02")
                    .build();
            clazzRepository.save(clazz1);
        }

    }
}
