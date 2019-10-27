package com.app.playtrip.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.helpers.UIHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;

import com.app.playtrip.ui.binders.MakeVideoBinder;
import com.app.playtrip.ui.views.AutoCompleteLocation;
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;

import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MakeVideosFragment extends BaseFragment implements RecyclerClickListner, AutoCompleteLocation.AutoCompleteLocationListener, ViewPagerEx.OnPageChangeListener {


    ArrayList<BannerEntity> bannerEntityList = new ArrayList<>();
    @BindView(R.id.recyclerView)
    CustomRecyclerView recyclerView;


    public static MakeVideosFragment newInstance() {
        return new MakeVideosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_video, container, false);
        ButterKnife.bind(this, view);
        setAdapter();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ac_location.setAutoCompleteTextListener(this);

    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showMenuButton();
        titleBar.setSubHeading("Home");
        titleBar.showNotificationButton(0);

    }


    @Override
    public void onTextClear() {
    }

    @Override
    public void onItemSelected(Place selectedPlace) {
        UIHelper.showShortToastInCenter(getDockActivity(), selectedPlace.getLatLng().latitude + "  :  " + selectedPlace.getLatLng().longitude);
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



    public void setAdapter() {
        LinearLayoutManager lm;

        lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.BindRecyclerView(new MakeVideoBinder(getDockActivity(), prefHelper, this), bannerEntityList, lm, new DefaultItemAnimator());


    }

    @Override
    public void onClick(Object entity, int position) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

