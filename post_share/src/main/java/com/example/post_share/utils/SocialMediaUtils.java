package com.example.post_share.utils;

import com.example.post_share.model.Post;

public class SocialMediaUtils {

    private String getFacebookShareUrl(Post post) {
        String appId = "YOUR_APP_ID"; // Replace with your Facebook app ID
        String redirectUri = "https://YOUR_REDIRECT_URI"; // Replace with your redirect URI
        String postUrl = "https://YOUR_POST_URL?id=" + post.getId(); // Replace with your post URL
        return String.format("https://www.facebook.com/dialog/share?app_id=%s&display=popup&href=%s&redirect_uri=%s",
                appId, postUrl, redirectUri);
    }

}
