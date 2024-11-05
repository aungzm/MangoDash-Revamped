package org.example.mangodash.view.Extension;

import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionDataOutput;

public interface ExtensionOutputBoundary {
    void prepareSuccessView(DashboardDataOutput dashboardDataOutput, ExtensionDataOutput extensionDataOutput);

    void prepareFailView(String error);
}
