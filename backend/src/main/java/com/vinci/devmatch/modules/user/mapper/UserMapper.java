package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.entity.User;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {ContactInfoMapper.class, FreelancerProfileMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    // ✅ Use afterMapping instead of expressions
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userType", ignore = true)
    @Mapping(target = "kycStatus", ignore = true)
    UserResponse toUserResponse(User user);

    @AfterMapping
    default void mapEnums(@MappingTarget UserResponse response, User user) {
        response.setRole(user.getRole() != null ? user.getRole().name() : null);
        response.setUserType(user.getUserType() != null ? user.getUserType().name() : null);
        response.setKycStatus(user.getKycStatus() != null ? user.getKycStatus().name() : null);
    }

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