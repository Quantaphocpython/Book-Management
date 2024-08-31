package com.devteria.postservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devteria.postservice.entity.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByUserId(String userId);
}
