package com.app.playtrip.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.entities.Data;
import com.app.playtrip.entities.banners.BannersInnerData;
import com.app.playtrip.entities.trending.TrendingEntity;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.entities.video.VideoTranslations;
import com.app.playtrip.fragments.Profile.ProfileFragment;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.AppConstants;
import com.app.playtrip.global.WebServiceConstants;
import com.app.playtrip.helpers.UIHelper;
import com.app.playtrip.interfaces.RecycleHomeClickListner;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.adapters.CustomSpinnerAdapter;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.app.playtrip.activities.DockActivity.KEY_FRAG_FIRST;


public class HomeFragment extends BaseFragment implements RecycleHomeClickListner, AutoCompleteLocation.AutoCompleteLocationListener, ViewPagerEx.OnPageChangeListener {


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
    @BindView(R.id.btnProfile)
    ImageView btnProfile;
    @BindView(R.id.btnViewMoreUser)
    TextView btnViewMoreUser;
    @BindView(R.id.spinner)
    Spinner spinner;

    String[] spinnerArray = {"Most view","Most recent","Most shows","Most likes"};

    ArrayList<VideoInnerData> videoInnerData = new ArrayList<>();
    ArrayList<VideoTranslations> videoInnerlist =new ArrayList<>();
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        getDataFromServer();
        VideoTranslations videoTranslations = new VideoTranslations();
        VideoInnerData videoInnerData1 = new VideoInnerData();
        videoTranslations.setCaption("fdsfsdfd");
        videoInnerlist.add(videoTranslations);
        videoInnerData1.setTranslations(videoInnerlist);
        videoInnerData1.setTitle("ali");
        videoInnerData.add(videoInnerData1);

        //setAdapter(videoInnerData);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinner();

    }

    private void setSpinner() {

        spinner.setAdapter(new CustomSpinnerAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item,spinnerArray,"Most View"));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos==0){
                    serviceHelper.enqueueCall(headerWebService.getVideos("most_viewed_videos"), WebServiceConstants.VIDEOS);
                }else if(pos==1){
                    serviceHelper.enqueueCall(headerWebService.getVideos("most_recent_videos"), WebServiceConstants.VIDEOS);
                }else if(pos==2){
                    serviceHelper.enqueueCall(headerWebService.getVideos("most_shared_videos"), WebServiceConstants.VIDEOS);
                }else if(pos==3){
                    serviceHelper.enqueueCall(headerWebService.getVideos("most_liked_videos"), WebServiceConstants.VIDEOS);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    public void getDataFromServer() {
        serviceHelper.enqueueCall(headerWebService.getVideos(), WebServiceConstants.VIDEOS);
        serviceHelper.enqueueCall(headerWebService.getBanners(), WebServiceConstants.BANNERS);
        serviceHelper.enqueueCall(headerWebService.getTrendingVideos(AppConstants.Recent), WebServiceConstants.TRENDING);



    }

    public  void loadBannerSliderImage(ArrayList<BannersInnerData> bannerEntityList, String bannerImage) {
        mSlider.removeAllSliders();
        boolean isMultiple;
        DefaultSliderView textSliderView;
        if (bannerEntityList != null && bannerEntityList.size() > 0) {

            for (int i = 0; i < bannerEntityList.size(); i++) {
                BannersInnerData bannerEntity = bannerEntityList.get(i);
                textSliderView = new DefaultSliderView(getActivity());
                textSliderView
                        .image(bannerEntity.getImage_url());
              //  Log.e("dataV",bannerEntity.getImage_url().toString());

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

    public void setAdapter(ArrayList<VideoInnerData> videoInnerData) {



        LinearLayoutManager lmTopList, lmMiddleList, lmBottomList;

        lmTopList = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
      //  recyclerViewTop.BindRecyclerView(new HomeTopBinder(getDockActivity(), prefHelper, this), , lmTopList, new DefaultItemAnimator());
        lmMiddleList = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewMiddle.BindRecyclerView(new HomeMiddleBinder(getDockActivity(), prefHelper, this), videoInnerData, lmMiddleList, new DefaultItemAnimator());



    }


    @OnClick({R.id.tv_viewMore,R.id.btnProfile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_viewMore:
                replaceMainFragment(DetailVideoFragment.newInstance());
                break;
            case R.id.btnProfile:
                replaceMainFragment(ProfileFragment.newInstance(prefHelper.getUser().getId()+""));
                break;

        }
    }
    @Override
    public void ResponseSuccess(Object result, String Tag) {
        super.ResponseSuccess(result, Tag);
        switch (Tag){
            case WebServiceConstants.BANNERS:
                Data<BannersInnerData> data=(Data)result;
                ArrayList<BannersInnerData> bannerEntities = data.getData();
                loadBannerSliderImage(bannerEntities, "Play Trp");


                break;
            case WebServiceConstants.VIDEOS:
                Data<VideoInnerData> dataVideo =(Data)result;
                ArrayList<VideoInnerData> videoInnerData = dataVideo.getData();
                setAdapter(videoInnerData);
                break;

            case WebServiceConstants.TRENDING:
                Data<TrendingEntity> dataTrendVideo =(Data)result;
                ArrayList<TrendingEntity> trendingData = dataTrendVideo.getData();
                setUserBottomData(trendingData);
                break;


        }
    }

    private void setUserBottomData(ArrayList<TrendingEntity> trendingData){
        LinearLayoutManager lmBottomList = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBottom.BindRecyclerView(new HomeBottomBinder(getDockActivity(), prefHelper, this), trendingData, lmBottomList, new DefaultItemAnimator());
    }

    @Override
    public void onClick(Object entity, int position, String key) {

        switch (key){
            case "User":
                TrendingEntity ent=(TrendingEntity)entity;
                replaceMainFragment(ProfileFragment.newInstance(ent.getId()+""));
                break;
        }
    }
}

