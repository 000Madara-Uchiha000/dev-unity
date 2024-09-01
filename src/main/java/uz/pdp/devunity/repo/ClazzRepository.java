package uz.pdp.devunity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.dto.clazz.ClazzStatProjectionDto;
import uz.pdp.devunity.entity.Clazz;

import java.util.List;
import java.util.UUID;

public interface ClazzRepository extends JpaRepository<Clazz, UUID> {
    Clazz findByName(String name);
    @Query(nativeQuery = true,value = "select name from clazz order by name")
    List<String> findAllNames();


//    @Query(nativeQuery = true,value = "select c.name as clazzName,COUNT(b.*) as studentCount from clazz c left join bio b on b.clazz_id=c.id group by c.id order by c.name")
    @Query(value = "select new uz.pdp.devunity.dto.clazz.ClazzStatProjectionDto(c.name,count(b)) from  Clazz c left join Bio b on b.clazz.id=c.id group by c.id order by c.name")
    List<ClazzStatProjectionDto> generateStatisticByClazz();
}