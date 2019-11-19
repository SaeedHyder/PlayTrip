package com.app.playtrip.fragments.Profile;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.entities.Data;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.WebServiceConstants;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.binders.VideosBinder;
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.app.playtrip.global.WebServiceConstants.VIDEOS;

public class VideosFragment extends BaseFragment implements RecyclerClickListner {

    @BindView(R.id.rvVideos)
    CustomRecyclerView rvVideos;


    private static String userId = "";

    public static VideosFragment newInstance(String id) {
        userId = id;
        return new VideosFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceHelper.enqueueCall(headerWebService.getUserVideos(userId), VIDEOS);

    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        super.ResponseSuccess(result, Tag);
        switch (Tag) {
            case VIDEOS:
                Data<VideoInnerData> dataVideo = (Data<VideoInnerData>) result;
                ArrayList<VideoInnerData> videoInnerData = dataVideo.getData();

                setData(videoInnerData);
                break;

        }
    }

    private void setData(ArrayList<VideoInnerData> data) {
        if (data != null && data.size() > 0) {
            rvVideos.setVisibility(View.VISIBLE);
            rvVideos.BindRecyclerView(new VideosBinder(getDockActivity(), prefHelper, this), data,
                    new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                    , new DefaultItemAnimator());
        } else {
            rvVideos.setVisibility(View.GONE);
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