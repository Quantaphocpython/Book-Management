package com.devteria.profile.service;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IUserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);
    UserProfileResponse getProfile(String id);

    @PreAuthorize("hasRole('ADMIN')")
    List<UserProfileResponse> getAllProfiles();
}
