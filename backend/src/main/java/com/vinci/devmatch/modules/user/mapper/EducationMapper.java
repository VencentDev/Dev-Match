package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.freelancer.Education;
import com.vinci.devmatch.modules.user.entity.EducationInfo;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    Education toDto(EducationInfo entity);

    EducationInfo toEntity(Education dto);

    Set<Education> toDtoSet(Set<EducationInfo> entities);

    Set<EducationInfo> toEntitySet(Set<Education> dtos);
}