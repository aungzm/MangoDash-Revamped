package org.example.mangodash.use_case.api_data_access;

import org.example.mangodash.model.FacebookStats;
import org.example.mangodash.model.SocialMediaStats;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

public class FacebookAPIDAO implements APIDataAccessInterface {

    private String apiKey;
    private boolean apiError;

    @Override
    public void fetchData(SocialMediaStats socialMediaStats) throws MalformedURLException {
        if (socialMediaStats instanceof FacebookStats) {
            FacebookStats facebookStats = (FacebookStats) socialMediaStats;
            apiError = false;

            String userAccountId = null;
            String userAccountName = null;
            int friendCount = 0;
            List<FacebookStats.Post> posts = new ArrayList<>();

            // Retrieve User's ID, Name, and Email
            URL urlGetUserInfo = new URL(
                    "https://graph.facebook.com/v18.0/me?fields=id,name,email&access_token=" + apiKey);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlGetUserInfo.openStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    JSONObject object = new JSONObject(line);
                    userAccountId = object.getString("id");
                    userAccountName = object.getString("name");
                    String email = object.getString("email");
                    facebookStats.setEmail(email);
                    facebookStats.setName(userAccountName);
                    facebookStats.setUserId(userAccountId);
                }
            } catch (IOException e) {
                System.out.println("Error with API call to get user account ID");
                apiError = true;
                return;
            }

            if (userAccountId == null) {
                return;
            }

            // Retrieve User Friend Count
            URL urlGetFriendsInfo = new URL(
                    "https://graph.facebook.com/v18.0/" + userAccountId + "?fields=friends&access_token=" + apiKey);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlGetFriendsInfo.openStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    JSONObject object = new JSONObject(line);
                    JSONObject userFriends = object.optJSONObject("friends");
                    if (userFriends != null) {
                        JSONObject summary = userFriends.getJSONObject("summary");
                        friendCount = summary.getInt("total_count");
                        facebookStats.setFriendCount(friendCount);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error with API call to get user friends info");
                apiError = true;
                return;
            }

            // Retrieve User's Posts
            URL urlGetPosts = new URL(
                    "https://graph.facebook.com/v18.0/" + userAccountId + "?fields=posts&access_token=" + apiKey);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlGetPosts.openStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    JSONObject object = new JSONObject(line);
                    JSONObject userPosts = object.getJSONObject("posts");
                    JSONArray postsData = userPosts.getJSONArray("data");

                    for (int i = 0; i < postsData.length(); i++) {
                        JSONObject post = postsData.getJSONObject(i);
                        String postId = post.getString("id");
                        String createdTime = post.getString("created_time");
                        String message = post.optString("message", "");

                        // Retrieve post reactions and comments count
                        URL urlGetPostEngagement = new URL(
                                "https://graph.facebook.com/v18.0/" + postId + "?fields=reactions.summary(total_count),comments.summary(total_count)&access_token=" + apiKey);

                        int likeCount = 0;
                        int commentCount = 0;

                        try (BufferedReader postReader = new BufferedReader(new InputStreamReader(urlGetPostEngagement.openStream(), StandardCharsets.UTF_8))) {
                            String postLine;
                            while ((postLine = postReader.readLine()) != null) {
                                JSONObject postObject = new JSONObject(postLine);

                                // Parse likes and comments
                                JSONObject reactions = postObject.optJSONObject("reactions");
                                if (reactions != null) {
                                    JSONObject summary = reactions.getJSONObject("summary");
                                    likeCount = summary.getInt("total_count");
                                }

                                JSONObject comments = postObject.optJSONObject("comments");
                                if (comments != null) {
                                    JSONObject summary = comments.getJSONObject("summary");
                                    commentCount = summary.getInt("total_count");
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error retrieving engagement data for post ID: " + postId);
                            apiError = true;
                        }

                        // Add post data to the list
                        FacebookStats.Post postEntry = new FacebookStats.Post(postId, createdTime, message, likeCount, commentCount);
                        posts.add(postEntry);
                    }
                    facebookStats.setPosts(posts); // Set posts after retrieving all
                }
            } catch (IOException e) {
                System.out.println("Error with API call to get user posts info");
                apiError = true;
                return;
            }
        } else  {
            System.out.println("Wrong instance type. Must be a facebook instance type");
        }
    }

    @Override
    public void setAPI(String apiUrl) {
        this.apiKey = apiUrl;
    }

    @Override
    public boolean isApiError() {
        return apiError;
    }
}

