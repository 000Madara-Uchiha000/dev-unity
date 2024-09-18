package uz.pdp.devunity.controller.checking;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/check/role")
@RequiredArgsConstructor
@Tag(name = "Role checking")
public class RoleCheckController {

    @GetMapping("/admin")
    public HttpEntity<Boolean> isAdmin(){
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/user")
    public HttpEntity<Boolean> isUser(){
        return ResponseEntity.ok().body(true);
    }
    @GetMapping("/super")
    public HttpEntity<Boolean> isSuperAdmin(){
        return ResponseEntity.ok().body(true);
    }

}
