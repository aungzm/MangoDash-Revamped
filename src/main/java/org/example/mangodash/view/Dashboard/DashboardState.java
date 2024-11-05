package org.example.mangodash.view.Dashboard;

import org.example.mangodash.model.SocialMediaStats;
import java.util.ArrayList;
import java.util.List;

public class DashboardState {
    private SocialMediaStats facebookStats;
    private SocialMediaStats instagramStats;
    private SocialMediaStats telegramStats;
    private List<String> errors;
    private String username;

    // Constructor initializes the errors list
    public DashboardState() {
        this.errors = new ArrayList<>();
    }

    // Getters and Setters
    public SocialMediaStats getFacebookStats() {
        return facebookStats;
    }

    public void setFacebookStats(SocialMediaStats facebookStats) {
        this.facebookStats = facebookStats;
    }

    public SocialMediaStats getInstagramStats() {
        return instagramStats;
    }

    public void setInstagramStats(SocialMediaStats instagramStats) {
        this.instagramStats = instagramStats;
    }

    public SocialMediaStats getTelegramStats() {
        return telegramStats;
    }

    public void setTelegramStats(SocialMediaStats telegramStats) {
        this.telegramStats = telegramStats;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clearErrors() {
        this.errors.clear();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "DashboardState{" +
                "facebookStats=" + facebookStats +
                ", instagramStats=" + instagramStats +
                ", telegramStats=" + telegramStats +
                ", errors=" + errors +
                '}';
    }
}
