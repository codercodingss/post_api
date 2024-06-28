package com.example.post_share.controller;

import com.example.post_share.model.Post;
import com.example.post_share.model.PostRequest;
import com.example.post_share.model.PostResponse;
import com.example.post_share.model.SharingRequest;
import com.example.post_share.repository.PostRepository;
import com.example.post_share.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/post/")
@CrossOrigin("*")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @PostMapping("/createpost")
    public boolean savePost(@RequestBody PostRequest postReq){
        return postService.savedPost(postReq);
    }

    @GetMapping("/getpost/{mytitle}")
    public PostResponse getOnePost(@PathVariable String mytitle){
        return postService.getPostDetails(mytitle);
    }

    @PutMapping("/updatepost/{did}")
    public String updatePost(@PathVariable Long did, @RequestBody PostRequest myupdate){
        return postService.updatePost(did, myupdate);
    }

    @DeleteMapping("/deletepost/{did}")
    public boolean deletePost(@PathVariable Long did){
        return postService.deletePost(did);
    }

    @PostMapping("/posts/{id}/share")
    public ResponseEntity<Post> sharePost(@PathVariable Long id, @RequestBody SharingRequest request) {
        Post post = postRepository.findById(id).orElseThrow();
        postService.shareOnSocialMedia(post, request.getPlatform(), request.getMessage());
        post.setShared(true);
        postRepository.save(post);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/{id}/share/facebook")
    public ResponseEntity<Void> shareOnFacebook(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        String shareUrl = getFacebookShareUrl(post);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(shareUrl)).build();
    }

    private String getFacebookShareUrl(Post post) {
        String appId = "480268924413234"; // Replace with your Facebook app ID
        String redirectUri = "https://localhost:8080/post/facebook-redirect"; // Replace with your redirect URI
        String postUrl = "https://localhost:8080/post/getpost/" + post.getPost_title(); // Replace with your post URL
        return String.format("https://www.facebook.com/dialog/share?app_id=%s&display=popup&href=%s&redirect_uri=%s",
                appId, postUrl, redirectUri);
    }

    @GetMapping("/facebook-redirect")
    public String handleFacebookRedirect() {
        // Handle the redirect request from Facebook
        return "Redirect received from Facebook";
    }
}
