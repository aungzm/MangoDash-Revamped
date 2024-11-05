package org.example.mangodash.use_case.data_processing.Settings;

public class SettingsDataOutput {
    private final String username;
    private String name;
    private String bio;

    public SettingsDataOutput(Builder builder) {
        this.username = builder.username;
        this.name = builder.name;
        this.bio = builder.bio;
    }

    public static class Builder {
        private final String username;
        private String name;
        private String bio;

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

        public SettingsDataOutput build() {
            return new SettingsDataOutput(this);
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
}
