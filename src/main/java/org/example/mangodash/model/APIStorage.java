package org.example.mangodash.model;

public class APIStorage {
    private String username;
    private String apiKey;
    private boolean apiStatus;
    private String apiType;

    public APIStorage(String username, String apiKey, boolean apiStatus, String apiType) {
        this.username = username;
        this.apiKey = apiKey;
        this.apiStatus = apiStatus;
        this.apiType = apiType;
    }

    public String getUsername() {
        return username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public boolean getApiStatus() {
        return apiStatus;
    }

    public String getApiType() {
        return apiType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiStatus(boolean apiStatus) {
        this.apiStatus = apiStatus;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }
}