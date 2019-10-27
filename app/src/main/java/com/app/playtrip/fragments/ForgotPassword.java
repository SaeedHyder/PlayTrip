package com.app.playtrip.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.playtrip.R;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ForgotPassword extends BaseFragment {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    Unbinder unbinder;
    @BindView(R.id.btnForgotPass)
    Button btnForgotPass;

    public static ForgotPassword newInstance() {
        return new ForgotPassword();
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
        View view = inflater.inflate(R.layout.layout_forget_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    private boolean isValidated() {
        if (edtEmail.getText() == null || (edtEmail.getText().toString().isEmpty()) ||
                !(Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())) {
            edtEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else {
            return true;
        }
    }



    @OnClick(R.id.btnForgotPass)
    public void onViewClicked() {
        if(isValidated()){
            getDockActivity().replaceDockableFragment(ResetPasswordFragment.newInstance(),"ResetPasswordFragment");
        }

    }

}
