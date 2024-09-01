package uz.pdp.devunity.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.devunity.entity.*;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;
import uz.pdp.devunity.repo.AdminRepository;
import uz.pdp.devunity.repo.ClazzRepository;
import uz.pdp.devunity.repo.RoleRepository;
import uz.pdp.devunity.repo.UserRepository;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ClazzRepository clazzRepository;
    private final AdminRepository adminRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @SneakyThrows
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

            Clazz clazz=Clazz.builder()
                    .name("8-02")
                    .build();
            clazzRepository.save(clazz);


            Clazz clazz1=Clazz.builder()
                    .name("10-02")
                    .build();
            clazzRepository.save(clazz1);


            Path resourcePath = Paths.get("src/main/resources/def_super.png");

            InputStream resourceAsStream = Files.newInputStream(resourcePath);
            byte[] bytes = resourceAsStream.readAllBytes();
            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(roleUser))
                    .build();
            User user1 = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(roleUser,roleAdmin))
                    .build();
            User user2 = User.builder()
                    .username("super")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(roleUser,roleAdmin,roleSuperAdmin))
                    .bio(Bio.builder()
                            .firstname("Ja'farbek")
                            .lastname("Sayfiddinov")
                            .clazz(clazz1)
                            .bio("Everything is ok")
                            .photo(Photo.builder()
                                    .photo(bytes)
                                    .build())
                            .build())
                    .build();

            User user3 = User.builder()
                    .username("super1")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(roleUser,roleAdmin,roleSuperAdmin))
                    .bio(Bio.builder()
                            .firstname("Abdulaziz")
                            .lastname("Najmiddinov")
                            .clazz(clazz1)
                            .bio("Hakuna matata")
                            .photo(Photo.builder()
                                    .photo(bytes)
                                    .build())
                            .build())
                    .build();

            userRepository.save(user);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);



            Admin admin=Admin.builder()
                    .adminRole(ADMIN_ROLE.DEVELOPER)
                    .roleDesc("Backend developer")
                    .user(user2)
                    .build();


            Admin admin1=Admin.builder()
                    .adminRole(ADMIN_ROLE.DEVELOPER)
                    .roleDesc("Frontend developer")
                    .user(user3)
                    .build();

            adminRepository.save(admin);
            adminRepository.save(admin1);
        }

    }
}
