package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.dto.user.UserShortDto;
import uz.pdp.devunity.entity.Bio;

import java.util.List;
import java.util.UUID;

public interface BioRepository extends JpaRepository<Bio, UUID> {
    @Query(value = "select new uz.pdp.devunity.dto.user.UserShortDto(b.user.id,b.user.username,b.firstname,b.lastname,b.clazz.name) from Bio b where b.user.id!=:userId and b.clazz.id=:clazzId")
    List<UserShortDto> findAllClazzmatesBioByClazzId(UUID clazzId, UUID userId);
}