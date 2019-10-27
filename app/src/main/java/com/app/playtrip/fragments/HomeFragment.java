package com.app.playtrip.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.AppConstants;
import com.app.playtrip.helpers.UIHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.binders.HomeBottomBinder;
import com.app.playtrip.ui.binders.HomeMiddleBinder;
import com.app.playtrip.ui.binders.HomeTopBinder;
import com.app.playtrip.ui.views.AutoCompleteLocation;
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.location.places.Place;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.app.playtrip.activities.DockActivity.KEY_FRAG_FIRST;
import static com.app.playtrip.global.WebServiceConstants.LOGIN;


public class HomeFragment extends BaseFragment implements RecyclerClickListner, AutoCompleteLocation.AutoCompleteLocationListener, ViewPagerEx.OnPageChangeListener {


    @BindView(R.id.sliderLayout)
    SliderLayout mSlider;
    @BindView(R.id.rv_topList)
    CustomRecyclerView recyclerViewTop;
    @BindView(R.id.rv_middleList)
    CustomRecyclerView recyclerViewMiddle;
    @BindView(R.id.rv_bottomList)
    CustomRecyclerView recyclerViewBottom;

    Map<String, Integer> sliderImages;
    ArrayList<BannerEntity> bannerEntityList = new ArrayList<>();
    @BindView(R.id.tv_viewMore)
    TextView tvViewMore;
    private FragmentManager manager;



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

    public void getImages() {
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setTabPosterPath(R.drawable.s23_img);
        bannerEntityList.add(bannerEntity);

        loadBannerSliderImage(bannerEntityList, "Play Trp");

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

    public void setAdapter() {
        LinearLayoutManager lmTopList, lmMiddleList, lmBottomList;

        lmTopList = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTop.BindRecyclerView(new HomeTopBinder(getDockActivity(), prefHelper, this), bannerEntityList, lmTopList, new DefaultItemAnimator());
        lmMiddleList = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewMiddle.BindRecyclerView(new HomeMiddleBinder(getDockActivity(), prefHelper, this), bannerEntityList, lmMiddleList, new DefaultItemAnimator());
        lmBottomList = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewBottom.BindRecyclerView(new HomeBottomBinder(getDockActivity(), prefHelper, this), bannerEntityList, lmBottomList, new DefaultItemAnimator());


    }

    @Override
    public void onClick(Object entity, int position) {

    }

    @OnClick({R.id.tv_viewMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_viewMore:
                replaceFragment(DetailVideoFragment.newInstance());
                break;

        }
    }
    public void replaceFragment(Fragment frag) {

        manager = getFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, frag);
        transaction.addToBackStack(manager.getBackStackEntryCount() == 1 ? KEY_FRAG_FIRST : null).commit();


    }
}

