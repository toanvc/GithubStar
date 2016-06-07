package toan.githubstar.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.githubstar.R;
import toan.githubstar.fragments.ContributorFragment;
import toan.githubstar.fragments.GitFragment;
import toan.githubstar.interfaces.IClickItem;
import toan.githubstar.interfaces.ILoadData;
import toan.githubstar.model.Contributor;
import toan.githubstar.model.RepositoryItem;
import toan.githubstar.presenter.GetPresenter;

public class MainActivity extends AppCompatActivity implements IClickItem, ILoadData {

    private static final String BACKSTACK = "MainActivity";
    @BindView(R.id.container)
    FrameLayout mViewContainer;
    @BindView(R.id.progress_bar)
    ProgressBar mLoading;
    private GetPresenter mPresenter;


    @Override
    public void onClick(Contributor contributor) {
        //control click item
        if (findViewById(R.id.contributer_container) != null) {

            getContributorFragment().bindData(contributor);
        } else {
            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            f.replace(R.id.container, ContributorFragment.createNewContributerFragment(contributor));
            f.addToBackStack(BACKSTACK);
            f.commit();
        }
    }

    @Override
    public void onLoadData() {
        mPresenter.loadRepositories(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        mPresenter = new GetPresenter(this);
        mPresenter.loadRepositories(false);
    }

    @Override
    public void onDestroy() {
//        if (mAdapter != null) {
//            mAdapter.onRelease();
//            mAdapter = null;
//        }

        super.onDestroy();
    }

    public void showProgress(boolean isShow) {
        mLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);

    }

    public void onRefreshDone() {
        getGitListFragment().onRefreshDone();
//        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void onRequestFail(String error) {
        Log.w("MainActivity", "Error: " + error);
        Toast.makeText(this, "Error. Please try again!", Toast.LENGTH_SHORT).show();
//        mSwipeRefreshLayout.setRefreshing(false);
//        mLoading.setVisibility(View.GONE);

    }

    public void onRequestSuccess(ArrayList<RepositoryItem> listRepo) {
        Contributor con = null;
        if (listRepo.size() > 0)
            con = listRepo.get(0).getOwner();

        GitFragment fragment = getGitListFragment();
        if (fragment == null) {

            initFragments(listRepo, con);
        } else {
            fragment.onRequestSuccess(listRepo);
            getContributorFragment().bindData(con);
        }
    }

    private void initFragments(ArrayList<RepositoryItem> listRepo, Contributor con) {
        FragmentTransaction f = getSupportFragmentManager().beginTransaction();
        GitFragment gitFragment = GitFragment.createNewContributerFragment(listRepo);
        gitFragment.setIClickItem(this);
        gitFragment.setILoadData(this);
        f.add(R.id.container, gitFragment);
        // the fragment_container FrameLayout
        if (findViewById(R.id.contributer_container) != null) {
            f.add(R.id.contributer_container, ContributorFragment.createNewContributerFragment(con));
        }
        f.commit();

    }


    private GitFragment getGitListFragment() {
        GitFragment fragment = (GitFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        return fragment;
    }

    private ContributorFragment getContributorFragment() {
        ContributorFragment contributorFragment = (ContributorFragment) getSupportFragmentManager().findFragmentById(R.id.contributer_container);
        return contributorFragment;
    }
}
