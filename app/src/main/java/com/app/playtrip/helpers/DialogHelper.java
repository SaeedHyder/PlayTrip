package com.app.playtrip.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import com.app.playtrip.R;
import com.app.playtrip.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created on 5/24/2017.
 */

public class DialogHelper {
    private Dialog dialog;
    private Context context;
    private ImageLoader imageLoader;
    private RadioGroup rg;


    public DialogHelper(Context context) {
        this.context = context;
        this.dialog = new Dialog(context);
    }


   /* public Dialog toastDialoge(String text) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(R.layout.toast_dialoge);
        AnyTextView textView = (AnyTextView) dialog.findViewById(R.id.txt_text);
        textView.setText(text);
        return this.dialog;
    }*/

      public Dialog initlogout(View.OnClickListener yesListner, View.OnClickListener noListner) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(R.layout.dialoge_logout);
        Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(yesListner);
        Button btnNo = (Button) dialog.findViewById(R.id.btn_No);
        btnNo.setOnClickListener(noListner);
        return this.dialog;
    }

    public Dialog commonDialoge(View.OnClickListener yesListner, String Title, String Description) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(R.layout.dialoge_logout);
        Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);
        AnyTextView txtTitle = (AnyTextView) dialog.findViewById(R.id.txt_Logout);
        AnyTextView txtDescription = (AnyTextView) dialog.findViewById(R.id.txt_logout_text);
        btnYes.setOnClickListener(yesListner);
        txtTitle.setText(Title);
        txtDescription.setText(Description);
        Button btnNo = (Button) dialog.findViewById(R.id.btn_No);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        return this.dialog;
    }




    public void showDialog() {

        dialog.show();
    }

    public void setCancelable(boolean isCancelable) {
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelable);
    }

    public void hideDialog() {
        dialog.dismiss();
    }
}
