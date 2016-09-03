package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by luo on 2016/8/21.
 */
public class MyApplication extends Application{
    private SharedPreferences Preferences;
    @Override
    public void onCreate() {
        super.onCreate();
        Preferences=getSharedPreferences("START_SPLASH", Context.MODE_PRIVATE);
    }
    public SharedPreferences getPreferences(){
        return Preferences;
    }
   public static class ConfidKey{
      public static final String IS_ONE_START="START_SPLASH";
    }
}
