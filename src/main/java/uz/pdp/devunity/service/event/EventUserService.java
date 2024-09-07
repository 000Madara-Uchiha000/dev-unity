package uz.pdp.devunity.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.devunity.dto.event.EventFullDto;
import uz.pdp.devunity.dto.event.EventShortDto;
import uz.pdp.devunity.dto.event.TeamEventRegisterRequest;
import uz.pdp.devunity.dto.event_prize.PrizeResponseDto;
import uz.pdp.devunity.dto.user.UserShortDto;
import uz.pdp.devunity.entity.Event;
import uz.pdp.devunity.entity.Participation;
import uz.pdp.devunity.entity.Team;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.repo.*;
import uz.pdp.devunity.service.user.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EventUserService {
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;
    private final EventPrizeRepository eventPrizeRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ClazzRepository clazzRepository;
    private final BioRepository bioRepository;

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

        Integer availability = calculateAvailability(id);
        eventFullDto.setAvailability(
                availability
        );

        List<PrizeResponseDto> prizes = eventPrizeRepository.findAllByEventIdConvertIntoEventResponseDto(id);
        eventFullDto.setPrizeDtos(prizes);
        return eventFullDto;
    }

    private Integer calculateAvailability(UUID eventId) {
        Integer availability = 0;
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        if (event.getParticipationLimit() != null && event.getParticipationLimit() != 0) {
            Integer bookedCount = participationRepository.countBookedSeatsByEventId(eventId);
            availability = event.getParticipationLimit() - bookedCount;
        } else {
            Integer bookedCount = participationRepository.countBookedTeamPlacesByEventId(eventId);
            availability = event.getTeamNumber() - bookedCount;
        }
        return availability;
    }

    public void registerForEvent(UUID eventId, TeamEventRegisterRequest teamRegister) {
        Optional<Event> opt = eventRepository.findById(eventId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Event not found");
        }
        Event event = opt.get();


        UserDetails currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("User not logged in");
        }
        if (!isUser(currentUser)) {
            throw new RuntimeException("Only users can register events");
        }


        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username);

        if (teamRegister != null) {
            saveTeamParticipants(teamRegister, event, user);
        } else {
            Participation participation = new Participation();
            participation.setEvent(event);
            participation.setUser(user);
            participationRepository.save(participation);
        }


    }

    private void saveTeamParticipants(TeamEventRegisterRequest teamRegister, Event event, User user) {
        Team team = new Team(event, teamRegister.getTeamName());
        teamRepository.save(team);
        Integer teamSize = event.getTeamSize();

        LinkedHashSet<UUID> teamMemberIds = teamRegister.getTeamMemberIds();
        if (teamMemberIds.size() != teamSize - 1) {
            throw new RuntimeException("not valid team");
        }
        Participation leaderParticipant = new Participation();
        leaderParticipant.setUser(user);
        leaderParticipant.setEvent(event);
        leaderParticipant.setTeam(team);
        leaderParticipant.setLeader(true);
        participationRepository.save(leaderParticipant);
        Iterator<UUID> iterator = teamMemberIds.iterator();
        while (iterator.hasNext()) {
            UUID teamMemberId = iterator.next();
            User member = userRepository.findById(teamMemberId).orElseThrow(() -> new RuntimeException(teamMemberId + " not found"));
            Participation memberParticipation = new Participation();
            memberParticipation.setEvent(event);
            memberParticipation.setTeam(team);
            memberParticipation.setLeader(false);
            memberParticipation.setUser(member);
            participationRepository.save(memberParticipation);

        }
    }

    private boolean isUser(UserDetails currentUser) {
        return currentUser.getAuthorities().size() == 1 && currentUser.getAuthorities().stream().allMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("USER"));
    }

    public List<UserShortDto> getClassmatesShortDto() {
        UserDetails currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("User not logged in");
        }
        String username = currentUser.getUsername();
        User user = userRepository.findByUsername(username);
        return bioRepository.findAllClazzmatesBioByClazzId(user.getBio().getClazz().getId(),user.getId());
    }
}
