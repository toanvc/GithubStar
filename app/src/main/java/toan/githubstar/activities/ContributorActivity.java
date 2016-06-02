package toan.githubstar.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.githubstar.R;
import toan.githubstar.model.Contributor;
import toan.githubstar.widget.CircleTransform;

/**
 * Created by Toan Vu on 6/1/16.
 */
public class ContributorActivity extends AppCompatActivity{
    public static final String EXTRAS_OWNER = "extras_repository_owner";

    @BindView(R.id.name)
    TextView mTxtName;
    @BindView(R.id.profile_link)
    TextView mTxtLink;
    @BindView(R.id.avatar)
    ImageView mImgAvatar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Contributor contributor = (Contributor) getIntent().getExtras().get(EXTRAS_OWNER);
        bindData(contributor);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void bindData(Contributor contributor){
        mTxtName.setText(contributor.getName());
        mTxtLink.setText(contributor.getHtmlUrl());
        Glide.with(this).load(contributor.getAvatarUrl())
                .placeholder(R.drawable.placeholder)
                .transform(new CircleTransform(this))
                .into(mImgAvatar);
        setTitle(contributor.getName());
    }
}
