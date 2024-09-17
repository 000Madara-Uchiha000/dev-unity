package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.dto.event_prize.PrizeResponseDto;
import uz.pdp.devunity.entity.EventPrize;

import java.util.List;
import java.util.UUID;

public interface EventPrizeRepository extends JpaRepository<EventPrize, UUID> {

    @Query(value = "select new uz.pdp.devunity.dto.event_prize.PrizeResponseDto(ep.name,ep.description,ep.photo.photo) from EventPrize ep where ep.event.id=:id")
    List<PrizeResponseDto> findAllByEventIdConvertIntoEventResponseDto(UUID id);
}