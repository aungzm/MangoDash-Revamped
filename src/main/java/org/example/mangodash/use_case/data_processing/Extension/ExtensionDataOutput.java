package org.example.mangodash.use_case.data_processing.Extension;

import java.util.ArrayList;
import java.util.List;

public class ExtensionDataOutput {
    private List<ApiDetail> apiDetails;

    // Constructor initializes apiDetails as a new list
    public ExtensionDataOutput() {
        this.apiDetails = new ArrayList<>();
    }

    // Constructor with initial ApiDetail
    public ExtensionDataOutput(ApiDetail apiDetail) {
        this.apiDetails = new ArrayList<>();
        this.apiDetails.add(apiDetail);
    }

    public void addApiDetail(ApiDetail apiDetail) {
        this.apiDetails.add(apiDetail);
    }

    public List<ApiDetail> getApiDetails() {
        return apiDetails;
    }

    // Inner class to represent API detail
    public static class ApiDetail {
        private String apiKey;
        private Boolean apiStatus;
        private String apiType;

        public ApiDetail(String apiKey, Boolean apiStatus, String apiType) {
            this.apiKey = apiKey;
            this.apiStatus = apiStatus;
            this.apiType = apiType;
        }

        public String getApiKey() {
            return apiKey;
        }

        public Boolean getApiStatus() {
            return apiStatus;
        }

        public String getApiType() {
            return apiType;
        }

        @Override
        public String toString() {
            return "ApiDetail{" +
                    "apiKey='" + apiKey + '\'' +
                    ", apiStatus=" + apiStatus +
                    ", apiType='" + apiType + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ExtensionDataOutput{" +
                "apiDetails=" + apiDetails +
                '}';
    }
}
