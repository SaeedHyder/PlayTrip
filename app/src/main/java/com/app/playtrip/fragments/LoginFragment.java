package com.app.playtrip.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.entities.User.DataUser;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.AppConstants;
import com.app.playtrip.global.WebServiceConstants;
import com.app.playtrip.helpers.ServiceHelper;
import com.app.playtrip.ui.views.TitleBar;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.playtrip.global.WebServiceConstants.LOGIN;


public class LoginFragment extends BaseFragment {

    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.rl_card_view)
    LinearLayout rlCardView;
    @BindView(R.id.btn_fb)
    ImageView btnFb;
    @BindView(R.id.btn_google)
    ImageView btnGoogle;
    @BindView(R.id.btnRegister)
    TextView btnRegister;
    @BindView(R.id.btnForgotPass)
    TextView btnForgotPass;


    public static LoginFragment newInstance() {
        return new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;

    }



    private boolean isValidated() {
        if (edtEmail.getText() == null || (edtEmail.getText().toString().isEmpty()) ||
                !(Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())) {
            edtEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtPassword.getText().toString().length() < 6) {
            edtPassword.setError(getString(R.string.passwordLength));
            return false;
        } else {
            return true;
        }
    }


    @OnClick({R.id.btn_login, R.id.btn_fb, R.id.btn_google, R.id.btnRegister, R.id.btnForgotPass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (isValidated()) {
                    serviceHelper.enqueueCall(webService.loginUser(edtEmail.getText().toString(),edtPassword.getText().toString(), AppConstants.Device_Type, FirebaseInstanceId.getInstance().getToken()), LOGIN);
                }
                break;
            case R.id.btn_fb:
                notImplemented();
                break;
            case R.id.btn_google:
                notImplemented();
                break;
            case R.id.btnRegister:
                getDockActivity().replaceDockableFragment(SignupFragment.newInstance(),"SignupFragment");
                break;
            case R.id.btnForgotPass:
                getDockActivity().replaceDockableFragment(ForgotPassword.newInstance(),"SignupFragment");
                break;
        }
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        super.ResponseSuccess(result, Tag);
        switch (Tag){
            case LOGIN:
                DataUser dataUser=(DataUser)result;
                prefHelper.setLoginStatus(true);
                prefHelper.putUser(dataUser.getUser());
                prefHelper.set_TOKEN(dataUser.getUser().getAccessToken());
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(MainFragment.newInstance(), "MainFragment");
                break;
        }
    }

}
