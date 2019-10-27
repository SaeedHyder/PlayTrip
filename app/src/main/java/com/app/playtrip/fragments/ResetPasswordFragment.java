package com.app.playtrip.fragments;


import android.os.Bundle;
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

public class ResetPasswordFragment extends BaseFragment {

    @BindView(R.id.edt_old_password)
    EditText edtOldPassword;
    @BindView(R.id.edt_new_password)
    EditText edtNewPassword;
    @BindView(R.id.edt_confirpassword)
    EditText edtConfirpassword;
    @BindView(R.id.btnReset)
    Button btnReset;


    public static ResetPasswordFragment newInstance() {
        return new ResetPasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_reset_password, container, false);
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

    @OnClick(R.id.btnReset)
    public void onViewClicked() {
        if(isvalidate()){
            getDockActivity().popBackStackTillEntry(0);
            getDockActivity().replaceDockableFragment(LoginFragment.newInstance(),"LoginFragment");
        }
    }

    private boolean isvalidate() {
        if (edtOldPassword.getText() == null || (edtOldPassword.getText().toString().trim().isEmpty())) {
            edtOldPassword.setError(getString(R.string.enter_password));
            if (edtOldPassword.requestFocus()) {
                setEditTextFocus(edtOldPassword);
            }
            return false;
        } else if (edtNewPassword.getText() == null || (edtNewPassword.getText().toString().trim().isEmpty())) {
            edtNewPassword.setError(getString(R.string.enter_password));
            if (edtNewPassword.requestFocus()) {
                setEditTextFocus(edtNewPassword);
            }
            return false;
        } else if (edtNewPassword.getText().toString().equals(edtOldPassword.getText().toString())) {
            edtNewPassword.setError(getString(R.string.samePassword));
            if (edtNewPassword.requestFocus()) {
                setEditTextFocus(edtNewPassword);
            }
            return false;
        } else if (edtNewPassword.getText().toString().trim().length() < 6) {
            edtNewPassword.setError(getString(R.string.passwordLength));
            if (edtNewPassword.requestFocus()) {
                setEditTextFocus(edtNewPassword);
            }
            return false;
        } else if (edtConfirpassword.getText() == null || (edtConfirpassword.getText().toString().trim().isEmpty())) {
            edtConfirpassword.setError(getString(R.string.enter_confirm_password));
            if (edtConfirpassword.requestFocus()) {
                setEditTextFocus(edtConfirpassword);
            }
            return false;
        } else if (edtConfirpassword.getText().toString().trim().length() < 6) {
            edtConfirpassword.setError(getString(R.string.confirmpasswordLength));
            if (edtConfirpassword.requestFocus()) {
                setEditTextFocus(edtConfirpassword);
            }
            return false;
        } else if (!edtConfirpassword.getText().toString().trim().equals(edtNewPassword.getText().toString().trim())) {
            edtConfirpassword.setError(getString(R.string.conform_password_error));
            if (edtConfirpassword.requestFocus()) {
                setEditTextFocus(edtConfirpassword);
            }
            return false;
        } else {
            return true;
        }
    }
}