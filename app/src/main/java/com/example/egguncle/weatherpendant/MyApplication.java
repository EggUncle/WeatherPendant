package com.example.egguncle.weatherpendant;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by egguncle on 16-11-7.
 */

public class MyApplication extends Application {

    private String MY_API_KEY="994f4d03499d618879bd337a94bce437";

    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this,MY_API_KEY);

    }
}