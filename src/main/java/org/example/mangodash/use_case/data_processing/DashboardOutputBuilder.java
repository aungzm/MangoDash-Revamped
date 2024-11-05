package org.example.mangodash.use_case.data_processing;

import org.example.mangodash.model.SocialMediaStats;
import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;

import java.util.List;

public class DashboardOutputBuilder {

    // Method to build with multiple stats and errors
    public static DashboardDataOutput.Builder getBuilder(
            boolean facebookFetched, SocialMediaStats facebookStats,
            boolean instagramFetched, SocialMediaStats instagramStats,
            boolean telegramFetched, SocialMediaStats telegramStats,
            List<String> errors) {

        DashboardDataOutput.Builder builder = new DashboardDataOutput.Builder();

        if (facebookFetched && facebookStats != null) {
            builder.setFacebookStats(facebookStats);
        }
        if (instagramFetched && instagramStats != null) {
            builder.setInstagramStats(instagramStats);
        }
        if (telegramFetched && telegramStats != null) {
            builder.setTelegramStats(telegramStats);
        }

        // Add errors to the builder
        builder.addErrors(errors);

        return builder;
    }

    // Overloaded method to handle a single media stat, such as only Instagram or only Facebook
    public static DashboardDataOutput.Builder getBuilder(SocialMediaStats singleStat, String mediaName) {
        DashboardDataOutput.Builder builder = new DashboardDataOutput.Builder();

        if ("facebook".equalsIgnoreCase(mediaName) && singleStat != null) {
            builder.setFacebookStats(singleStat);
        } else if ("instagram".equalsIgnoreCase(mediaName) && singleStat != null) {
            builder.setInstagramStats(singleStat);
        } else if ("telegram".equalsIgnoreCase(mediaName) && singleStat != null) {
            builder.setTelegramStats(singleStat);
        }

        return builder;
    }
}
