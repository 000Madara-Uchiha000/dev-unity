package uz.pdp.devunity.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.devunity.dto.admin.AdminResponseProjectionDto;
import uz.pdp.devunity.dto.clazz.ClazzStatProjectionDto;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;
import uz.pdp.devunity.projection.EventDtoProjection;
import uz.pdp.devunity.repo.*;
import uz.pdp.devunity.response.Response;
import uz.pdp.devunity.response.SuperUserClazzStatResponse;
import uz.pdp.devunity.response.UserProfileResponse;
import uz.pdp.devunity.service.super_admin.SuperAdminService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/super/admin")
@RequiredArgsConstructor
@Tag(name = "Super Admin Controller")
public class SuperAdminController {

    private final UserRepository userRepository;
    private final ClazzRepository clazzRepository;
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;
    private final SuperAdminService superAdminService;
    private final AdminRepository adminRepository;

//    @Tag(name = "super user profile getting name and photo")
    @GetMapping("/profile")
    public ResponseEntity<?> getUserBio() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user.getBio()!=null) {
            var superUserProfileResponse = new UserProfileResponse(
                    user.getBio().getFirstname(),
                    user.getBio().getLastname(),
                    user.getBio().getPhoto().getPhoto());
            return ResponseEntity.ok(
                    Response.builder().message("Super User Profile").data(superUserProfileResponse).build()
            );

        }else {
            return ResponseEntity.notFound().build();
        }


    }

//    @Tag(name = "the statistics of classes in terms of user registration")
    @GetMapping("/statistics/clazz/list")
    public ResponseEntity<?> getClazzStat() {
        List<ClazzStatProjectionDto> clazzStatDtos = clazzRepository.generateStatisticByClazz();
        var superUserClazzStatResponse = new SuperUserClazzStatResponse(clazzStatDtos);
        return ResponseEntity.ok(
                Response.builder().message("Class Statistics").data(superUserClazzStatResponse).build()
        );
    }

//    @Tag(name = "The statistics of event.", description = "Result gives event count")
    @GetMapping("/statistics/event/count")
    public ResponseEntity<?> getEventCount() {
        Integer count = eventRepository.countEvents();
        return ResponseEntity.ok(
                Response.builder().message("Event quantity").data(count).build()
        );
    }

//    @Tag(name = "The statistics of event in terms of participant count")
    @GetMapping("/statistics/event/participant/count")
    public ResponseEntity<?> getParticipantCount() {
        Integer count = participationRepository.countAttendedParticipation();
        return ResponseEntity.ok(
                Response.builder().message("Attended participation quantity").data(count).build()
        );
    }

//    @Tag(name = "The statistics of devUnity member(count)")
    @GetMapping("/statistics/dev/member/count")
    public ResponseEntity<?> getDevUnityMemberCount() {
        Integer count = userRepository.countDevUnityMembers();
        return ResponseEntity.ok(
                Response.builder().message("Dev Unity member quantity").data(count).build()
        );
    }

//    @Tag(name = "The count of registered User")
    @GetMapping("/statistics/user/count")
    public ResponseEntity<?> getCountOfAllRegisteredUsers() {
        long count = userRepository.count();
        return ResponseEntity.ok(
                Response.builder().message("Registered users").data(count).build()
        );
    }

//    @Tag(name = "The statistics of event", description = "result give event count in each month")
    @GetMapping("/statistics/event/organize")
    public ResponseEntity<?> getCountOrganizedEvents() {
        EventDtoProjection eventDtoProjection = eventRepository.countEventsGroupByMonthOrderByTimeDesc();
        return ResponseEntity.ok(
                Response.builder().message("Event data group by month").data(eventDtoProjection).build()
        );
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<?> getRoles() {
        List<ADMIN_ROLE> adminRoles = Arrays.stream(ADMIN_ROLE.values()).toList();
        return ResponseEntity.ok(
                Response.builder().message("Admin roles").data(adminRoles).build()
        );
    }


//    @Tag(name = "Adding new admin")
    @PostMapping("/admins/add")
    public ResponseEntity<?> addAdmin(
            @RequestParam String username,
            @RequestParam ADMIN_ROLE adminRole,
            @RequestParam String role_description
    ) {
        superAdminService.addAdmin(username, adminRole, role_description);
        return ResponseEntity.ok("Admin successfully added ");
    }


//    @Tag(name = "Adding new admin role", description = "send userId")
    @PostMapping("/admins/role/add")
    public ResponseEntity<?> addAdminRole(
            @RequestParam UUID userId,
            @RequestParam ADMIN_ROLE adminRole,
            @RequestParam String role_description
    ) {
        superAdminService.addAdminRole(userId, adminRole, role_description);
        return ResponseEntity.ok("Admin Role successfully added");
    }

//    @Tag(name = "Delete admin role", description = "adminId")
    @DeleteMapping("/admins/role/delete")
    public ResponseEntity<?> deleteAdminRole(
            @RequestParam UUID adminId
    ) {
        superAdminService.deleteAdminRole(adminId);
        return ResponseEntity.ok("Admin Role successfully deleted");
    }


//    @Tag(name = "Extracting from admin role wholly", description = "send user Id not adminId")
    @DeleteMapping("/admins/delete")
    public ResponseEntity<?> deleteAdmin(@RequestParam UUID userId) {
        superAdminService.extractFromAdmin(userId);

        return ResponseEntity.ok("Admin successfully deleted");
    }

//    @Tag(name = "Get all admins")
    @GetMapping("/admins")
    public ResponseEntity<?> getAllAdmins() {
        List<AdminResponseProjectionDto> list = superAdminService.getAllAdmins();
        return ResponseEntity.ok(
                Response.builder().message("Admin List").data(list).build()
        );
    }

//    @Tag(name = "Get All Admin Roles", description = "such as Leader,Developer")
    @GetMapping("/admins/roles")
    public ResponseEntity<?> getAllAdminRoles() {
        List<ADMIN_ROLE> roles = superAdminService.getAllAdminRoles();
        return ResponseEntity.ok(
                Response.builder().message("Admin Roles").data(roles).build()
        );
    }

}
