package org.example.mangodash.view.Dashboard;

import org.example.mangodash.model.SocialMediaStats;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;
import org.example.mangodash.view.ViewManagerModel;

public class DashboardPresenter implements DashboardOutputBoundary {
    private final DashboardViewModel dashboardViewModel;

    private final ViewManagerModel viewManagerModel;

    // Constructor to initialize DashboardState
    public DashboardPresenter(DashboardViewModel dashboardViewModel, ViewManagerModel viewManagerModel) {
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DashboardDataOutput dashboardDataOutput) {
        // Populate the DashboardState from the DashboardDataOutput

        // Set social media stats if they are available
        if (dashboardDataOutput.getFacebookStats() != null) {
            dashboardViewModel.getState().setFacebookStats(dashboardDataOutput.getFacebookStats());
        }
        if (dashboardDataOutput.getInstagramStats() != null) {
            dashboardViewModel.getState().setInstagramStats(dashboardDataOutput.getInstagramStats());
        }
        if (dashboardDataOutput.getTelegramStats() != null) {
            dashboardViewModel.getState().setTelegramStats(dashboardDataOutput.getTelegramStats());
        }

        // Set errors if any
        dashboardViewModel.getState().clearErrors();
        if (dashboardDataOutput.getErrors() != null && !dashboardDataOutput.getErrors().isEmpty()) {
            dashboardViewModel.getState().setErrors(dashboardDataOutput.getErrors());
        }

        // Display or process updated state (For demonstration, printing to console)
        System.out.println("Dashboard state updated successfully:");
        System.out.println(dashboardViewModel.getState());
        dashboardViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
