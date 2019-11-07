package com.inpromos.app.utils;

import android.content.ContentProvider;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesReference {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String SHARED_PREFERENCES_KEY = "IN_PROMOS_APP_PREFERENCES";
    private Context context;

    public SharedPreferencesReference(Context context) {
        this.context = context;
    }

    public SharedPreferences getPreferences() {
        preferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        return preferences;
    }

    public SharedPreferences.Editor getEditor() {
        editor = getPreferences().edit();
        return editor;
    }

}
