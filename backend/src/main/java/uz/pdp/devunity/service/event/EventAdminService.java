package uz.pdp.devunity.service.event;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.devunity.dto.event.EventCreationDto;
import uz.pdp.devunity.dto.event.EventFullDto;
import uz.pdp.devunity.dto.event.EventShortDto;
import uz.pdp.devunity.dto.event_prize.PrizeRequestDto;
import uz.pdp.devunity.dto.event_prize.PrizeResponseDto;
import uz.pdp.devunity.entity.Event;
import uz.pdp.devunity.entity.EventPrize;
import uz.pdp.devunity.entity.Photo;
import uz.pdp.devunity.repo.EventPrizeRepository;
import uz.pdp.devunity.repo.EventRepository;
import uz.pdp.devunity.repo.ParticipationRepository;
import uz.pdp.devunity.response.EventCreationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventAdminService {
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;
    private final EventPrizeRepository eventPrizeRepository;

    @SneakyThrows
    public EventCreationResponse  createEvent(EventCreationDto eventDto) {
        Event newEvent = eventDtoToEntity(eventDto);
        Event savedEvent = eventRepository.save(newEvent);
        List<EventPrize> eventPrizes = saveAllEventPrizes(eventDto, savedEvent);

        return new EventCreationResponse(
                savedEvent,
                eventPrizes
        );
    }

    @SneakyThrows
    private List<EventPrize> saveAllEventPrizes(EventCreationDto eventDto, Event savedEvent) {
        List<PrizeRequestDto> prizeRequestDtos = eventDto.getPrizeRequestDtos();
        List<EventPrize> eventPrizes = new ArrayList<>();
        for (PrizeRequestDto prizeRequestDto : prizeRequestDtos) {
            EventPrize eventPrize = new EventPrize();
            eventPrize.setName(prizeRequestDto.getName());
            eventPrize.setDescription(prizeRequestDto.getDescription());
            eventPrize.setEvent(savedEvent);
            byte[] bytes = prizeRequestDto.getPhoto().getBytes();
            eventPrize.setPhoto(new Photo(bytes));
            eventPrizes.add(eventPrize);
        }
        if (!eventPrizes.isEmpty()) {

            return eventPrizeRepository.saveAll(eventPrizes);
        }
        return null;
    }

    @SneakyThrows
    private Event eventDtoToEntity(EventCreationDto eventDto) {
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setBody(eventDto.getBody());
        event.setParticipationLimit(eventDto.getParticipationLimit());
        event.setStartTime(eventDto.getStartTime());
        event.setLastRegisterTime(eventDto.getLastRegisterTime());
        event.setPlace(eventDto.getPlace());
        MultipartFile eventPhoto = eventDto.getEventPhoto();
        byte[] bytes = eventPhoto.getBytes();
        event.setPhoto(new Photo(bytes));
        event.setTeamNumber(eventDto.getTeamNumber());
        event.setTeamSize(eventDto.getTeamSize());
        return event;
    }


}
