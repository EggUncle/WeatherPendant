package com.example.egguncle.weatherpendant.unities;


import android.content.Context;
import android.util.Log;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.example.egguncle.weatherpendant.adapter.WeatherAdapter;
import com.example.egguncle.weatherpendant.entities.weather.Root;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.apistore.sdk.ApiStoreSDK.GET;

/**
 * Created by egguncle on 16-11-7.
 * 网络通信模块
 */

public class NetWorkUnit {
    private List<Root> mWeatherList;
    private Context mContext;
    private final String URL_WEATHER = "http://apis.baidu.com/heweather/weather/free";

    public NetWorkUnit(Context context) {
        mWeatherList = new ArrayList<>();
        mContext = context;
    }

    /**
     * @param cityName 发送请求获取天气信息
     */
    public void getWeatherMessage(String cityName, final WeatherAdapter weatherAdapter) {
        Parameters para = new Parameters();
        para.put("city", cityName);
        ApiStoreSDK.execute(URL_WEATHER, ApiStoreSDK.GET, para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int i, String json) {
                        Gson gson = new Gson();
                        Root root = gson.fromJson(json, Root.class);
                        if (root != null) {
                            synchronized (mContext) {
                                mWeatherList.add(root);
                                weatherAdapter.notifyDataSetChanged();
                                Log.v("MY_TAG",root.getHeWeather().get(0).getBasic().getCity());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onError(int i, String s, Exception e) {
                        super.onError(i, s, e);
                    }
                });

    }

    /**
     * 获取天气信息
     *
     * @return
     */
    public List<Root> getmWeatherList() {
        return mWeatherList;
    }
}
