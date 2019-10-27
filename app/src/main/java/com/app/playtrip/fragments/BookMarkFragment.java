package com.app.playtrip.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.binders.BookMarkBinder;
import com.app.playtrip.ui.binders.MakeVideoBinder;
import com.app.playtrip.ui.views.AutoCompleteLocation;
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookMarkFragment extends BaseFragment implements RecyclerClickListner, AutoCompleteLocation.AutoCompleteLocationListener, ViewPagerEx.OnPageChangeListener  {


    @BindView(R.id.recyclerView)
    CustomRecyclerView recyclerView;
    Unbinder unbinder;

    ArrayList<BannerEntity> bannerEntityList = new ArrayList<>();


    public static BookMarkFragment newInstance() {
        return new BookMarkFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
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
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);
        unbinder = ButterKnife.bind(this, view);
        setAdapter();
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void setAdapter() {
        LinearLayoutManager lm;

        lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.BindRecyclerView(new BookMarkBinder(getDockActivity(), prefHelper, this), bannerEntityList, lm, new DefaultItemAnimator());


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
}
