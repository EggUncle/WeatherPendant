package com.example.egguncle.weatherpendant.activity;

import android.os.AsyncTask;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.egguncle.weatherpendant.R;
import com.example.egguncle.weatherpendant.adapter.WeatherAdapter;
import com.example.egguncle.weatherpendant.entities.weather.Root;
import com.example.egguncle.weatherpendant.unities.NetWorkUnit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout activityMain;
    private ContentLoadingProgressBar progressBar;
    private RecyclerView rcvWeather;

    private WeatherAdapter mWeatherAdapter;
    private List<Root> mWeatherData;
    private NetWorkUnit mNetWorkUnit;

    private String defaultCityName = "北京";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVar();
        initView();

        WeatherSetAsyncTask weatherSetAsyncTask=new WeatherSetAsyncTask();
        weatherSetAsyncTask.execute();
    }

    private void initView(){
        activityMain = (RelativeLayout) findViewById(R.id.activity_main);
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress_bar);
        rcvWeather = (RecyclerView) findViewById(R.id.rcv_weather);

        rcvWeather.setLayoutManager(new LinearLayoutManager(this));
        rcvWeather.setHasFixedSize(true);
        rcvWeather.setAdapter(mWeatherAdapter);
    }

    private void initVar(){
        mNetWorkUnit =new NetWorkUnit(this);
        mWeatherData=new ArrayList<>();
        mWeatherAdapter=new WeatherAdapter(this,mNetWorkUnit.getmWeatherList());

    }

    private class WeatherSetAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNetWorkUnit.getWeatherMessage(defaultCityName,mWeatherAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
       //     mWeatherAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            rcvWeather.setVisibility(View.VISIBLE);
        }
    }
}
