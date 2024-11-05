package org.example.mangodash.factory;

import org.example.mangodash.controller.LoginController;
import org.example.mangodash.use_case.api_data_access.APIDataAccessInterface;
import org.example.mangodash.use_case.data_processing.Login.LoginInteractor;
import org.example.mangodash.use_case.data_processing.Login.LoginInputBoundary;
import org.example.mangodash.view.Dashboard.DashboardViewModel;
import org.example.mangodash.view.Extension.ExtensionViewModel;
import org.example.mangodash.view.Login.LoginPresenter;
import org.example.mangodash.view.Login.LoginViewModel;
import org.example.mangodash.view.Settings.SettingsViewModel;
import org.example.mangodash.view.ViewManagerModel;
import org.example.mangodash.use_case.db_acccess.APIStorageDataAccessInterface;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;
import org.example.mangodash.model.SocialMediaStats;

public class LoginControllerFactory {

    private final UserDataAccessInterface userDAO;
    private final APIStorageDataAccessInterface apiStorageDataAccess;
    private final APIDataAccessInterface facebookAPIDAO;
    private final APIDataAccessInterface instagramAPIDAO;
    private final APIDataAccessInterface telegramAPIDAO;

    private final SocialMediaStats facebookStats;
    private final SocialMediaStats instagramStats;
    private final SocialMediaStats telegramStats;

    private final DashboardViewModel dashboardViewModel;
    private final ExtensionViewModel extensionViewModel;
    private final SettingsViewModel settingsViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginControllerFactory(UserDataAccessInterface userDAO,
                                  APIStorageDataAccessInterface apiStorageDataAccess,
                                  APIDataAccessInterface facebookAPIDAO,
                                  APIDataAccessInterface instagramAPIDAO,
                                  APIDataAccessInterface telegramAPIDAO,
                                  SocialMediaStats facebookStats,
                                  SocialMediaStats instagramStats,
                                  SocialMediaStats telegramStats,
                                  DashboardViewModel dashboardViewModel,
                                  ExtensionViewModel extensionViewModel,
                                  SettingsViewModel settingsViewModel,
                                  LoginViewModel loginViewModel,
                                  ViewManagerModel viewManagerModel) {
        this.userDAO = userDAO;
        this.apiStorageDataAccess = apiStorageDataAccess;
        this.facebookAPIDAO = facebookAPIDAO;
        this.instagramAPIDAO = instagramAPIDAO;
        this.telegramAPIDAO = telegramAPIDAO;
        this.facebookStats = facebookStats;
        this.instagramStats = instagramStats;
        this.telegramStats = telegramStats;
        this.dashboardViewModel = dashboardViewModel;
        this.extensionViewModel = extensionViewModel;
        this.settingsViewModel = settingsViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public LoginController createLoginController() {
        // Instantiate the LoginInteractor with its dependencies
        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDAO,
                new LoginPresenter(dashboardViewModel, extensionViewModel, settingsViewModel, loginViewModel, viewManagerModel),
                apiStorageDataAccess,
                instagramAPIDAO,
                facebookAPIDAO,
                telegramAPIDAO,
                instagramStats,
                facebookStats,
                telegramStats
        );

        // Create and return the fully initialized LoginController
        return new LoginController(loginInteractor, viewManagerModel, loginViewModel);
    }
}
