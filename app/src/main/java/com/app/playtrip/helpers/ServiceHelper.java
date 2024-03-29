package com.app.playtrip.helpers;

import android.util.Log;


import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.User.DataUser;
import com.app.playtrip.entities.Wrapper.ResponseSimple;
import com.app.playtrip.entities.Wrapper.ResponseWrapper;
import com.app.playtrip.global.WebServiceConstants;
import com.app.playtrip.interfaces.webServiceResponseLisener;
import com.app.playtrip.retrofit.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 7/17/2017.
 */

public class ServiceHelper<T> {
    private webServiceResponseLisener serviceResponseLisener;
    private DockActivity context;
    private WebService webService;

    public ServiceHelper(webServiceResponseLisener serviceResponseLisener, DockActivity conttext, WebService webService) {
        this.serviceResponseLisener = serviceResponseLisener;
        this.context = conttext;
        this.webService = webService;
    }

    public void enqueueCall(Call<ResponseWrapper<T>> call, final String tag) {
        if (InternetHelper.CheckInternetConectivityandShowToast(context)) {
            context.onLoadingStarted();
            call.enqueue(new Callback<ResponseWrapper<T>>() {
                @Override
                public void onResponse(Call<ResponseWrapper<T>> call, Response<ResponseWrapper<T>> response) {
                    context.onLoadingFinished();
                    if (response != null && response.body() != null && response.code() == 200) {
                        if (response.body().getSuccess()) {
                            serviceResponseLisener.ResponseSuccess(response.body().getData(), tag);
                        } else {
                            serviceResponseLisener.ResponseFailure(tag);
                            UIHelper.showShortToastInCenter(context, response.body().getMessage());
                        }
                    } else {
                        serviceResponseLisener.ResponseFailure(tag);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            UIHelper.showShortToastInCenter(context, jObjError.get("message").toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseWrapper<T>> call, Throwable t) {
                    context.onLoadingFinished();
                    serviceResponseLisener.ResponseFailure(tag);
                    t.printStackTrace();
                    Log.e(ServiceHelper.class.getSimpleName() + " by tag: " + tag, t.toString());
                }
            });
        }
    }
    public void enqueueCallDel(Call<ResponseSimple> call, final String tag) {
        if (InternetHelper.CheckInternetConectivityandShowToast(context)) {
            context.onLoadingStarted();
            call.enqueue(new Callback<ResponseSimple>() {
                @Override
                public void onResponse(Call<ResponseSimple> call, Response<ResponseSimple> response) {
                    context.onLoadingFinished();
                    if (response != null && response.body() != null && response.code() == 200) {
                        if (response.body().isSuccess()) {
                            serviceResponseLisener.ResponseSuccess(response.body().getMessage(), tag);
                        } else {
                            serviceResponseLisener.ResponseFailure(tag);
                            UIHelper.showShortToastInCenter(context, response.body().getMessage());
                        }
                    } else {
                        serviceResponseLisener.ResponseFailure(tag);
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            UIHelper.showShortToastInCenter(context, jObjError.get("message").toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseSimple> call, Throwable t) {
                    context.onLoadingFinished();
                    serviceResponseLisener.ResponseFailure(tag);
                    t.printStackTrace();
                    Log.e(ServiceHelper.class.getSimpleName() + " by tag: " + tag, t.toString());
                }
            });
        }
    }




}
