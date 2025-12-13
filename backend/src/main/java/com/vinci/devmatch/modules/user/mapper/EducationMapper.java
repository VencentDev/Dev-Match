package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.freelancer.Education;
import com.vinci.devmatch.modules.user.entity.EducationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    Education toDto(EducationInfo entity);

    EducationInfo toEntity(Education dto);

    default Set<Education> toDtoSet(Set<EducationInfo> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }

    default Set<EducationInfo> toEntitySet(Set<Education> dtos) {
        if (dtos == null) return null;
        return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
