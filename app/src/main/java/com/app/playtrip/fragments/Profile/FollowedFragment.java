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
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FollowedFragment extends BaseFragment implements RecyclerClickListner {

    @BindView(R.id.rvFollowed)
    CustomRecyclerView rvFollowed;

    public static FollowedFragment newInstance() {
        return new FollowedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faragment_followed, container, false);
       ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    private void setData() {

        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");

        rvFollowed.BindRecyclerView(new FollowingBinder(getDockActivity(), prefHelper, this,true), data,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
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