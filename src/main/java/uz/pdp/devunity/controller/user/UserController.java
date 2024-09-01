package uz.pdp.devunity.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.devunity.dto.user.EditUserDto;
import uz.pdp.devunity.service.user.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User controller")
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
