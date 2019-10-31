package com.app.playtrip.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.helpers.PermissionHelper;
import com.app.playtrip.helpers.UIHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.binders.MakeVideoBinder;
import com.app.playtrip.ui.views.AutoCompleteLocation;
import com.app.playtrip.ui.views.CustomRecyclerView;
import com.app.playtrip.ui.views.TitleBar;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.location.places.Place;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Orientation;
import id.zelory.compressor.Compressor;

import static com.app.playtrip.global.WebServiceConstants.LOGIN;


public class MakeVideosFragment extends BaseFragment implements RecyclerClickListner, AutoCompleteLocation.AutoCompleteLocationListener, ViewPagerEx.OnPageChangeListener {


    ArrayList<BannerEntity> bannerEntityList = new ArrayList<>();
    @BindView(R.id.recyclerView)
    CustomRecyclerView recyclerView;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_uploadPic)
    TextView tvUploadPic;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_subTitle)
    EditText etSubTitle;
    @BindView(R.id.et_caption)
    EditText etCaption;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.btn_publish)
    Button btnPublish;


    private int MAX_ATTACHMENT_COUNT = 1;
    private final int PROFILE_IMAGE_KEY = 223;
    private final int PROFILE_IMAGE_BANNER_KEY = 552;
    Bitmap image = null;

    private ArrayList<String> photoPaths = new ArrayList<>();


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
    @OnClick({R.id.tv_uploadPic,R.id.btn_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_uploadPic:
                cameraPermission(PROFILE_IMAGE_KEY);
                break;
            case R.id.btn_publish:
                serviceHelper.enqueueCall(headerWebService.uploadVideo("Title","simole","5","5", String.valueOf(image), String.valueOf(image),"3","2"),LOGIN);
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
        photoPaths = new ArrayList<>();
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
                    Glide.with(getDockActivity()).load(image).into(ivPhoto);

                }
                break;
            case PROFILE_IMAGE_BANNER_KEY:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths = new ArrayList<>();
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));


                    try {
                        image = new Compressor(getDockActivity()).setQuality(60).compressToBitmap(new File(photoPaths.get(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Glide.with(getDockActivity()).load(image).into(ivPhoto);

                }
                break;


        }
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



}

