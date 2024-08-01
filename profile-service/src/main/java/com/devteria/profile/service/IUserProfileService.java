package com.devteria.profile.service;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;

import java.util.List;

public interface IUserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);
    UserProfileResponse getProfile(String id);

    List<UserProfileResponse> getAllProfiles();
}
