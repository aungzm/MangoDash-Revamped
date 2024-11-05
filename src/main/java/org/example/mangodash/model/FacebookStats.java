package org.example.mangodash.model;

import java.util.List;

public class FacebookStats implements SocialMediaStats{
    private String userId;
    private String name;
    private String email;
    private int friendCount;
    private List<Post> posts;

    public FacebookStats() {
    }
    @Override
    public void setFriendCount(int friend) {
        this.friendCount = friend;
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
    }
    @Override
    public void setUserId(String userId){
        this.userId = userId;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public void setEmail(String email){
        this.email = email;
    }

    // Inner class to represent a Post
    public static class Post {
        private String id;
        private String createdTime;
        private String message;
        private int likesCount;
        private int commentsCount;

        // Constructor for Post
        public Post(String id, String createdTime, String message, int likesCount, int commentsCount) {
            this.id = id;
            this.createdTime = createdTime;
            this.message = message;
            this.likesCount = likesCount;
            this.commentsCount = commentsCount;
        }

        // Getters for Post class
        public String getId() {
            return id;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public String getMessage() {
            return message;
        }

        public int getLikesCount() {
            return likesCount;
        }

        public int getCommentsCount() {
            return commentsCount;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "id='" + id + '\'' +
                    ", createdTime='" + createdTime + '\'' +
                    ", message='" + message + '\'' +
                    ", likesCount=" + likesCount +
                    ", commentsCount=" + commentsCount +
                    '}';
        }
    }



    // Getters for FacebookStats class
    @Override
    public String getUserId() {
        return userId;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getFriendCount() {
        return friendCount;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "FacebookStats{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", friendCount=" + friendCount +
                ", posts=" + posts +
                '}';
    }
}
