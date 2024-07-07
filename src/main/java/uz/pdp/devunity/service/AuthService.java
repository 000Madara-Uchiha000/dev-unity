package uz.pdp.devunity.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.devunity.dto.RegisterDto;
import uz.pdp.devunity.entity.Bio;
import uz.pdp.devunity.entity.Clazz;
import uz.pdp.devunity.entity.Role;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;
import uz.pdp.devunity.repo.ClazzRepository;
import uz.pdp.devunity.repo.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ClazzRepository clazzRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(RegisterDto registerDto) {
        Clazz clazz = clazzRepository.findByName(registerDto.getClassName());
        Role role = roleRepository.findByRoleEnum(ROLE_ENUM.ROLE_USER);

        return User.builder()
                .roles(List.of(
                        role
                ))
                .email(registerDto.getEmail())
                .password(
                        passwordEncoder.encode(
                                registerDto.getPassword())

                )
                .bio(
                        Bio.builder()
                                .clazz(clazz)
                                .firstname(registerDto.getFirstName())
                                .lastname(registerDto.getLastName())
                                .build()
                )
                .build();

    }
}
