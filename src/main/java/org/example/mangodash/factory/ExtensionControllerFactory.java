package org.example.mangodash.factory;

import org.example.mangodash.controller.ExtensionController;
import org.example.mangodash.model.SocialMediaStats;
import org.example.mangodash.use_case.api_data_access.APIDataAccessInterface;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionInteractor;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionInputBoundary;
import org.example.mangodash.view.Extension.ExtensionPresenter;
import org.example.mangodash.view.Extension.ExtensionViewModel;
import org.example.mangodash.view.ViewManagerModel;
import org.example.mangodash.use_case.db_acccess.APIStorageDataAccessInterface;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;

public class ExtensionControllerFactory {

    private final UserDataAccessInterface userDAO;
    private final APIStorageDataAccessInterface apiStorageDataAccess;
    private final APIDataAccessInterface facebookAPIDAO;
    private final APIDataAccessInterface instagramAPIDAO;
    private final APIDataAccessInterface telegramAPIDAO;

    private final SocialMediaStats facebookStats;
    private final SocialMediaStats instagramStats;
    private final SocialMediaStats telegramStats;

    private final ExtensionViewModel extensionViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExtensionControllerFactory(UserDataAccessInterface userDAO,
                                      APIStorageDataAccessInterface apiStorageDataAccess,
                                      APIDataAccessInterface facebookAPIDAO,
                                      APIDataAccessInterface instagramAPIDAO,
                                      APIDataAccessInterface telegramAPIDAO,
                                      SocialMediaStats facebookStats,
                                      SocialMediaStats instagramStats,
                                      SocialMediaStats telegramStats,
                                      ExtensionViewModel extensionViewModel,
                                      ViewManagerModel viewManagerModel) {
        this.userDAO = userDAO;
        this.apiStorageDataAccess = apiStorageDataAccess;
        this.facebookAPIDAO = facebookAPIDAO;
        this.instagramAPIDAO = instagramAPIDAO;
        this.telegramAPIDAO = telegramAPIDAO;
        this.facebookStats = facebookStats;
        this.instagramStats = instagramStats;
        this.telegramStats = telegramStats;
        this.extensionViewModel = extensionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public ExtensionController createExtensionController() {
        // Create the ExtensionPresenter
        ExtensionPresenter extensionPresenter = new ExtensionPresenter(viewManagerModel, extensionViewModel);

        // Create the ExtensionInteractor with its dependencies
        ExtensionInputBoundary extensionInteractor = new ExtensionInteractor(
                userDAO,
                apiStorageDataAccess,
                instagramStats,
                facebookStats,
                telegramStats,
                extensionPresenter,
                instagramAPIDAO,
                facebookAPIDAO,
                telegramAPIDAO
        );

        // Create and return the fully initialized ExtensionController
        return new ExtensionController(extensionViewModel, viewManagerModel, extensionInteractor);
    }
}
