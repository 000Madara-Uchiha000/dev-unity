package uz.pdp.devunity.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.devunity.dto.LoginDto;
import uz.pdp.devunity.dto.RegisterDto;
import uz.pdp.devunity.entity.Bio;
import uz.pdp.devunity.entity.Clazz;
import uz.pdp.devunity.entity.Role;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;
import uz.pdp.devunity.repo.BioRepository;
import uz.pdp.devunity.repo.ClazzRepository;
import uz.pdp.devunity.repo.RoleRepository;
import uz.pdp.devunity.repo.UserRepository;
import uz.pdp.devunity.security.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ClazzRepository clazzRepository;
    private final UserRepository userRepository;
    private final BioRepository bioRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @SneakyThrows
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication auth;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }

        authenticationManager.authenticate(auth);
        return ResponseEntity.ok("Bearer " + jwtUtil.generateToken(loginDto.getEmail()));
//        return ResponseEntity.ok("togri");
    }

    @Transactional
    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto registerDto) {

        Clazz clazz = clazzRepository.findByName(registerDto.getClassName());
        Role role = roleRepository.findByRoleEnum(ROLE_ENUM.ROLE_USER);
        User user = User.builder()
                .roles(List.of(
                    role
                ))
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .bio(
                        Bio.builder()
                                .clazz(clazz)
                                .firstname(registerDto.getFirstName())
                                .lastname(registerDto.getLastName())
                                .build()
                )
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("Bearer " + jwtUtil.generateToken(registerDto.getEmail()));
    }
}
