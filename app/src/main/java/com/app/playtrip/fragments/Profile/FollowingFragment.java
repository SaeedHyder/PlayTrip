package com.app.playtrip.fragments.Profile;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.playtrip.R;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.binders.FollowingBinder;
import com.app.playtrip.ui.binders.VideosBinder;
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FollowingFragment extends BaseFragment implements RecyclerClickListner {

    @BindView(R.id.rvFollowing)
    CustomRecyclerView rvFollowing;


    public static FollowingFragment newInstance() {
        return new FollowingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    private void setData() {

        ArrayList<String> data=new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");

        rvFollowing.BindRecyclerView(new FollowingBinder(getDockActivity(), prefHelper, this,false), data,
                new LinearLayoutManager(getDockActivity(),LinearLayoutManager.VERTICAL,false)
                , new DefaultItemAnimator());
    }


    @Override

    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();

    }


    @Override
    public void onClick(Object entity, int position) {
        notImplemented();
    }
}