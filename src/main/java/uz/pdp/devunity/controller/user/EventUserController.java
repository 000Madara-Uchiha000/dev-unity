package uz.pdp.devunity.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.devunity.dto.event.EventFullDto;
import uz.pdp.devunity.dto.event.EventShortDto;
import uz.pdp.devunity.dto.event.TeamEventRegisterRequest;
import uz.pdp.devunity.dto.user.UserShortDto;
import uz.pdp.devunity.entity.Participation;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.response.Response;
import uz.pdp.devunity.service.event.EventUserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event/user")
@RequiredArgsConstructor
@Tag(name = "Event management for Users")
public class EventUserController {

    private final EventUserService eventUserService;

    //    @Transactional
//    @PreAuthorize("hasRole('USER') and !hasRole('ADMIN') ")
//    @PostMapping("/register")
//    public HttpEntity<?> registerForEvent(@RequestParam UUID eventId){
//        Participation participation=eventUserService.registerForEvent(eventId);
//        return ResponseEntity.ok(
//                Response.builder().message("Successfully registered for event").data(participation).build());
//    }
    @Transactional
    @PreAuthorize("hasRole('USER') and !hasRole('ADMIN') ")
    @PostMapping("/register")
    public HttpEntity<?> registerForEvent(@RequestParam UUID eventId, @RequestBody(required = false) TeamEventRegisterRequest teamRegister) {
        eventUserService.registerForEvent(eventId, teamRegister);
        return ResponseEntity.ok(
                Response.builder().message("Successfully registered for event").build());
    }

    //    @Tag(name = "get all events")
    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents(Pageable pageable) {
        List<EventShortDto> events = eventUserService.getAllEventsShortVersion(pageable);
        return ResponseEntity.ok(
                Response.builder().message("All Events").data(events).build()
        );
    }

    //    @Tag(name = "get event full info")
    @GetMapping
    public ResponseEntity<?> getEventById(@RequestParam("id") UUID id) {
        EventFullDto event = eventUserService.getEventById(id);
        return ResponseEntity.ok(
                Response.builder().message("Event full info").data(event).build()
        );
    }

    @GetMapping("/classmates")
    public ResponseEntity<?> getAllClassmates() {
        List<UserShortDto> users=eventUserService.getClassmatesShortDto();
        return ResponseEntity.ok(
                Response.builder().message("All classmates").data(users).build()
        );
    }
}
