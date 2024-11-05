package org.example.mangodash.use_case.data_processing.Dashboard;

import org.example.mangodash.model.SocialMediaStats;

import java.net.MalformedURLException;

public interface DashboardInputBoundary {
    void executeRefresh(DashboardDataInput dashboardDataInput) throws MalformedURLException;
}
