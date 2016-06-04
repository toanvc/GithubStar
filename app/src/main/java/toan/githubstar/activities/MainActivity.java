package toan.githubstar.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.githubstar.R;
import toan.githubstar.adapter.RepositoryAdapter;
import toan.githubstar.model.RepositoryItem;
import toan.githubstar.presenter.GetPresenter;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mLoading;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    RepositoryAdapter mAdapter;
    private GetPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new GetPresenter(this);

        mAdapter = new RepositoryAdapter(this, new ArrayList<RepositoryItem>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        initSwipeLayout();

        mPresenter.loadRepositories(true);
    }

    @Override
    public void onDestroy() {
        if (mAdapter != null) {
            mAdapter.onRelease();
            mAdapter = null;
        }

        super.onDestroy();
    }

    public void showProgress(boolean isShow) {
        mLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void onRefreshDone(){
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void onRequestFail(String error) {
        Log.w("MainActivity", "Error: " + error);
        Toast.makeText(this, "Error. Please try again!", Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);
        mLoading.setVisibility(View.GONE);
    }

    public void onRequestSuccess(List<RepositoryItem> listRepo) {
        mAdapter.setRepoList(listRepo);
        mAdapter.notifyDataSetChanged();
    }

    private void initSwipeLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadRepositories(true);
            }
        });
    }

}
