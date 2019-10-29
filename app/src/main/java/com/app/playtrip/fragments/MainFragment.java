package com.app.playtrip.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.entities.Data;
import com.app.playtrip.entities.User.DataUser;
import com.app.playtrip.entities.banners.BannersInnerData;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.fragments.Profile.ProfileFragment;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.AppConstants;
import com.app.playtrip.global.WebServiceConstants;
import com.app.playtrip.helpers.UIHelper;
import com.app.playtrip.ui.views.TitleBar;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.playtrip.activities.DockActivity.KEY_FRAG_FIRST;
import static com.facebook.FacebookSdk.getApplicationContext;

public class MainFragment extends BaseFragment {

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private FragmentManager manager;

    public static MainFragment newInstance() {
        return new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_main, container, false);
         ButterKnife.bind(this, view);

        return view;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

        setTabLayout();
        tabLayoutistner();
    }

    private void setTabLayout() {

        if (tabLayout != null) {
            tabLayout.removeAllTabs();
            tabLayout.addTab(tabLayout.newTab().setIcon(getResDrawable(R.drawable.s2_home)));
            tabLayout.addTab(tabLayout.newTab().setIcon(getResDrawable(R.drawable.s2_bag)));
            tabLayout.addTab(tabLayout.newTab().setIcon(getResDrawable(R.drawable.s2_v)));
            tabLayout.addTab(tabLayout.newTab().setIcon(getResDrawable(R.drawable.s2_cart)));
            tabLayout.addTab(tabLayout.newTab().setIcon(getResDrawable(R.drawable.s2_c)));

            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();
            setData(tab);


        }
    }

    private void tabLayoutistner() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
            replaceFragment(HomeFragment.newInstance());
        } else if(tab.getPosition() == 1){

            UIHelper.showLongToastInCenter(getContext(),
                    R.string.message_coming_soon);
            //replaceFragment(ProfileFragment.newInstance());
        } else if(tab.getPosition() == 2){
           // serviceHelper.enqueueCall(headerWebService.getVideos(), WebServiceConstants.VIDEOS);
            UIHelper.showLongToastInCenter(getContext(),
                    R.string.message_coming_soon);
          //  replaceFragment(MakeVideosFragment.newInstance());
        } else if(tab.getPosition() == 3){
            UIHelper.showLongToastInCenter(getContext(),
                    R.string.message_coming_soon);
           // replaceFragment(BuyTicketsFragment.newInstance());
        } else if(tab.getPosition() == 4){
            UIHelper.showLongToastInCenter(getContext(),
                    R.string.message_coming_soon);
           // replaceFragment(BookMarkFragment.newInstance());
        }
    }
    public void replaceFragment(Fragment frag) {

        manager = getChildFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, frag);
        transaction.addToBackStack(manager.getBackStackEntryCount() == 0 ? KEY_FRAG_FIRST : null).commit();
    }


}
