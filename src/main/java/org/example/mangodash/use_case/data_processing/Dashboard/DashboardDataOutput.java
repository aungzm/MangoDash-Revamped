package org.example.mangodash.use_case.data_processing.Dashboard;

import org.example.mangodash.model.SocialMediaStats;
import java.util.ArrayList;
import java.util.List;

public class DashboardDataOutput {
    private final SocialMediaStats facebookStats;
    private final SocialMediaStats instagramStats;
    private final SocialMediaStats telegramStats;
    private final List<String> errors;

    private DashboardDataOutput(Builder builder) {
        this.facebookStats = builder.facebookStats;
        this.instagramStats = builder.instagramStats;
        this.telegramStats = builder.telegramStats;
        this.errors = builder.errors;
    }

    public SocialMediaStats getFacebookStats() {
        return facebookStats;
    }

    public SocialMediaStats getInstagramStats() {
        return instagramStats;
    }

    public SocialMediaStats getTelegramStats() {
        return telegramStats;
    }

    public List<String> getErrors() {
        return errors;
    }

    // Builder class for flexible instantiation
    public static class Builder {
        private SocialMediaStats facebookStats;
        private SocialMediaStats instagramStats;
        private SocialMediaStats telegramStats;
        private List<String> errors = new ArrayList<>(); // Initialize an empty list for errors

        public Builder setFacebookStats(SocialMediaStats facebookStats) {
            this.facebookStats = facebookStats;
            return this;
        }

        public Builder setInstagramStats(SocialMediaStats instagramStats) {
            this.instagramStats = instagramStats;
            return this;
        }

        public Builder setTelegramStats(SocialMediaStats telegramStats) {
            this.telegramStats = telegramStats;
            return this;
        }

        public Builder addErrors(List<String> errors) {
            this.errors.addAll(errors);
            return this;
        }

        public DashboardDataOutput build() {
            // Only build if at least one stat is not null or there are errors
            if (facebookStats != null || instagramStats != null || telegramStats != null || !errors.isEmpty()) {
                return new DashboardDataOutput(this);
            }
            return null; // Return null if all stats are null and there are no errors
        }
    }
}
