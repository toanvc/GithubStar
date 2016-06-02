package toan.githubstar.presenter;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import toan.githubstar.activities.MainActivity;
import toan.githubstar.api.GithubApiService;
import toan.githubstar.model.GithubData;
import toan.githubstar.module.ApiModule;
import toan.githubstar.module.DaggerGithubComponent;
import toan.githubstar.module.GithubComponent;
import toan.githubstar.util.MyUtils;

public class GetPresenter {
    GithubApiService mApi;
    GithubComponent mApiComponent;
    MainActivity mView;

    public GetPresenter(MainActivity view) {
        this.mView = view;
        mApiComponent = DaggerGithubComponent.builder()
                .apiModule(new ApiModule())
                .build();
        mApi = mApiComponent.provideGithubService();
    }

    public void loadRepositories(final boolean isRefresh) {
        if (!isRefresh)
            mView.showProgress(true);
        mApi.getStarRepositiories("created:>="+ MyUtils.getPastSevenDay(), "star", "desc")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<GithubData>>() {
                    @Override
                    public void onCompleted() {
                        mView.showProgress(false);
                        if (isRefresh) {
                            mView.onRefreshDone();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestFail(e.toString());
                    }

                    @Override
                    public void onNext(Response<GithubData> response) {
                        mView.onRequestSuccess(response.body().getItems());
                    }
                });
    }
}