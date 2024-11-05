package org.example.mangodash.controller;

import javafx.fxml.FXML;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionDataInput;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionDataOutput;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionInputBoundary;
import org.example.mangodash.use_case.data_processing.Login.LoginDataInput;
import org.example.mangodash.view.Extension.ExtensionState;
import org.example.mangodash.view.Extension.ExtensionViewModel;
import org.example.mangodash.view.ViewManagerModel;

import java.net.MalformedURLException;

public class ExtensionController {
    private final ExtensionViewModel extensionViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ExtensionInputBoundary extensionInputBoundary;

    public ExtensionController(ExtensionViewModel extensionViewModel, ViewManagerModel viewManagerModel, ExtensionInputBoundary extensionInputBoundary) {
        this.extensionViewModel = extensionViewModel;
        this.viewManagerModel = viewManagerModel;
        this.extensionInputBoundary = extensionInputBoundary;
    }

    @FXML
    private void handleSaveFacebookChanges() throws MalformedURLException {
        ExtensionState currentState = extensionViewModel.getState();
        String username = currentState.getUsername();
        ExtensionState.ExtensionApiDetail facebookDetails = currentState.getApiDetailsMap().get("facebook");
        ExtensionDataInput extensionDataInput = new ExtensionDataInput(username, "facebook", facebookDetails.getApiKey(), facebookDetails.getApiStatus());
        extensionInputBoundary.executeSaveAPIData(extensionDataInput);
    }

    @FXML
    private void handleSaveInstagramChanges() throws MalformedURLException {
        ExtensionState currentState = extensionViewModel.getState();
        String username = currentState.getUsername();
        ExtensionState.ExtensionApiDetail instagramDetails = currentState.getApiDetailsMap().get("instagram");
        ExtensionDataInput extensionDataInput = new ExtensionDataInput(username, "instagram", instagramDetails.getApiKey(), instagramDetails.getApiStatus());
        extensionInputBoundary.executeSaveAPIData(extensionDataInput);
    }


    @FXML
    private void handleSaveTelegramChanges() throws MalformedURLException {
        ExtensionState currentState = extensionViewModel.getState();
        String username = currentState.getUsername();
        ExtensionState.ExtensionApiDetail telegramDetails = currentState.getApiDetailsMap().get("telegram");
        ExtensionDataInput extensionDataInput = new ExtensionDataInput(username, "telegram", telegramDetails.getApiKey(), telegramDetails.getApiStatus());
        extensionInputBoundary.executeSaveAPIData(extensionDataInput);
    }
}
