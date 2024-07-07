package uz.pdp.devunity.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
import uz.pdp.devunity.response.Response;
import uz.pdp.devunity.security.JwtUtil;
import uz.pdp.devunity.service.AuthService;

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
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Tag(name = "login user")
    @Transactional
    @SneakyThrows
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password", e);
        }

        return ResponseEntity.ok(
                Response.builder().message("Token").data("Bearer " + jwtUtil.generateToken(loginDto.getEmail())).build()
        );
    }

    @Tag(name = "Register user")
    @Transactional
    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto registerDto) {

        User user = authService.createUser(registerDto);

        userRepository.save(user);

        return ResponseEntity.ok(
                Response.builder().message("Token").data("Bearer " + jwtUtil.generateToken(registerDto.getEmail())).build()
        );
    }

    @Tag(name = "get all Class Names")
    @GetMapping("/clazz")
    public HttpEntity<?> findAllClazzNames() {
        return ResponseEntity.ok(
                Response.builder().message("Class names").data(clazzRepository.findAllNames()).build()
        );
    }
}
