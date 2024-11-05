package org.example.mangodash.view.Extension;

import java.util.HashMap;
import java.util.Map;

public class ExtensionState {
    // HashMap to store apiType as key and ApiDetail (apiKey, apiStatus) as value
    private Map<String, ExtensionApiDetail> apiDetailsMap;
    private String error;

    private String username;

    public ExtensionState() {
        this.apiDetailsMap = new HashMap<>();
        this.error = "";
    }

    public void setApiDetailsMap(Map<String, ExtensionApiDetail> apiDetailsMap) {
        this.apiDetailsMap = apiDetailsMap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Method to add or update an ApiDetail
    public void addApiDetail(String apiType, String apiKey, Boolean apiStatus) {
        this.apiDetailsMap.put(apiType, new ExtensionApiDetail(apiKey, apiStatus));
    }

    // Method to get ApiDetail by apiType
    public ExtensionApiDetail getApiDetail(String apiType) {
        return this.apiDetailsMap.get(apiType);
    }

    // Method to retrieve the entire map of api details
    public Map<String, ExtensionApiDetail> getApiDetailsMap() {
        return apiDetailsMap;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // Inner class to represent API detail with only apiKey and apiStatus
    public static class ExtensionApiDetail {
        private String apiKey;
        private Boolean apiStatus;

        public ExtensionApiDetail(String apiKey, Boolean apiStatus) {
            this.apiKey = apiKey;
            this.apiStatus = apiStatus;
        }

        public String getApiKey() {
            return apiKey;
        }

        public Boolean getApiStatus() {
            return apiStatus;
        }

        @Override
        public String toString() {
            return "{" +
                    "apiKey='" + apiKey + '\'' +
                    ", apiStatus=" + apiStatus +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ExtensionState{" +
                "apiDetailsMap=" + apiDetailsMap +
                '}';
    }
}

