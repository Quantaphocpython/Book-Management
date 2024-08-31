package com.devteria.postservice.mapper;

import org.mapstruct.Mapper;

import com.devteria.postservice.dto.response.PostResponse;
import com.devteria.postservice.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
