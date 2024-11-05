package org.example.mangodash.use_case.api_data_access;

import org.example.mangodash.model.SocialMediaStats;
import org.json.JSONArray;

import java.net.MalformedURLException;
import java.util.HashMap;

public interface APIDataAccessInterface {
    void fetchData(SocialMediaStats socialMediaStats) throws MalformedURLException;
    void setAPI(String apiUrl);
    boolean isApiError();
}
