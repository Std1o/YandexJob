package com.yandex.job;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private static final String PREF_NAME = "yandexJobPref";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_USER_PHOTO = "userPhoto";
    private static final String KEY_LOGGED_IN = "loggedIn";
    private static final String KEY_IS_ACCEPTED = "isAccepted";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getId() {
        return pref.getString(KEY_ID, "");
    }

    public void setId(String value) {
        editor.putString(KEY_ID, value);
        editor.apply();
    }

    //----------------------------
    public String getName() {
        return pref.getString(KEY_NAME, "");
    }

    public void setName(String value) {
        editor.putString(KEY_NAME, value);
        editor.apply();
    }

    //----------------------------
    public String getLastName() {
        return pref.getString(KEY_LAST_NAME, "");
    }

    public void setLastName(String value) {
        editor.putString(KEY_LAST_NAME, value);
        editor.apply();
    }

    public String getUserPhoto() {
        return pref.getString(KEY_USER_PHOTO, "");
    }

    public void setUserPhoto(String value) {
        editor.putString(KEY_USER_PHOTO, value);
        editor.apply();
    }

    public boolean getLoggedIn() {
        return pref.getBoolean(KEY_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean value) {
        editor.putBoolean(KEY_LOGGED_IN, value);
        editor.apply();
    }
    public boolean isAccepted() {
        return pref.getBoolean(KEY_IS_ACCEPTED, false);
    }

    public void setAccepted(boolean value) {
        editor.putBoolean(KEY_IS_ACCEPTED, value);
        editor.apply();
    }
}
