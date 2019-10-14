package com.app.playtrip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.playtrip.R;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends BaseFragment implements OnClickListener {

    @BindView(R.id.btn_login)
    Button loginButton;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
      ButterKnife.bind(this, view);
        setListeners();

    }

    private void setListeners() {
        loginButton.setOnClickListener(this);
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
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_login:
                prefHelper.setLoginStatus(true);
                getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), "HomeFragmnet");
                break;
        }
    }

}
