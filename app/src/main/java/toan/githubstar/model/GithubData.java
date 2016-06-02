package toan.githubstar.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Toan Vu on 6/1/16.
 */
public class GithubData {

    @SerializedName("total_count")
    private int totalCount;
    private List<RepositoryItem> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<RepositoryItem> getItems() {
        return items;
    }

    public void setItems(List<RepositoryItem> items) {
        this.items = items;
    }
}
