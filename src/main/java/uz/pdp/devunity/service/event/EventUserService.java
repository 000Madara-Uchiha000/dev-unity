package uz.pdp.devunity.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.devunity.dto.event.EventFullDto;
import uz.pdp.devunity.dto.event.EventShortDto;
import uz.pdp.devunity.dto.event_prize.PrizeResponseDto;
import uz.pdp.devunity.entity.Event;
import uz.pdp.devunity.entity.Participation;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.repo.EventPrizeRepository;
import uz.pdp.devunity.repo.EventRepository;
import uz.pdp.devunity.repo.ParticipationRepository;
import uz.pdp.devunity.repo.UserRepository;
import uz.pdp.devunity.service.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventUserService {
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;
    private final EventPrizeRepository eventPrizeRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public List<EventShortDto> getAllEventsShortVersion(Pageable pageable) {
        return eventRepository.findAllShort(pageable);
    }

    public EventFullDto getEventById(UUID id) {
        Optional<Event> opt = eventRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Event not found");
        }
        Event event = opt.get();
        EventFullDto eventFullDto = new EventFullDto(event);
        Integer bookedCount = participationRepository.countBookedSeatsByEventId(id);
        eventFullDto.setAvailable_seats(
                eventFullDto.getParticipation_limit() - bookedCount
        );

        List<PrizeResponseDto> prizes = eventPrizeRepository.findAllByEventIdConvertIntoEventResponseDto(id);
        eventFullDto.setPrizeDtos(prizes);
        return eventFullDto;
    }

    public Participation registerForEvent(UUID eventId) {
        Optional<Event> opt = eventRepository.findById(eventId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Event not found");
        }
        Event event = opt.get();


        UserDetails currentUser = userService.getCurrentUser();
        if (currentUser==null){
            throw new RuntimeException("User not logged in");
        }
        if (!isUser(currentUser)){
            throw new RuntimeException("Only users can register events");
        }


        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username);

        Participation participation = new Participation();
        participation.setEvent(event);
        participation.setUser(user);
        return participationRepository.save(participation);
    }

    private boolean isUser(UserDetails currentUser) {
        return currentUser.getAuthorities().size()==1 && currentUser.getAuthorities().stream().allMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("USER"));
    }
}
