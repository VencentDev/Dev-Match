package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {ContactInfoMapper.class, FreelancerProfileMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    @Mapping(target = "role", expression = "java(user.getRole() != null ? user.getRole().name() : null)")
    @Mapping(target = "userType", expression = "java(user.getUserType() != null ? user.getUserType().name() : null)")
    @Mapping(target = "kycStatus", expression = "java(user.getKycStatus() != null ? user.getKycStatus().name() : null)")
    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "auth0Id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "profileCompleted", ignore = true)
    @Mapping(target = "userType", ignore = true)
    @Mapping(target = "kycStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "freelancerProfile", ignore = true)
    void updateUserFromUserProfileUpdateRequest(
            @MappingTarget User user,
            UserProfileUpdateRequest dto
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "auth0Id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "profileCompleted", ignore = true)
    @Mapping(target = "userType", ignore = true)
    @Mapping(target = "kycStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "freelancerProfile", ignore = true)
    void updateUserFromUserProfileFinishRequest(
            @MappingTarget User user,
            UserProfileFinishRequest dto
    );
}