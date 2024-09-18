package uz.pdp.devunity.mappers.admin;

import org.mapstruct.*;
import uz.pdp.devunity.dto.admin.AdminDto;
import uz.pdp.devunity.entity.Admin;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminMapper {
    Admin toEntity(AdminDto adminDto);

    AdminDto toDto(Admin admin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Admin partialUpdate(AdminDto adminDto, @MappingTarget Admin admin);
}