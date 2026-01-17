package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileResponse;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileUpdateRequest;
import com.vinci.devmatch.modules.user.entity.FreelancerProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {EducationMapper.class})
public interface FreelancerProfileMapper {

    @Mapping(target = "education", source = "educationInfo")
    FreelancerProfileResponse toDto(FreelancerProfile profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "educationInfo", source = "education")
    FreelancerProfile toEntity(FreelancerProfileFinishRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "educationInfo", source = "education")
    void updateEntityFromDto(
            FreelancerProfileUpdateRequest dto,
            @MappingTarget FreelancerProfile entity
    );
}