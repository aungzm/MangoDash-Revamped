package org.example.mangodash.view.Extension;

import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionDataOutput;
import org.example.mangodash.view.ViewManagerModel;

public class ExtensionPresenter implements ExtensionOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    private final ExtensionViewModel extensionViewModel;

    // Constructor to initialize the presenter with an ExtensionState instance
    public ExtensionPresenter(ViewManagerModel viewManagerModel, ExtensionViewModel extensionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.extensionViewModel = extensionViewModel;
    }

    @Override
    public void prepareSuccessView(DashboardDataOutput dashboardDataOutput, ExtensionDataOutput extensionDataOutput) {
        // Populate ExtensionState from ExtensionDataOutput
        extensionViewModel.getState().getApiDetailsMap().clear(); // Clear existing data to avoid duplicates

        for (ExtensionDataOutput.ApiDetail apiDetail : extensionDataOutput.getApiDetails()) {
            extensionViewModel.getState().addApiDetail(apiDetail.getApiType(), apiDetail.getApiKey(), apiDetail.getApiStatus());
        }

        // Display the updated ExtensionState for verification
        System.out.println("Extension state updated successfully:");
        System.out.println(extensionViewModel.getState());
        extensionViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(viewManagerModel.getActiveView());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // Handle error display (could be logging, setting an error message in the view, etc.)
        extensionViewModel.getState().setError(error);
        System.out.println("Error: " + error);
        extensionViewModel.firePropertyChanged();
    }
}