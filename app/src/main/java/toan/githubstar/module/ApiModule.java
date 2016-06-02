package toan.githubstar.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toan.githubstar.api.GithubApiService;
import toan.githubstar.common.Constant;


/**
 * Created by Toan Vu on 6/1/16.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    OkHttpClient okHttpClient() {
       return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    GithubApiService provideGetApi(Retrofit retrofit) {
        return retrofit.create(GithubApiService.class);
    }


}
