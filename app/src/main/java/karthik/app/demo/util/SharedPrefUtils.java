package karthik.app.demo.util;

import android.content.Context;
import android.content.SharedPreferences;


import javax.inject.Inject;
import javax.inject.Singleton;

import karthik.app.demo.di.scope.ApplicationContext;

/**
 * Created by gaurav on 28/6/17.
 */

@Singleton
public class SharedPrefUtils {

    private Context context;
    private String SHARED_PREF_FILE_NAME = "MyDemoApp";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Inject
    public SharedPrefUtils(@ApplicationContext Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getInt(String key) {

        return sharedPreferences.getInt(key, -1);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, -1);
    }


    public void putBoolean(String key, boolean defaultValue) {

        editor.putBoolean(key, defaultValue);
        editor.apply();
    }

    public void putInt(String key, int defaultValue) {

        editor.putInt(key, defaultValue);
        editor.apply();

    }

    public void putString(String key, String defaultValue) {

        editor.putString(key, defaultValue);
        editor.apply();
    }

    public void putLong(String key, long defaultValue) {

        editor.putLong(key, defaultValue);
        editor.apply();
    }

    public void removeValue(String key) {

        editor.remove(key);
        editor.apply();
    }

    public void clearAll() {
        editor.clear();
        editor.apply();
    }

}

