package uz.pdp.devunity.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.devunity.dto.event.EventCreationDto;
import uz.pdp.devunity.response.EventCreationResponse;
import uz.pdp.devunity.response.Response;
import uz.pdp.devunity.service.event.EventAdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/event")
@Tag(name = "Event management by admin")
public class EventAdminController {
    private final EventAdminService eventAdminService;
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Transactional
//    @Tag(name = "create event",description = "create event")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEvent(@ModelAttribute EventCreationDto eventDto) {
        EventCreationResponse eventResponse = eventAdminService.createEvent(eventDto);
        return ResponseEntity.ok(
                Response.builder().message("Saved event data").data(eventResponse).build()
        );
    }








}
