package org.example.mangodash.factory;

import org.example.mangodash.controller.DashboardController;
import org.example.mangodash.model.SocialMediaStats;
import org.example.mangodash.use_case.api_data_access.APIDataAccessInterface;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardInteractor;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardInputBoundary;
import org.example.mangodash.use_case.db_acccess.APIStorageDataAccessInterface;
import org.example.mangodash.view.Dashboard.DashboardPresenter;
import org.example.mangodash.view.Dashboard.DashboardViewModel;
import org.example.mangodash.view.ViewManagerModel;

public class DashboardControllerFactory {

    private final SocialMediaStats instagramStats;
    private final SocialMediaStats facebookStats;
    private final SocialMediaStats telegramStats;
    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final APIStorageDataAccessInterface apiStorageDataAccessInterface;
    private final APIDataAccessInterface facebookAPIDAO;
    private final APIDataAccessInterface instagramAPIDAO;
    private final APIDataAccessInterface telegramAPIDAO;

    public DashboardControllerFactory(SocialMediaStats instagramStats,
                                      SocialMediaStats facebookStats,
                                      SocialMediaStats telegramStats,
                                      DashboardViewModel dashboardViewModel,
                                      ViewManagerModel viewManagerModel,
                                      APIStorageDataAccessInterface apiStorageDataAccessInterface,
                                      APIDataAccessInterface facebookAPIDAO,
                                      APIDataAccessInterface instagramAPIDAO,
                                      APIDataAccessInterface telegramAPIDAO) {
        this.instagramStats = instagramStats;
        this.facebookStats = facebookStats;
        this.telegramStats = telegramStats;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
        this.apiStorageDataAccessInterface = apiStorageDataAccessInterface;
        this.facebookAPIDAO = facebookAPIDAO;
        this.instagramAPIDAO = instagramAPIDAO;
        this.telegramAPIDAO = telegramAPIDAO;
    }

    public DashboardController createDashboardController() {
        // Instantiate DashboardPresenter
        DashboardPresenter dashboardPresenter = new DashboardPresenter(dashboardViewModel, viewManagerModel);

        // Instantiate DashboardInteractor with dependencies
        DashboardInputBoundary dashboardInteractor = new DashboardInteractor(
                instagramStats,
                facebookStats,
                telegramStats,
                dashboardPresenter,
                apiStorageDataAccessInterface,
                instagramAPIDAO,
                telegramAPIDAO,
                apiStorageDataAccessInterface,
                facebookAPIDAO
        );

        // Return fully initialized DashboardController
        return new DashboardController(dashboardInteractor, dashboardViewModel, viewManagerModel);
    }
}
