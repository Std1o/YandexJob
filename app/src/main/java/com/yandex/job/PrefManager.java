package com.yandex.job;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private static final String PREF_NAME = "yandexJobPref";

    private static final String KEY_ID = "id";
    private static final String KEY_PERMISSION = "permission";
    private static final String KEY_HD = "hdConnection";

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
    public boolean getPermission() {
        return pref.getBoolean(KEY_PERMISSION, true);
    }

    public void setPermission(boolean value) {
        editor.putBoolean(KEY_PERMISSION, value);
        editor.apply();
    }

    //----------------------------
    public boolean getHD() {
        return pref.getBoolean(KEY_HD, true);
    }

    public void setHD(boolean value) {
        editor.putBoolean(KEY_HD, value);
        editor.apply();
    }
}
