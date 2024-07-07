package uz.pdp.devunity.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.devunity.dto.EditUserDto;
import uz.pdp.devunity.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Transactional
    @PostMapping("/edit")
    public HttpEntity<?> editUser(
            @RequestBody EditUserDto editUserDto
    ) {
        userService.editUser(editUserDto);
        return ResponseEntity.ok().build();
    }

    
}
