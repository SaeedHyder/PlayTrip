package com.app.playtrip.helpers;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {

    private Context context;

    public PermissionHelper(Context context) {
        this.context=context;
    }

     public void grantPermission(String permissionType, final PermissionInterface permissionInterface){
        Dexter.withActivity((Activity) context)
                .withPermission(permissionType)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        permissionInterface.onSuccess();
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        permissionInterface.onError();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(error -> openSettings()).onSameThread().check();

    }

    public void grantMultiplePermissions(ArrayList<String> permissionTypes, final PermissionInterface permissionInterface) {
        Dexter.withActivity((Activity) context)
                .withPermissions(permissionTypes)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        if (report.areAllPermissionsGranted()) {
                            permissionInterface.onSuccess();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            permissionInterface.onError();
                        } else if (report.getDeniedPermissionResponses().size() > 0) {
                            permissionInterface.onError();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(error -> openSettings()).onSameThread().check();


    }

     void openSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
    public interface PermissionInterface {

        void onSuccess();
        void onError();
    }


}
