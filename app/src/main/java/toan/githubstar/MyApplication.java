package toan.githubstar;

import android.app.Application;
import android.content.Context;

import toan.githubstar.api.GithubApiService;
import toan.githubstar.module.ApiModule;
import toan.githubstar.module.DaggerGithubComponent;
import toan.githubstar.module.GithubComponent;

/**
 * Created by Toan Vu on 6/1/16.
 */
public class MyApplication extends Application {

    GithubComponent mComponent;
    static GithubApiService mApi;
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        mComponent = DaggerGithubComponent.builder()
                .apiModule(new ApiModule())//.appModule(new AppModule())
                .build();
        mApi = mComponent.provideGithubService();
    }

    public static Context getContext() {
        return context;
    }

    public static GithubApiService getInstagramServiceApi() {
        return mApi;
    }

}
