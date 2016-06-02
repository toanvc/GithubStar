package toan.githubstar.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import toan.githubstar.model.GithubData;

/**
 * Created by Toan Vu on 6/1/16.
 */
public interface GithubApiService {

    @GET("repositories")
    Observable<Response<GithubData>> getStarRepositiories(@Query("q") String query,
                                                          @Query("sort") String sortBy,
                                                          @Query("order") String orderBy);
}