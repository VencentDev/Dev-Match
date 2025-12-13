package com.vinci.devmatch.modules.user.service;

import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.entity.FreelancerProfile;
import com.vinci.devmatch.modules.user.entity.User;
import com.vinci.devmatch.modules.user.mapper.FreelancerProfileMapper;
import com.vinci.devmatch.modules.user.mapper.UserMapper;
import com.vinci.devmatch.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        FreelancerProfile freelancerProfile = user.getFreelancerProfile();
        return userMapper.toUserResponse(user, freelancerProfile);
    }

    @Override
    public UserResponse updateUserProfile(Long userId, UserProfileUpdateRequest updateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update user basic info from update DTO
        userMapper.updateUserFromUserProfileUpdateRequest(user, updateRequest);

        // Handle freelancer profile update or creation
        FreelancerProfileUpdateRequest freelancerUpdateDto = updateRequest.getFreelancerProfile();
        if (freelancerUpdateDto != null) {
            FreelancerProfile profile = user.getFreelancerProfile();
            if (profile == null) {
                // Create new FreelancerProfile entity from finish DTO via mapper conversion
                FreelancerProfileFinishRequest finishDto = FreelancerProfileMapper.INSTANCE.toFinishDto(freelancerUpdateDto);
                profile = FreelancerProfileMapper.INSTANCE.toEntity(finishDto);
                profile.setUser(user);
                user.setFreelancerProfile(profile);
            } else {
                FreelancerProfileMapper.INSTANCE.updateEntityFromDto(freelancerUpdateDto, profile);
            }
        }

        userRepository.save(user);
        return userMapper.toUserResponse(user, user.getFreelancerProfile());
    }

    @Override
    public UserResponse finishUserProfile(Long userId, UserProfileFinishRequest finishRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update user entity from finish DTO
        userMapper.updateUserFromUserProfileFinishRequest(user, finishRequest);

        // Handle freelancer profile finish or update
        FreelancerProfileFinishRequest freelancerFinishDto = finishRequest.getFreelancerProfile();
        if (freelancerFinishDto != null) {
            FreelancerProfile profile = user.getFreelancerProfile();
            if (profile == null) {
                profile = FreelancerProfileMapper.INSTANCE.toEntity(freelancerFinishDto);
                profile.setUser(user);
                user.setFreelancerProfile(profile);
            } else {
                FreelancerProfileUpdateRequest updateDto = FreelancerProfileMapper.INSTANCE.toUpdateDto(freelancerFinishDto);
                FreelancerProfileMapper.INSTANCE.updateEntityFromDto(updateDto, profile);
            }
        }

        user.setProfileCompleted(true);

        userRepository.save(user);
        return userMapper.toUserResponse(user, user.getFreelancerProfile());
    }
}
