package toan.githubstar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ContributorFragment extends BaseFragment{
    public static final String EXTRAS_OWNER = "extras_repository_owner";

    @BindView(R.id.name)
    TextView mTxtName;
    @BindView(R.id.profile_link)
    TextView mTxtLink;
    @BindView(R.id.avatar)
    ImageView mImgAvatar;

    public static ContributorFragment createNewContributerFragment(Contributor contributor){
        ContributorFragment fragment = new ContributorFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRAS_OWNER, contributor);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contributor, container, false);
        ButterKnife.bind(this,view);
        Contributor contributor = getArguments().getParcelable(EXTRAS_OWNER);

            bindData(contributor);
        return view;
    }


    public void bindData(Contributor contributor){
        if (contributor ==null)
            return;
        mTxtName.setText(contributor.getName());
        mTxtLink.setText(contributor.getHtmlUrl());
        Glide.with(this).load(contributor.getAvatarUrl())
                .placeholder(R.drawable.placeholder)
                .transform(new CircleTransform(getActivity()))
                .into(mImgAvatar);
    }
}
