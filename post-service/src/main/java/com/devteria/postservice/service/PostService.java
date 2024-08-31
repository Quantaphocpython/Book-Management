package com.devteria.postservice.service;

import java.time.Instant;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devteria.postservice.dto.request.PostRequest;
import com.devteria.postservice.dto.response.PostResponse;
import com.devteria.postservice.entity.Post;
import com.devteria.postservice.mapper.PostMapper;
import com.devteria.postservice.repository.PostRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;

    public PostResponse createPost(PostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Post post = Post.builder()
                .content(request.getContent())
                .userId(authentication.getName())
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .build();

        post = postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    public List<PostResponse> getMyPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return postRepository.findAllByUserId(userId).stream()
                .map(postMapper::toPostResponse)
                .toList();
    }
}
