package uz.pdp.devunity.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.devunity.dto.EditUserDto;
import uz.pdp.devunity.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Transactional
    @PutMapping("/edit")
    public HttpEntity<?> editUser(
            @RequestBody EditUserDto editUserDto
    ) {
        userService.editUser(editUserDto);
        return ResponseEntity.ok().build();
    }

    
}
