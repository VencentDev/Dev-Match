package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileResponse;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileUpdateRequest;
import com.vinci.devmatch.modules.user.entity.FreelancerProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EducationMapper.class})
public interface FreelancerProfileMapper {

    FreelancerProfileMapper INSTANCE = Mappers.getMapper(FreelancerProfileMapper.class);

    FreelancerProfileResponse toDto(FreelancerProfile profile);

    @Mapping(target = "user", ignore = true)
    FreelancerProfile toEntity(FreelancerProfileFinishRequest dto);

    void updateEntityFromDto(FreelancerProfileUpdateRequest dto, @MappingTarget FreelancerProfile entity);

    // Convert between finish and update DTOs
    FreelancerProfileUpdateRequest toUpdateDto(FreelancerProfileFinishRequest finishDto);

    FreelancerProfileFinishRequest toFinishDto(FreelancerProfileUpdateRequest updateDto);
}

