package com.app.playtrip.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.app.playtrip.R;
import com.app.playtrip.entities.User.DataUser;
import com.app.playtrip.fragments.abstracts.BaseFragment;
import com.app.playtrip.global.AppConstants;
import com.app.playtrip.global.WebServiceConstants;
import com.app.playtrip.ui.views.TitleBar;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignupFragment extends BaseFragment {

    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConPassword)
    EditText edtConPassword;
    @BindView(R.id.cbTerms)
    CheckBox cbTerms;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    Unbinder unbinder;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
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
        View view = inflater.inflate(R.layout.layout_registration, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    private boolean isvalidated() {
        if (edtUsername.getText().toString().trim().isEmpty() || edtUsername.getText().toString().trim().length() < 3) {
            edtUsername.setError(getString(R.string.enter_fullname));
            if (edtUsername.requestFocus()) {
                setEditTextFocus(edtUsername);
            }
            return false;
        } else if (edtEmail.getText() == null || edtEmail.getText().toString().trim().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
            edtEmail.setError(getString(R.string.enter_valid_email));
            if (edtEmail.requestFocus()) {
                setEditTextFocus(edtEmail);
            }
            return false;
        } else if (edtPassword.getText().toString().trim().isEmpty()) {
            edtPassword.setError(getString(R.string.enter_password));
            if (edtPassword.requestFocus()) {
                setEditTextFocus(edtPassword);
            }
            return false;
        } else if (edtPassword.getText().toString().trim().length() < 6) {
            edtPassword.setError(getString(R.string.passwordLength));
            if (edtPassword.requestFocus()) {
                setEditTextFocus(edtPassword);
            }
            return false;
        } else if (edtConPassword.getText().toString().trim().isEmpty()) {
            edtConPassword.setError(getString(R.string.enter_password));
            if (edtConPassword.requestFocus()) {
                setEditTextFocus(edtConPassword);
            }
            return false;
        } else if (!edtConPassword.getText().toString().trim().equals(edtPassword.getText().toString().trim())) {
            edtConPassword.setError(getString(R.string.confirm_password_error));
            if (edtConPassword.requestFocus()) {
                setEditTextFocus(edtConPassword);
            }
            return false;
        } else
            return true;

    }

    @OnClick(R.id.btnRegister)
    public void onViewClicked() {
        if(isvalidated()){
            serviceHelper.enqueueCall(webService.signup(edtUsername.getText().toString(),edtUsername.getText().toString(),edtEmail.getText().toString(),edtPassword.getText().toString()
            ,edtConPassword.getText().toString(), FirebaseInstanceId.getInstance().getToken(), AppConstants.Device_Type), WebServiceConstants.SIGNUP);
        }
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        super.ResponseSuccess(result, Tag);
        switch (Tag){
            case WebServiceConstants.SIGNUP:
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
