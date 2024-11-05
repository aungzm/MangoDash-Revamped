package org.example.mangodash.view.Login;

import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionDataOutput;
import org.example.mangodash.use_case.data_processing.Settings.SettingsDataOutput;

import java.util.List;

public interface LoginOutputBoundary {
    void prepareSuccessView(SettingsDataOutput settingsDataOutput, ExtensionDataOutput extensionDataOutput, DashboardDataOutput dashboardDataOutput, List<String> Errors);

    void prepareFailView(String error);
}
