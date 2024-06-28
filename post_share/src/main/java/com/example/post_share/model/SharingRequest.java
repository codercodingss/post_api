package com.example.post_share.model;

import lombok.Data;

@Data
public class SharingRequest {
    private String platform;
    private String message;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
