package org.example.mangodash.use_case.data_processing.Login;

import org.example.mangodash.model.*;
import org.example.mangodash.use_case.api_data_access.APIDataAccessInterface;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;
import org.example.mangodash.use_case.data_processing.DashboardOutputBuilder;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionDataOutput;
import org.example.mangodash.use_case.data_processing.Settings.SettingsDataOutput;
import org.example.mangodash.use_case.db_acccess.APIStorageDataAccessInterface;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;
import org.example.mangodash.view.Login.LoginOutputBoundary;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class LoginInteractor implements LoginInputBoundary{

    private final UserDataAccessInterface userDAO;
    private final LoginOutputBoundary loginOutputBoundary;

    private final APIStorageDataAccessInterface apiStorageDataAccessInterface;
    private final APIDataAccessInterface instagramAPIDAO;
    private final APIDataAccessInterface facebookAPIDAO;

    private final APIDataAccessInterface telegramAPIDAO;
    private final SocialMediaStats instagramStats;

    private final SocialMediaStats facebookStats;

    private final SocialMediaStats telegramStats;

    public LoginInteractor(UserDataAccessInterface userDAO, LoginOutputBoundary loginOutputBoundary,
                           APIStorageDataAccessInterface apiStorageDataAccessInterface,
                           APIDataAccessInterface instagramAPIDataAccessInterface,
                           APIDataAccessInterface facebookAPIDAO, APIDataAccessInterface telegramAPIDAO,
                           SocialMediaStats instagramStats,
                           SocialMediaStats facebookStats,
                           SocialMediaStats telegramStats) {
        this.userDAO = userDAO;
        this.loginOutputBoundary = loginOutputBoundary;
        this.apiStorageDataAccessInterface = apiStorageDataAccessInterface;
        this.instagramAPIDAO = instagramAPIDataAccessInterface;
        this.facebookAPIDAO = facebookAPIDAO;
        this.telegramAPIDAO = telegramAPIDAO;
        this.instagramStats = instagramStats;
        this.facebookStats = facebookStats;
        this.telegramStats = telegramStats;
    }

    @Override
    public void executeLogin(LoginDataInput loginDataInput) throws MalformedURLException {
        String username = loginDataInput.getUsername();
        String password = loginDataInput.getPassword();

        if (!userDAO.existsByName(username)) {
            loginOutputBoundary.prepareFailView(username + ": Account does not exist.");
        } else {
            User user = userDAO.getUser(username);
            if (!password.equals(user.getPassword())) {
                loginOutputBoundary.prepareFailView("Incorrect password");
            } else {
                String name = user.getName();
                String bio = user.getBio();
                SettingsDataOutput settingsDataOutput = new SettingsDataOutput.Builder(username)
                        .setName(name)
                        .setBio(bio)
                        .build();

                // Create and populate ExtensionDataOutput using APIStorageDAO
                ExtensionDataOutput extensionDataOutput = new ExtensionDataOutput();
                List<APIStorage> userApis = apiStorageDataAccessInterface.getApisForUser(username);

                // Initialize API data objects

                boolean facebookFetched = false;
                boolean instagramFetched = false;
                boolean telegramFetched = false;
                List<String> errors = new ArrayList<>();

                for (APIStorage apiStorage : userApis) {
                    // Add API detail to ExtensionDataOutput
                    ExtensionDataOutput.ApiDetail apiDetail = new ExtensionDataOutput.ApiDetail(
                            apiStorage.getApiKey(),
                            apiStorage.getApiStatus(),
                            apiStorage.getApiType()
                    );
                    extensionDataOutput.addApiDetail(apiDetail);

                    // Fetch data only if the API status is true
                    if (apiStorage.getApiStatus()) {
                        switch (apiStorage.getApiType().toLowerCase()) {
                            case "facebook":
                                facebookAPIDAO.setAPI(apiStorage.getApiKey());
                                facebookAPIDAO.fetchData(facebookStats);
                                facebookFetched = !facebookAPIDAO.isApiError();
                                if (!facebookFetched) {
                                    errors.add("Error getting Facebook API Data");
                                }
                                break;

                            case "instagram":
                                instagramAPIDAO.setAPI(apiStorage.getApiKey());
                                instagramAPIDAO.fetchData(instagramStats);
                                instagramFetched = !instagramAPIDAO.isApiError();
                                if (!instagramFetched) {
                                    errors.add("Error getting Instagram API Data");
                                }
                                break;

                            case "telegram":
                                telegramAPIDAO.setAPI(apiStorage.getApiKey());
                                telegramAPIDAO.fetchData(telegramStats);
                                telegramFetched = !telegramAPIDAO.isApiError();
                                if (!telegramFetched) {
                                    errors.add("Error getting Telegram API Data");
                                }
                                break;

                            default:
                                System.out.println("Unknown API type: " + apiStorage.getApiType());
                                break;
                        }
                    }
                }

                DashboardDataOutput.Builder builder = DashboardOutputBuilder.getBuilder(facebookFetched, facebookStats, instagramFetched, instagramStats, telegramFetched, telegramStats, errors);

                DashboardDataOutput dashboardDataOutput = builder.build();

                loginOutputBoundary.prepareSuccessView(settingsDataOutput, extensionDataOutput, dashboardDataOutput, errors);
            }
        }
    }

    @Override
    public void executeForgotPassword(LoginDataInput loginDataInput) {
        System.out.println("Not implemented");
    }
}
