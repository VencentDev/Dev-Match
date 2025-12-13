package com.vinci.devmatch.modules.user.mapper;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.entity.FreelancerProfile;
import com.vinci.devmatch.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ContactInfoMapper.class, FreelancerProfileMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // disambiguate `id` by specifying the source parameter `user`
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "freelancerProfile", source = "freelancerProfile")
    UserResponse toUserResponse(User user, FreelancerProfile freelancerProfile);

    void updateUserFromUserProfileUpdateRequest(@MappingTarget User user, UserProfileUpdateRequest dto);

    // Map finish request DTO to User entity
    User toUserEntity(UserProfileFinishRequest dto);

    // Optionally, update User from finish request DTO as well
    void updateUserFromUserProfileFinishRequest(@MappingTarget User user, UserProfileFinishRequest dto);
}
