package toan.githubstar.module;

import javax.inject.Singleton;

import dagger.Component;
import toan.githubstar.api.GithubApiService;

/**
 * Created by Toan Vu on 6/1/16.
 */
@Singleton
@Component(modules = {ApiModule.class})
public interface GithubComponent {
    GithubApiService provideGithubService();

}
