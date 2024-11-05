package org.example.mangodash.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataInput;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardInputBoundary;
import org.example.mangodash.view.Dashboard.DashboardViewModel;
import org.example.mangodash.view.ViewManagerModel;

import java.net.MalformedURLException;

public class DashboardController {
    private final DashboardInputBoundary dashboardInputBoundary;

    private final DashboardViewModel dashboardViewModel;

    private final ViewManagerModel viewManagerModel;
    @FXML
    private Button refreshButton;

    @FXML
    private Label statusLabel;

    public DashboardController(DashboardInputBoundary dashboardInputBoundary, DashboardViewModel dashboardViewModel, ViewManagerModel viewManagerModel) {
        this.dashboardInputBoundary = dashboardInputBoundary;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @FXML
    private void initialize() {
        statusLabel.setText("Welcome to the Dashboard!");
    }

    @FXML
    private void refresh() throws MalformedURLException {

        DashboardDataInput dashboardDataInput = new DashboardDataInput(dashboardViewModel.getState().getUsername());
        dashboardInputBoundary.executeRefresh(dashboardDataInput);
        statusLabel.setText("Dashboard refreshed!");
    }
}

