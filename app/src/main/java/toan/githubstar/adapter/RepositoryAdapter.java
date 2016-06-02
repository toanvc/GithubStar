package toan.githubstar.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.githubstar.R;
import toan.githubstar.activities.ContributorActivity;
import toan.githubstar.model.Contributor;
import toan.githubstar.model.RepositoryItem;
import toan.githubstar.util.MyUtils;

/**
 * Created by Toan Vu on 5/6/16.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.MyViewHolder> {

    private static final String TAG = "RepositoryAdapter";
    private List<RepositoryItem> mMediaList;

    private Activity mActivity;


    public RepositoryAdapter(Activity activity, List<RepositoryItem> mediaList) {
        this.mMediaList = mediaList;
        this.mActivity = activity;
    }


    public void setMediaList(List<RepositoryItem> list) {
        this.mMediaList = list;
    }

    public void addMore(List<RepositoryItem> addedList) {
        this.mMediaList.addAll(addedList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RepositoryItem data = mMediaList.get(position);
        bindData(data, holder);
    }

    @Override
    public int getItemCount() {
        return mMediaList.size();
    }

    private void bindData(final RepositoryItem data, MyViewHolder holder) {
        holder.parentView.setTag(holder);
        holder.name.setText(data.getName());
        holder.language.setText(data.getLanguage());
        holder.date.setText(MyUtils.formatInputDate(data.getCreatedAt()));
        holder.star.setText(String.valueOf(data.getStargazersCount()));

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowDevInfo(data.getOwner());
            }
        });

    }

    private void goToShowDevInfo(Contributor contributor) {
        Intent mpdIntent = new Intent(mActivity, ContributorActivity.class)
                .putExtra(ContributorActivity.EXTRAS_OWNER, contributor);
        mActivity.startActivity(mpdIntent);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repo_name)
        public TextView name;
        @BindView(R.id.date)
        public TextView date;
        @BindView(R.id.language)
        public TextView language;
        @BindView(R.id.star)
        public TextView star;
        public View parentView;

        public MyViewHolder(View view) {
            super(view);
            this.parentView = view;

            try {
                ButterKnife.bind(this, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void onRelease() {
        if (mMediaList != null) {
            mMediaList.clear();
            mMediaList = null;
        }
        if (mActivity !=null){
            mActivity = null;
        }
    }
}