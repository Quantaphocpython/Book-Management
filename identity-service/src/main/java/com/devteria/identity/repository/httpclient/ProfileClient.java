package com.devteria.identity.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.devteria.identity.configuration.AuthenticationRequestInterceptor;
import com.devteria.identity.dto.request.ProfileCreationRequest;
import com.devteria.identity.dto.response.ApiResponse;
import com.devteria.identity.dto.response.UserProfileResponse;

@FeignClient(
        name = "profile-service",
        url = "${app.service.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
// them thuoc tinh config de them header Authorization cho cac request, co the co nhieu class interceptor
public interface ProfileClient {

    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<UserProfileResponse> createProfile(
            @RequestBody
                    ProfileCreationRequest
                            request); // truyền header chứa token cho profile-service nhằm thuực hiện authorize
}
