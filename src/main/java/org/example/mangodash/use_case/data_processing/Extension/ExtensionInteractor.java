package org.example.mangodash.use_case.data_processing.Extension;

import org.example.mangodash.model.*;
import org.example.mangodash.use_case.api_data_access.APIDataAccessInterface;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;
import org.example.mangodash.use_case.data_processing.DashboardOutputBuilder;
import org.example.mangodash.use_case.db_acccess.APIStorageDataAccessInterface;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;
import org.example.mangodash.view.Dashboard.DashboardOutputBoundary;
import org.example.mangodash.view.Extension.ExtensionOutputBoundary;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ExtensionInteractor implements ExtensionInputBoundary {
    private final APIStorageDataAccessInterface apiStorageDAO;
    private final SocialMediaStats instagramStats;

    private final SocialMediaStats facebookStats;

    private final SocialMediaStats telegramStats;

    private final ExtensionOutputBoundary extensionOutputBoundary;

    private final APIDataAccessInterface instagramAPIDAO;
    private final APIDataAccessInterface facebookAPIDAO;

    private final APIDataAccessInterface telegramAPIDAO;

    public ExtensionInteractor(UserDataAccessInterface userDAO, APIStorageDataAccessInterface apiStorageDAO,
                               SocialMediaStats instagramStats, SocialMediaStats facebookStats,
                               SocialMediaStats telegramStats, ExtensionOutputBoundary extensionOutputBoundary, APIDataAccessInterface instagramAPIDAO,
                               APIDataAccessInterface facebookAPIDAO, APIDataAccessInterface telegramAPIDAO) {
        this.apiStorageDAO = apiStorageDAO;
        this.instagramStats = instagramStats;
        this.facebookStats = facebookStats;
        this.telegramStats = telegramStats;
        this.extensionOutputBoundary = extensionOutputBoundary;
        this.instagramAPIDAO = instagramAPIDAO;
        this.facebookAPIDAO = facebookAPIDAO;
        this.telegramAPIDAO = telegramAPIDAO;
    }

    @Override
    public void executeSaveAPIData(ExtensionDataInput extensionDataInput) throws MalformedURLException {
        String username = extensionDataInput.getUsername();
        String mediaName = extensionDataInput.getMediaName();
        String newApiKey = extensionDataInput.getApiKey();
        Boolean status = extensionDataInput.getStatus();

        List<APIStorage> currentListApiStorage = apiStorageDAO.getApisForUser(username);

        APIStorage newApiStorage = null;
        APIDataAccessInterface mediaAPIDAO = null;
        SocialMediaStats mediaStats = null;

        // Determine the appropriate DAO and stats based on mediaName
        switch (mediaName.toLowerCase()) {
            case "facebook":
                mediaAPIDAO = facebookAPIDAO;
                mediaStats = facebookStats;
                break;
            case "instagram":
                mediaAPIDAO = instagramAPIDAO;
                mediaStats = instagramStats;
                break;
            case "telegram":
                mediaAPIDAO = telegramAPIDAO;
                mediaStats = telegramStats;
                break;
            default:
                extensionOutputBoundary.prepareFailView("Unknown media name: " + mediaName);
                return;
        }

        // Locate the existing APIStorage entry, if any
        for (APIStorage apiStorage : currentListApiStorage) {
            if (apiStorage.getApiType().equalsIgnoreCase(mediaName)) {
                newApiStorage = apiStorage;
                break;
            }
        }

        // Determine cases based on status and API key changes
        if (newApiStorage != null) {
            boolean apiKeyChanged = !newApiStorage.getApiKey().equals(newApiKey);
            boolean statusChanged = !newApiStorage.getApiStatus() == status;

            if (!apiKeyChanged && !statusChanged) {
                // Case 1: No changes
                DashboardDataOutput.Builder builder = DashboardOutputBuilder.getBuilder(mediaStats, mediaName);
                DashboardDataOutput dashboardDataOutput = builder.build();
                ExtensionDataOutput extensionDataOutput = new ExtensionDataOutput(new ExtensionDataOutput.ApiDetail(newApiKey, status, mediaName));
                extensionOutputBoundary.prepareSuccessView(dashboardDataOutput, extensionDataOutput);
                return;
            } else if (statusChanged && !apiKeyChanged) {
                // Case 2: Status change only
                apiStorageDAO.updateApiStatus(username, mediaName, status);
                if (status) {
                    // Status changed from false -> true, fetch new data
                    mediaAPIDAO.setAPI(newApiKey);
                    mediaAPIDAO.fetchData(mediaStats);
                    if (mediaAPIDAO.isApiError()) {
                        extensionOutputBoundary.prepareFailView("Error fetching data with current API key");
                        return;
                    }
                } else {
                    // Status changed from true -> false, set stats to null
                    setStatsToNull(mediaStats);
                }
            } else if (apiKeyChanged) {
                // Case 3: API key changed (and possibly status too)
                newApiStorage.setApiKey(newApiKey);
                newApiStorage.setApiStatus(status);
                apiStorageDAO.save(newApiStorage);

                if (status) {
                    mediaAPIDAO.setAPI(newApiKey);
                    mediaAPIDAO.fetchData(mediaStats);
                    if (mediaAPIDAO.isApiError()) {
                        extensionOutputBoundary.prepareFailView("Error invalid API key");
                        return;
                    }
                } else {
                    setStatsToNull(mediaStats);
                }
            }
        } else {
            // Case 4: New APIStorage entry (no existing record)
            apiStorageDAO.save(new APIStorage(username, newApiKey, status, mediaName));
            if (status) {
                mediaAPIDAO.setAPI(newApiKey);
                mediaAPIDAO.fetchData(mediaStats);
                if (mediaAPIDAO.isApiError()) {
                    extensionOutputBoundary.prepareFailView("Error fetching data for new API key");
                    return;
                }
            } else {
                setStatsToNull(mediaStats);
            }
        }

        // Build the final output and return success view
        DashboardDataOutput.Builder builder = DashboardOutputBuilder.getBuilder(mediaStats, mediaName);
        DashboardDataOutput dashboardDataOutput = builder.build();
        ExtensionDataOutput extensionDataOutput = new ExtensionDataOutput(new ExtensionDataOutput.ApiDetail(newApiKey, status, mediaName));
        extensionOutputBoundary.prepareSuccessView(dashboardDataOutput, extensionDataOutput);
    }

    private void setStatsToNull(SocialMediaStats stats) {
        if (stats instanceof FacebookStats) {
            FacebookStats facebookStats = (FacebookStats) stats;
            facebookStats.setUserId(null);
            facebookStats.setName(null);
            facebookStats.setEmail(null);
            facebookStats.setFriendCount(0);
            facebookStats.setPosts(null); // Assuming `setPosts` accepts a null list
        } else if (stats instanceof InstagramStats) {
            InstagramStats instagramStats = (InstagramStats) stats;
            instagramStats.setUserId(null);
            instagramStats.setName(null);
            instagramStats.setFriendCount(0);
        } else if (stats instanceof TelegramStats) {
            TelegramStats telegramStats = (TelegramStats) stats;
            telegramStats.setUserId(null);
            telegramStats.setEmail(null);
            telegramStats.setFriendCount(0);
        }
    }

}
