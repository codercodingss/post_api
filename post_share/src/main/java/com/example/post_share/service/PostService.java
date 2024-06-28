package com.example.post_share.service;

import com.example.post_share.model.Post;
import com.example.post_share.model.PostRequest;
import com.example.post_share.model.PostResponse;
import com.example.post_share.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;

    public boolean savedPost(PostRequest post){
        boolean truthy = false;
        if (post != null){
            Post post1 = new Post();
            post1.setPost_title(post.getPost_title());
            post1.setPost_date(post.getPost_date());
            post1.setPost_content(post.getPost_content());
            post1.setCommentEnabled(post.isCommentEnabled());
            post1.setPublic(post.isPublic());

            Post saved = postRepository.save(post1);

            if (saved != null){
                truthy = true;
            }
        }
        return truthy;
    }

    public PostResponse getPostDetails(String title){
        if (title != null){
            Post post = postRepository.findByPost_title(title);
            PostResponse post2 = new PostResponse();
            post2.setId(post.getId());
            post2.setPost_title(post.getPost_title());
            post2.setPost_date(post.getPost_date());
            post2.setCommentEnabled(post.isCommentEnabled());
            post2.setPost_content(post.getPost_content());
            post2.setPublic(post.isPublic());

            return post2;
        }
        else
        {
            PostResponse post2i = new PostResponse();
            post2i.setPost_title("This is a sample title");
            post2i.setPost_date(new Date());
            post2i.setCommentEnabled(false);
            post2i.setPost_content("This is my sample content");
            post2i.setPublic(false);

            return post2i;
        }
    }


    public String updatePost(Long postid, PostRequest post){

        Post existingPost = postRepository.findById(postid).orElseThrow(() -> new RuntimeException("Not Found"));
        if (existingPost != null) {
            existingPost.setPost_title(post.getPost_title());
            existingPost.setPost_date(post.getPost_date());
            existingPost.setPost_content(post.getPost_content());
            existingPost.setCommentEnabled(post.isCommentEnabled());
            existingPost.setPublic(post.isPublic());

            postRepository.save(existingPost);

            return "Data Updated";
        }
        else {
            return "Email not found";
        }

    }

    public boolean deletePost(Long postid){
        boolean deleted = false;
        Post existingPost = postRepository.findById(postid).orElseThrow(() -> new RuntimeException("Not found"));
        if (existingPost != null) {
            postRepository.deleteById(postid);
            deleted = true;
        }
        return deleted;
    }

    public void shareOnSocialMedia(Post post, String platform, String message) {
        // Implement sharing logic using the specified social media platform's API
    }

}
