package com.devteria.profile.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;

public interface IUserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);

    UserProfileResponse getProfile(String id);

    @PreAuthorize("hasRole('ADMIN')")
    List<UserProfileResponse> getAllProfiles();
}
