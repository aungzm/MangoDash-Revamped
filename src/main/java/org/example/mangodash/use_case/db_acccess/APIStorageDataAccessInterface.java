package org.example.mangodash.use_case.db_acccess;

import org.example.mangodash.model.APIStorage;

import java.util.List;

public interface APIStorageDataAccessInterface {

    /**
     * Saves or updates API storage data for a user.
     *
     * @param apiStorage An APIStorage object containing the data to save.
     */
    void save(APIStorage apiStorage);

    /**
     * Updates the API status for a specific API type for a user.
     *
     * @param username  The username of the user.
     * @param apiType   The type of API.
     * @param newStatus The new status to set for the API.
     */
    void updateApiStatus(String username, String apiType, boolean newStatus);

    /**
     * Fetches all API data entries for a specific user.
     *
     * @param username The username of the user.
     * @return A list of APIStorage objects representing the API data entries for the user.
     */
    List<APIStorage> getApisForUser(String username);

    /**
     * Checks if an API entry exists for a user and a specific API type.
     *
     * @param username The username of the user.
     * @param apiType  The type of API.
     * @return true if the API entry exists, false otherwise.
     */
    boolean apiExists(String username, String apiType);
}
