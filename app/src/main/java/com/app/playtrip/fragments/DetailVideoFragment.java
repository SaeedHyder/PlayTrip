package com.app.playtrip.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.entities.Data;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.WebServiceConstants;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.binders.BookMarkBinder;
import com.app.playtrip.ui.binders.DetailVideoBinder;
import com.app.playtrip.ui.binders.HomeMiddleBinder;
import com.app.playtrip.ui.views.AutoCompleteLocation;
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailVideoFragment extends BaseFragment implements RecyclerClickListner, AutoCompleteLocation.AutoCompleteLocationListener, ViewPagerEx.OnPageChangeListener  {


    @BindView(R.id.tabLayout_shortLong)
    TabLayout tabLayoutShortLong;
    @BindView(R.id.tabLayout_filter)
    TabLayout tabLayoutFilterFilter;
    @BindView(R.id.recyclerView)
    CustomRecyclerView recyclerView;
    Unbinder unbinder;
    ArrayList<BannerEntity> bannerEntityList = new ArrayList<>();

    public static DetailVideoFragment newInstance() {
        return new DetailVideoFragment();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.layout_videos, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        setDecInit();
        getDataFromServer();
    }
    public void setDecInit(){
        setTabLayout();
        tabLayoutFilteristner();
        setTabLayoutShortLong();
        tabLayoutShortLong();

    }
    private void setTabLayoutShortLong() {

        if (tabLayoutShortLong != null) {
            tabLayoutShortLong.removeAllTabs();
            tabLayoutShortLong.addTab(tabLayoutShortLong.newTab().setText(getString(R.string.tb_long)));
            tabLayoutShortLong.addTab(tabLayoutShortLong.newTab().setText(getString(R.string.tb_short)));
            TabLayout.Tab tab = tabLayoutShortLong.getTabAt(0);
            tab.select();
            setDataShortLong(tab);


        }
    }

    private void setTabLayout() {

        if (tabLayoutFilterFilter != null) {
            tabLayoutFilterFilter.removeAllTabs();
            tabLayoutFilterFilter.addTab(tabLayoutFilterFilter.newTab().setText(getString(R.string.tb_most_show)));
            tabLayoutFilterFilter.addTab(tabLayoutFilterFilter.newTab().setText(getString(R.string.tb_most_recent)));
            tabLayoutFilterFilter.addTab(tabLayoutFilterFilter.newTab().setText(getString(R.string.tb_most_likes)));
            tabLayoutFilterFilter.addTab(tabLayoutFilterFilter.newTab().setText(getString(R.string.tb_most_views)));

            TabLayout.Tab tab = tabLayoutFilterFilter.getTabAt(0);
            tab.select();
            setData(tab);


        }
    }

    private void tabLayoutFilteristner() {

        tabLayoutShortLong.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                setDataShortLong(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void tabLayoutShortLong() {

        tabLayoutFilterFilter.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                setData(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setData(TabLayout.Tab tab) {

        if (tab.getPosition() == 0) {
            serviceHelper.enqueueCall(headerWebService.getVideos("most_shared_videos"), WebServiceConstants.VIDEOS);
        } else if (tab.getPosition() == 1) {
            serviceHelper.enqueueCall(headerWebService.getVideos("most_recent_videos"), WebServiceConstants.VIDEOS);
        } else if (tab.getPosition() == 2) {
            serviceHelper.enqueueCall(headerWebService.getVideos("most_liked_videos"), WebServiceConstants.VIDEOS);
        } else if (tab.getPosition() == 3) {
            serviceHelper.enqueueCall(headerWebService.getVideos("most_viewed_videos"), WebServiceConstants.VIDEOS);
        }
    }
    private void setDataShortLong(TabLayout.Tab tab) {

        if (tab.getPosition() == 0) {
        } else if (tab.getPosition() == 1) {
        }
    }
    public void setAdapter(ArrayList<VideoInnerData> listData) {
        LinearLayoutManager lm;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);


      //  lm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,2, false);
        recyclerView.BindRecyclerView(new DetailVideoBinder(getDockActivity(), prefHelper, this), listData, gridLayoutManager, new DefaultItemAnimator());


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(Object entity, int position) {

    }

    @Override
    public void onTextClear() {

    }

    @Override
    public void onItemSelected(Place selectedPlace) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void getDataFromServer() {
        serviceHelper.enqueueCall(headerWebService.getVideos(), WebServiceConstants.VIDEOS);




    }
    @Override
    public void ResponseSuccess(Object result, String Tag) {
        super.ResponseSuccess(result, Tag);
        switch (Tag){
            case WebServiceConstants.VIDEOS:
                Data<VideoInnerData> dataVideo =(Data)result;
                ArrayList<VideoInnerData> videoInnerData = dataVideo.getData();
                setAdapter(videoInnerData);
                break;
        }
    }
}
