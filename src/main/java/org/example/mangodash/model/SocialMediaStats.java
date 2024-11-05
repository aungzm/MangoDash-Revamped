package org.example.mangodash.model;

public interface SocialMediaStats {
    void setEmail(String email);
    void setName(String name);
    void setUserId(String id);

    void setFriendCount(int friends);

    String getEmail();

    String getUserId();

    int getFriendCount();

    String getName();
}
