package org.example.mangodash.model;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface User {


    String getName();
    String getUserName();

    String getPassword();

    LocalDateTime getCreationTime();

    void setPassword(String password);

    void setName(String name);

    void setBio(String bio);

    String getBio();

    void setApiKeys(String socialMedia, String apiKey);

    HashMap<String,String> getApiKeys();

    void setApiStatus(HashMap<String, Boolean> apiStatus);

    HashMap<String, Boolean> getApiStatus();

}
