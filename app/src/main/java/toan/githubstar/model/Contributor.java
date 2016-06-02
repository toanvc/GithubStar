package toan.githubstar.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Toan Vu on 6/1/16.
 */
public class Contributor implements Serializable {
    @SerializedName("login")
    private String name;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

