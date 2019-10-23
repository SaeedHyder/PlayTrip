package com.app.playtrip.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.app.playtrip.entities.User.User;
import com.app.playtrip.retrofit.GsonFactory;


public class BasePreferenceHelper extends PreferenceHelper {

    private Context context;

    protected static final String KEY_LOGIN_STATUS = "islogin";

    private static final String FILENAME = "preferences";

    protected static final String Firebase_TOKEN = "Firebasetoken";

    protected static final String NotificationCount = "NotificationCount";
    protected static final String KEY_USER = "KEY_USER";
    protected static final String TOKEN = "TOKEN";


    public BasePreferenceHelper(Context c) {
        this.context = c;
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
    }

    public void setLoginStatus( boolean isLogin ) {
        putBooleanPreference( context, FILENAME, KEY_LOGIN_STATUS, isLogin );
    }

    public boolean isLogin() {
        return getBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS);
    }


    public String getFirebase_TOKEN() {
        return getStringPreference(context, FILENAME, Firebase_TOKEN);
    }

    public void setFirebase_TOKEN(String _token) {
        putStringPreference(context, FILENAME, Firebase_TOKEN, _token);
    }
    public int getNotificationCount() {
        return getIntegerPreference(context, FILENAME, NotificationCount);
    }

    public void setNotificationCount(int count) {
        putIntegerPreference(context, FILENAME, NotificationCount, count);
    }

    public String get_TOKEN() {
        return getStringPreference(context, FILENAME, TOKEN);
    }

    public void set_TOKEN(String token) {
        putStringPreference(context, FILENAME, TOKEN, token);
    }

    public User getUser() {
        return GsonFactory.getConfiguredGson().fromJson(getStringPreference(context, FILENAME, KEY_USER), User.class);
    }

    public void putUser(User user) {
        putStringPreference(context, FILENAME, KEY_USER, GsonFactory.getConfiguredGson().toJson(user));
    }

}
