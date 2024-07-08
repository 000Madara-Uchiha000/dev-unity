package uz.pdp.devunity.repo;

import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.devunity.dto.AdminResponseProjectionDto;
import uz.pdp.devunity.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);
    
    @Query(nativeQuery = true,value = "select count(distinct u.email) from users u join admins a on u.id=a.user_id ;")
    Integer countDevUnityMembers();

    @Query(nativeQuery = true,value = "select distinct" +
            " u.id as userId," +
            " u.email as email,b.firstname as firstName,b.lastname as lastName from users u" +
            "            join bio b on u.bio_id = b.id\n" +
            "            join users_roles us on us.user_id=u.id\n" +
            "            where us.roles_id=(select id from roles where role_enum='ROLE_ADMIN')")
    List<Object[]> findAllAdminsData();



}