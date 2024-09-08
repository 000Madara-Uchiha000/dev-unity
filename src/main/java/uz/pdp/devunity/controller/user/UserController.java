package uz.pdp.devunity.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.devunity.dto.user.EditUserDto;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.repo.UserRepository;
import uz.pdp.devunity.response.Response;
import uz.pdp.devunity.response.UserProfileResponse;
import uz.pdp.devunity.service.user.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User controller")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    @PutMapping("/edit")
    public HttpEntity<?> editUser(
            @RequestBody EditUserDto editUserDto
    ) {
        userService.editUser(editUserDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserBio() {

        UserDetails currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }


        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username);

        if (user.getBio() != null) {
            var userProfileResponse = new UserProfileResponse(
                    user.getBio().getFirstname(),
                    user.getBio().getLastname(),
                    user.getBio().getPhoto().getPhoto());
            return ResponseEntity.ok(
                    Response.builder().message("User Profile").data(userProfileResponse).build()
            );

        } else {
            return ResponseEntity.notFound().build();
        }


    }

}
