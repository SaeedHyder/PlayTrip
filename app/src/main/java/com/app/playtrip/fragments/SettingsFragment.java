package com.app.playtrip.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.helpers.DialogHelper;
import com.app.playtrip.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingsFragment extends BaseFragment {

    @BindView(R.id.btnChangePassword)
    TextView btnChangePassword;
    @BindView(R.id.btnTermsCond)
    TextView btnTermsCond;
    @BindView(R.id.btnPrivacyPolicy)
    TextView btnPrivacyPolicy;
    @BindView(R.id.btnLogout)
    Button btnLogout;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }



    @OnClick({R.id.btnChangePassword, R.id.btnTermsCond, R.id.btnPrivacyPolicy, R.id.btnLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnChangePassword:
                notImplemented();
                break;
            case R.id.btnTermsCond:
                notImplemented();
                break;
            case R.id.btnPrivacyPolicy:
                notImplemented();
                break;
            case R.id.btnLogout:


                DialogHelper dialoge = new DialogHelper(getDockActivity());
                dialoge.initlogout(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDockActivity().popBackStackTillEntry(0);
                        prefHelper.setLoginStatus(false);
                        getDockActivity().replaceDockableFragment(LoginFragment.newInstance(),"LoginFragment");
                        dialoge.hideDialog();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialoge.hideDialog();
                    }
                });
                dialoge.showDialog();

                break;
        }
    }
}