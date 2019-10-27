package com.app.playtrip.fragments.Profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.playtrip.R;
import com.app.playtrip.fragments.HomeFragment;
import com.app.playtrip.fragments.SettingsFragment;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.helpers.PermissionHelper;
import com.app.playtrip.ui.views.TitleBar;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Orientation;
import id.zelory.compressor.Compressor;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.app.playtrip.activities.DockActivity.KEY_FRAG_FIRST;
import static droidninja.filepicker.FilePickerConst.REQUEST_CODE_DOC;
import static droidninja.filepicker.FilePickerConst.REQUEST_CODE_PHOTO;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.ivProfileBannerImg)
    ImageView ivProfileBannerImg;
    @BindView(R.id.ivProfileImage)
    CircleImageView ivProfileImage;
    @BindView(R.id.btnCamera)
    ImageView btnCamera;
    @BindView(R.id.btnCameraBanner)
    ImageView btnCameraBanner;
    @BindView(R.id.txtUsername)
    TextView txtUsername;
    @BindView(R.id.btnSetting)
    TextView btnSetting;
    @BindView(R.id.frame_layout_ivBannerImage)
    FrameLayout frameLayoutIvBannerImage;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private int MAX_ATTACHMENT_COUNT = 1;
    private final int PROFILE_IMAGE_KEY = 223;
    private final int PROFILE_IMAGE_BANNER_KEY = 552;

    private ArrayList<String> photoPaths = new ArrayList<>();


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_personal_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTabLayout();
        tabLayoutistner();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    @OnClick({R.id.btnCamera, R.id.btnCameraBanner, R.id.btnSetting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCamera:
                cameraPermission(PROFILE_IMAGE_KEY);
                break;
            case R.id.btnCameraBanner:
                cameraPermission(PROFILE_IMAGE_BANNER_KEY);
                break;
            case R.id.btnSetting:
                replaceMainFragment(SettingsFragment.newInstance());
                break;
        }
    }

    private void cameraPermission(int key) {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(Manifest.permission.CAMERA);
        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        PermissionHelper permissionHelper = new PermissionHelper(getDockActivity());
        permissionHelper.grantMultiplePermissions(permissionList, new PermissionHelper.PermissionInterface() {
            @Override
            public void onSuccess() {
                onPickPhoto(key);
            }

            @Override
            public void onError() {
                cameraPermission(key);
            }
        });
    }

    public void onPickPhoto(int key) {

        String[] images = {".jpg,.png,.jpeg"};
        photoPaths=new ArrayList<>();
        int maxCount = MAX_ATTACHMENT_COUNT;
        if ((photoPaths.size()) == MAX_ATTACHMENT_COUNT) {
            Toast.makeText(getDockActivity(), "Cannot select more than " + MAX_ATTACHMENT_COUNT + " items", Toast.LENGTH_SHORT).show();
        } else {
            FilePickerBuilder.getInstance()
                    .setMaxCount(maxCount)
                    .setSelectedFiles(photoPaths)
                    .setActivityTheme(R.style.LibAppTheme)
                    .setActivityTitle("Please select media")
                    .enableVideoPicker(false)
                    .addFileSupport("Images", images)
                    .enableCameraSupport(true)
                    .showGifs(false)
                    .showFolderView(false)
                    .enableSelectAll(false)
                    .enableImagePicker(true)
                    .setCameraPlaceholder(R.drawable.custom_camera)
                    .withOrientation(Orientation.UNSPECIFIED)
                    .pickPhoto(this, key);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PROFILE_IMAGE_KEY:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths = new ArrayList<>();
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));

                    Bitmap image = null;
                    try {
                        image = new Compressor(getDockActivity()).setQuality(60).compressToBitmap(new File(photoPaths.get(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Glide.with(getDockActivity()).load(image).into(ivProfileImage);

                }
                break;
            case PROFILE_IMAGE_BANNER_KEY:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths = new ArrayList<>();
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));

                    Bitmap image = null;
                    try {
                        image = new Compressor(getDockActivity()).setQuality(60).compressToBitmap(new File(photoPaths.get(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Glide.with(getDockActivity()).load(image).into(ivProfileBannerImg);

                }
                break;


        }
    }


    private void setTabLayout() {

        if (tabLayout != null) {
            tabLayout.removeAllTabs();
            tabLayout.addTab(tabLayout.newTab().setText(getResString(R.string.videos)));
            tabLayout.addTab(tabLayout.newTab().setText(getResString(R.string.followed)));
            tabLayout.addTab(tabLayout.newTab().setText(getResString(R.string.following)));

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
            replaceFragmentProfile(VideosFragment.newInstance());
        } else if (tab.getPosition() == 1) {
            replaceFragmentProfile(FollowedFragment.newInstance());
        } else if (tab.getPosition() == 2) {
            replaceFragmentProfile(FollowingFragment.newInstance());
        }
    }

    public void replaceFragmentProfile(Fragment frag) {

        FragmentManager manager = getChildFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainerProfile, frag);
        transaction.addToBackStack(manager.getBackStackEntryCount() == 0 ? KEY_FRAG_FIRST : null).commit();


    }
}