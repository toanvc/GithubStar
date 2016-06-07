package toan.githubstar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.githubstar.R;
import toan.githubstar.adapter.RepositoryAdapter;
import toan.githubstar.interfaces.IClickItem;
import toan.githubstar.interfaces.ILoadData;
import toan.githubstar.model.RepositoryItem;

/**
 * Created by Toan Vu on 6/6/16.
 */
public class GitFragment extends BaseFragment {
    private static final String EXTRAS_LIST = "repo_list";
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    RepositoryAdapter mAdapter;

    IClickItem mIClickItem;
    ILoadData mILoadData;
    public static GitFragment createNewContributerFragment(ArrayList<RepositoryItem> listRepo){
        GitFragment fragment = new GitFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRAS_LIST, listRepo);
        fragment.setArguments(bundle);
        return  fragment;
    }

    public void setIClickItem(IClickItem iClickItem){
        this.mIClickItem = iClickItem;
    }

    public void setILoadData(ILoadData iLoadData){
        this.mILoadData = iLoadData;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_git, container, false);
        ButterKnife.bind(this, view);
        Bundle bun = getArguments();
        ArrayList<RepositoryItem> list = bun.getParcelableArrayList(EXTRAS_LIST);
        mAdapter = new RepositoryAdapter(this.getActivity(), list, mIClickItem);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        initSwipeLayout();

        getActivity().setTitle(R.string.app_name);
        return view;
    }

    @Override
    public void onDestroy() {
        if (mAdapter != null) {
            mAdapter.onRelease();
            mAdapter = null;
        }

        super.onDestroy();
    }


    public void onRefreshDone(){
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void onRequestSuccess(List<RepositoryItem> listRepo) {
        mAdapter.setRepoList(listRepo);
        mAdapter.notifyDataSetChanged();
    }

    private void initSwipeLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mILoadData !=null){
                    mILoadData.onLoadData();
                }
            }
        });
    }




}
