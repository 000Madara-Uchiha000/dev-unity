package uz.pdp.devunity.service.checking;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.devunity.service.user.UserService;

@Service
@RequiredArgsConstructor
public class RoleChecking {

    private final UserService userService;

    public boolean isAdmin(){
        UserDetails userDetails = userService.getCurrentUser();
        if (userDetails==null) return false;
        return userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
    public boolean isUser(){
        UserDetails userDetails = userService.getCurrentUser();
        if (userDetails==null) return false;
        return userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
    }
    public boolean isSuperAdmin(){
        UserDetails userDetails = userService.getCurrentUser();
        if (userDetails==null) return false;
        return userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"));
    }
    public boolean isAuthorized(){
        UserDetails userDetails = userService.getCurrentUser();
        return userDetails!=null;
    }
}
