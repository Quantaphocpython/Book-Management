package com.devteria.postservice.controller;

import org.springframework.web.bind.annotation.*;

import com.devteria.postservice.dto.ApiResponse;
import com.devteria.postservice.dto.PageResponse;
import com.devteria.postservice.dto.request.PostRequest;
import com.devteria.postservice.dto.response.PostResponse;
import com.devteria.postservice.service.PostService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping
    ApiResponse<PostResponse> createPost(@RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request))
                .build();
    }

    @GetMapping("/my-posts")
    ApiResponse<PageResponse<PostResponse>> getMyPost(
            @RequestParam(value = "curentPage", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<PostResponse>>builder()
                .result(postService.getMyPosts(page, size))
                .build();
    }
}
