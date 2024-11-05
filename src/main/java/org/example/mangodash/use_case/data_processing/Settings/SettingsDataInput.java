package org.example.mangodash.use_case.data_processing.Settings;

public class SettingsDataInput {
    private final String username;
    private String name;
    private String bio;
    private String newPassword;
    private String oldPassword;
    private String repeatNewPassword;

    private SettingsDataInput(Builder builder) {
        this.username = builder.username;
        this.name = builder.name;
        this.bio = builder.bio;
        this.newPassword = builder.newPassword;
        this.oldPassword = builder.oldPassword;
        this.repeatNewPassword = builder.repeatNewPassword;
    }

    public static class Builder {
        private final String username;
        private String name;
        private String bio;
        private String newPassword;
        private String oldPassword;
        private String repeatNewPassword;

        public Builder(String username) {
            this.username = username;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder setPasswordChange(String oldPassword, String newPassword, String repeatNewPassword) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
            this.repeatNewPassword = repeatNewPassword;
            return this;
        }

        public SettingsDataInput build() {
            return new SettingsDataInput(this);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }
}
