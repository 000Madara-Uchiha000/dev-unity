package uz.pdp.devunity.mappers;

import org.mapstruct.*;
import uz.pdp.devunity.dto.AdminUserBioDto;
import uz.pdp.devunity.entity.Bio;
import uz.pdp.devunity.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminBioMapper {
    Bio toEntity(AdminUserBioDto adminUserBioDto);

    @AfterMapping
    default void linkUser(@MappingTarget Bio bio) {
        User user = bio.getUser();
        if (user != null) {
            user.setBio(bio);
        }
    }

    AdminUserBioDto toDto(Bio bio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Bio partialUpdate(AdminUserBioDto adminUserBioDto, @MappingTarget Bio bio);
}