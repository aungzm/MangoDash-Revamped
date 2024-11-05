package org.example.mangodash.model;

import java.time.LocalDateTime;
import java.util.HashMap;

public class CommonUser implements  User{

    private String bio;
    private String name;

    private final String username;
    private String password;
    private final LocalDateTime creationTime;
    private HashMap<String, String> apiKeys;

    private HashMap<String, Boolean> apiStatus;


    public CommonUser(String name, String username, String password, String bio, LocalDateTime creationTime) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.creationTime = creationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String getBio() {
        return bio;
    }

    @Override
    public void setApiKeys(String socialMedia, String apiKey) {
        if (apiKeys == null) {
            apiKeys = new HashMap<>();
        }
        apiKeys.put(socialMedia, apiKey);
    }

    @Override
    public HashMap<String,String> getApiKeys() {
        return apiKeys;
    }

    @Override
    public void setApiStatus(HashMap<String, Boolean> apiStatus) {
        this.apiStatus = apiStatus;
    }

    @Override
    public HashMap<String, Boolean> getApiStatus() {
        return apiStatus;
    }
}