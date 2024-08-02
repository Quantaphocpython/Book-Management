package com.devteria.profile.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.service.IUserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/users")
public class UserProfileController {
    IUserProfileService userProfileService;

    @GetMapping("/{profileId}")
    UserProfileResponse getProfile(@PathVariable String profileId) {
        return userProfileService.getProfile(profileId);
    }

    @GetMapping
    List<UserProfileResponse> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }
}
