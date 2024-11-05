package org.example.mangodash.view.Dashboard;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;

public interface DashboardOutputBoundary {
    void prepareSuccessView(DashboardDataOutput dashboardDataOutput);
}
