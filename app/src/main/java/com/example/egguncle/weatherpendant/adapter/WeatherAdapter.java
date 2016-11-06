package com.example.egguncle.weatherpendant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.egguncle.weatherpendant.R;
import com.example.egguncle.weatherpendant.entities.weather.HeWeather;
import com.example.egguncle.weatherpendant.entities.weather.Root;

import java.util.List;

/**
 * Created by egguncle on 16-11-7.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private Context mContext;
    private List<Root> mListData;

    public WeatherAdapter(Context context, List<Root> listData) {
        mContext = context;
        mListData = listData;
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weather, parent, false));
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
        HeWeather heWeather = mListData.get(position).getHeWeather().get(0);
        holder.tvCity.setText(heWeather.getBasic().getCity());
        holder.tvWeahter.setText(heWeather.getNow().getCond().getTxt_d());
        holder.tvTemperature.setText(heWeather.getNow().getTmp());
        holder.tvTemperatureMin.setText(heWeather.getDaily_forecast().get(0).getTmp().getMin());
        holder.tvTemperatureMax.setText(heWeather.getDaily_forecast().get(0).getTmp().getMax());
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTemperature;
        private TextView tvCity;
        private TextView tvWeahter;
        private TextView tvTemperatureMin;
        private TextView tvTemperatureMax;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);
            tvCity = (TextView) itemView.findViewById(R.id.tv_city);
            tvWeahter = (TextView) itemView.findViewById(R.id.tv_weahter);
            tvTemperatureMin = (TextView) itemView.findViewById(R.id.tv_temperature_min);
            tvTemperatureMax = (TextView) itemView.findViewById(R.id.tv_temperature_max);


        }
    }
}
