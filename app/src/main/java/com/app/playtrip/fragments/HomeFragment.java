package com.app.playtrip.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.helpers.UIHelper;
import com.app.playtrip.ui.adapters.RecyclerViewAdapter;
import com.app.playtrip.ui.adapters.RecyclerViewAdapterHomeBottom;
import com.app.playtrip.ui.adapters.RecyclerViewAdapterHomeMiddle;
import com.app.playtrip.ui.adapters.RecyclerViewAdapterHomeTop;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.playtrip.ui.views.AutoCompleteLocation;
import com.app.playtrip.ui.views.TitleBar;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.facebook.FacebookSdk.getApplicationContext;


public class HomeFragment extends BaseFragment implements AutoCompleteLocation.AutoCompleteLocationListener, ViewPagerEx.OnPageChangeListener {


    @BindView(R.id.sliderLayout)
    SliderLayout mSlider;
    @BindView(R.id.rv_topList) RecyclerView recyclerViewTop;
    @BindView(R.id.rv_middleList) RecyclerView recyclerViewMiddle;
    @BindView(R.id.rv_lastList) RecyclerView recyclerViewBottom;
    Map<String,Integer> sliderImages;
    ArrayList<BannerEntity> bannerEntityList =new ArrayList<>();
    RecyclerViewAdapterHomeTop mAdapter;
    RecyclerViewAdapterHomeMiddle mAdapterMiddle;
    RecyclerViewAdapterHomeBottom mAdapterBottom;


    RecyclerViewAdapter recyclerViewAdapterMiddle;
    private RecyclerViewBinder<BannerEntity> viewBinder;



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        getImages();
        loadBannerSliderImage(bannerEntityList,"Play Trp");
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
        UIHelper.showShortToastInCenter(getDockActivity(),selectedPlace.getLatLng().latitude + "  :  " + selectedPlace.getLatLng().longitude);
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
    public void getImages(){
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setTabPosterPath(R.drawable.bg);
        bannerEntityList.add(bannerEntity);

    }
    public void loadBannerSliderImage(List<BannerEntity> bannerEntityList, String bannerImage) {
        mSlider.removeAllSliders();
        boolean isMultiple;
        DefaultSliderView textSliderView;
        if (bannerEntityList != null && bannerEntityList.size() > 0) {

            for (int i = 0; i < bannerEntityList.size(); i++) {
                BannerEntity bannerEntity = bannerEntityList.get(i);
                textSliderView = new DefaultSliderView(getContext());
                textSliderView
                        .image(bannerEntity.getTabPosterPath());

               /* textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putParcelable(Constants.INFO_BANNER_VIDEO_DATA, bannerEntity);*/
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {

                    }
                });

                mSlider.addSlider(textSliderView);
            }
            isMultiple = true;
        } else {
            if (bannerImage != null && !bannerImage.isEmpty()) {
                textSliderView = new DefaultSliderView(getContext());
                textSliderView.image(bannerImage);

                mSlider.addSlider(textSliderView);

            }

            isMultiple = false;
        }


        mSlider.setDuration(4000);
        //set the speed of animation
//        mSlider.setSliderTransformDuration(3000, null);

        if (!isMultiple) {
            mSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
            mSlider.stopAutoCycle();
            textSliderView = new DefaultSliderView(getContext());
            textSliderView.image(bannerImage);
            mSlider.addSlider(textSliderView);
            mSlider.setPagerTransformer(false, new BaseTransformer() {
                @Override
                protected void onTransform(View view, float v) {
                }
            });
        } else {
            mSlider.startAutoCycle();
            mSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
            mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Top);
            mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        }
    }
    public void setAdapter(){

        mAdapter = new RecyclerViewAdapterHomeTop(getActivity(), bannerEntityList);
        recyclerViewTop.setAdapter(mAdapter);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTop.setLayoutManager(layoutManager);
        recyclerViewTop.setHasFixedSize(true);

        mAdapterMiddle = new RecyclerViewAdapterHomeMiddle(getActivity(), bannerEntityList);
        recyclerViewMiddle.setAdapter(mAdapterMiddle);
         layoutManager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMiddle.setLayoutManager(layoutManager);
        recyclerViewMiddle.setHasFixedSize(true);

        mAdapterBottom = new RecyclerViewAdapterHomeBottom(getActivity(), bannerEntityList);
        recyclerViewBottom.setAdapter(mAdapterBottom);
         layoutManager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBottom.setLayoutManager(layoutManager);
        recyclerViewBottom.setHasFixedSize(true);


    }

}

