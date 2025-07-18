package com.salmakhd.android.shahabseries.instagram;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private MySharedPreference mySharedPreference;
    private SharedPreferences sharedPreferences;
    private MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("shared_preferences_insta", Context.MODE_PRIVATE);
    }
    public MySharedPreference getInstance(Context context) {
        if(mySharedPreference == null) {
            mySharedPreference = new MySharedPreference(context);
        }
        return mySharedPreference;
    }

    public void saveString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }
}
