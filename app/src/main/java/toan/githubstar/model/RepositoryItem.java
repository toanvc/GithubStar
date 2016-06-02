package toan.githubstar.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Toan Vu on 6/1/16.
 */
public class RepositoryItem {

    private String name;
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @SerializedName("created_at")
    private String createdAt;
    private String language;
    private Contributor owner;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Contributor getOwner() {
        return owner;
    }

    public void setOwner(Contributor owner) {
        this.owner = owner;
    }
}
