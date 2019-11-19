package com.app.playtrip.fragments.Profile;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.entities.Data;
import com.app.playtrip.entities.FollowingEnt;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.WebServiceConstants;
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
    @BindView(R.id.noDataFound)
    TextView noDataFound;

    private static String userId = "";

    public static FollowedFragment newInstance(String id) {
        userId = id;
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

        serviceHelper.enqueueCall(headerWebService.getFollowed(userId), WebServiceConstants.FOLLOWED);
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        super.ResponseSuccess(result, Tag);
        switch (Tag) {
            case WebServiceConstants.FOLLOWED:
                Data<FollowingEnt> data = (Data<FollowingEnt>) result;
                ArrayList<FollowingEnt> arrayList = data.getData();
                setData(arrayList);
                break;
        }
    }

    private void setData(ArrayList<FollowingEnt> data) {

        if (data != null && data.size() > 0) {
            noDataFound.setVisibility(View.GONE);
            rvFollowed.setVisibility(View.VISIBLE);
            rvFollowed.BindRecyclerView(new FollowingBinder(getDockActivity(), prefHelper, this, true), data,
                    new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                    , new DefaultItemAnimator());
        } else {
            noDataFound.setVisibility(View.VISIBLE);
            rvFollowed.setVisibility(View.GONE);
        }
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