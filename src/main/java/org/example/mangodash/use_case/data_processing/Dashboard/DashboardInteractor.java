package org.example.mangodash.use_case.data_processing.Dashboard;

import org.example.mangodash.model.APIStorage;
import org.example.mangodash.model.SocialMediaStats;
import org.example.mangodash.use_case.api_data_access.APIDataAccessInterface;
import org.example.mangodash.use_case.data_processing.DashboardOutputBuilder;
import org.example.mangodash.use_case.db_acccess.APIStorageDataAccessInterface;
import org.example.mangodash.view.Dashboard.DashboardOutputBoundary;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardInteractor implements DashboardInputBoundary{
    private final SocialMediaStats instagramStats;

    private final SocialMediaStats facebookStats;

    private final SocialMediaStats telegramStats;
    
    private final DashboardOutputBoundary dashboardOutputBoundary;
    
    private final APIStorageDataAccessInterface apiStorageDataAccessInterface;
    
    private final APIDataAccessInterface instagramAPIDAO;
    
    private final APIDataAccessInterface telegramAPIDAO;
    
    private final APIDataAccessInterface facebookAPIDAO;

    public DashboardInteractor(SocialMediaStats instagramStats, SocialMediaStats facebookStats, SocialMediaStats telegramStats,
                               DashboardOutputBoundary dashboardOutputBoundary, APIStorageDataAccessInterface facebookDAO,
                               APIDataAccessInterface instagramAPIDAO, APIDataAccessInterface telegramAPIDAO, 
                               APIStorageDataAccessInterface apiStorageDataAccessInterface, APIDataAccessInterface facebookAPIDAO) {
        this.instagramStats = instagramStats;
        this.facebookStats = facebookStats;
        this.telegramStats = telegramStats;
        this.dashboardOutputBoundary = dashboardOutputBoundary;
        this.instagramAPIDAO = instagramAPIDAO;
        this.telegramAPIDAO = telegramAPIDAO;
        this.apiStorageDataAccessInterface = apiStorageDataAccessInterface;
        this.facebookAPIDAO = facebookAPIDAO;
    }

    @Override
    public void executeRefresh(DashboardDataInput dashboardDataInput) throws MalformedURLException {
        String username = dashboardDataInput.getUsername();
        boolean facebookFetched = false;
        boolean instagramFetched = false;
        boolean telegramFetched = false;
        List<APIStorage> userApis = apiStorageDataAccessInterface.getApisForUser(username);
        List<String> errors = new ArrayList<>();

        for (APIStorage apiStorage : userApis) {
            if (apiStorage.getApiStatus()) {
                switch (apiStorage.getApiType().toLowerCase()) {
                    case "facebook":
                        facebookAPIDAO.setAPI(apiStorage.getApiKey());
                        facebookAPIDAO.fetchData(facebookStats);
                        facebookFetched = !facebookAPIDAO.isApiError();
                        if (!facebookFetched) {
                            errors.add("Error getting Facebook API Data");
                        }
                        continue;

                    case "instagram":
                        instagramAPIDAO.setAPI(apiStorage.getApiKey());
                        instagramAPIDAO.fetchData(instagramStats);
                        instagramFetched = !instagramAPIDAO.isApiError();
                        if (!instagramFetched) {
                            errors.add("Error getting Instagram API Data");
                        }
                        continue;

                    case "telegram":
                        telegramAPIDAO.setAPI(apiStorage.getApiKey());
                        telegramAPIDAO.fetchData(telegramStats);
                        telegramFetched = !telegramAPIDAO.isApiError();
                        if (!telegramFetched) {
                            errors.add("Error getting Telegram API Data");
                        }
                        continue;

                    default:
                        System.out.println("Unknown API type: " + apiStorage.getApiType());
                        errors.add("Unknown API type: " + apiStorage.getApiType());
                        break;
                }
            }
        }

        DashboardDataOutput.Builder builder = DashboardOutputBuilder.getBuilder(facebookFetched, facebookStats, instagramFetched, instagramStats, telegramFetched, telegramStats, errors);
        DashboardDataOutput dashboardDataOutput = builder.build();
        dashboardOutputBoundary.prepareSuccessView(dashboardDataOutput);
    }

}
