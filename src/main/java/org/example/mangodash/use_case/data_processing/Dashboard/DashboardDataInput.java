package org.example.mangodash.use_case.data_processing.Dashboard;

import org.example.mangodash.model.FacebookStats;
import org.example.mangodash.model.SocialMediaStats;

public class DashboardDataInput {
    private String username;

    public DashboardDataInput(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
