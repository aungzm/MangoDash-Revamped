package org.example.mangodash.use_case.data_processing.Extension;

public class ExtensionDataInput {

    private String username;
    private String mediaName;

    private String apiKey;

    private Boolean status;


    public ExtensionDataInput(String username, String mediaName, String apiKey, Boolean status){
        this.mediaName = mediaName;
        this.apiKey = apiKey;
        this.status = status;
        this.username = username;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }
}
