package org.example.mangodash.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.mangodash.use_case.data_processing.Login.LoginDataInput;
import org.example.mangodash.view.Frontpage.FrontPageViewModel;
import org.example.mangodash.view.ViewManagerModel;

import java.net.MalformedURLException;

public class FrontpageController {
    private final ViewManagerModel viewManagerModel;

    private final FrontPageViewModel frontPageViewModel;



    @FXML
    private Button logIn;

    @FXML
    private Button signup;

    public FrontpageController(ViewManagerModel viewManagerModel, FrontPageViewModel frontPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.frontPageViewModel = frontPageViewModel;
    }

    @FXML
    private void handleLogin() throws MalformedURLException {
        viewManagerModel.setActiveView("login");
    }

    @FXML
    private void handleBack() {
        viewManagerModel.setActiveView("signup");
        viewManagerModel.firePropertyChanged();
    }
}
