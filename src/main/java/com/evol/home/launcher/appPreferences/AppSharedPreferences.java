package com.evol.home.launcher.appPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.evol.home.launcher.pageddragdropgrid.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by katakam on 2/25/2016.
 */
public class AppSharedPreferences {

    private static final String APP_PREFS = "APP_PREFS";
    private static final String PAGE1 = "PAGE1";
    private static final String PAGE2 = "PAGE2";
    private static final String PAGE3 = "PAGE3";

    private static AppSharedPreferences singlePrefsInstance;

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public AppSharedPreferences(Context context){
        this.appSharedPrefs = context.getSharedPreferences(APP_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();

    }

    public static AppSharedPreferences getInstance(Context context) {
        if(singlePrefsInstance == null){
            singlePrefsInstance = new AppSharedPreferences(context);
        }
        return singlePrefsInstance;
    }

    public Page getPage1() {
        return appSharedPrefs.getString(PAGE1, null)!= null ?
                new Gson().fromJson(appSharedPrefs.getString(PAGE1, null), Page.class) :
                null;
    }

    public void setPage1(Page page) {
        prefsEditor.putString(PAGE1, new Gson().toJson(page)).commit();
    }

    public Page getPage2() {
        return appSharedPrefs.getString(PAGE2, null)!= null ?
                new Gson().fromJson(appSharedPrefs.getString(PAGE2, null), Page.class) :
                null;
    }

    public void setPage2(Page page) {
        prefsEditor.putString(PAGE2, new Gson().toJson(page)).commit();
    }

    public Page getPage3() {

        return appSharedPrefs.getString(PAGE3, null)!= null ?
                new Gson().fromJson(appSharedPrefs.getString(PAGE3, null), Page.class) :
                null;
    }

    public void setPage3(Page page) {
        prefsEditor.putString(PAGE3, new Gson().toJson(page)).commit();
    }
}
