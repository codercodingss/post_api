package com.example.post_share.repository;

import com.example.post_share.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findByPost_title(String postTitle);
}
