package weather.wu.com.bean;

import org.litepal.crud.DataSupport;

/**
         * Created by 吴海辉 on 2016/12/29.
         * "aqiDetail":{
         "co":0.9,
         "so2":15,
         "area":"广州",
         "o3":24,
         "no2":81,
         "area_code":"guangzhou",
         "quality":"良",
         "aqi":67,
         "pm10":75,
         "pm2_5":48,
         "o3_8h":55,
         "primary_pollutant":"颗粒物(PM2.5)"
         },
         "weather_code":"00",
         "wind_direction":"西南风",
         "temperature_time":"00:04",
         "wind_power":"1级",
         "aqi":67,
         "sd":"57%",
         "weather_pic":"http://appimg.showapi.com/images/weather/icon/night/00.png",
         "weather":"晴",
         "temperature":"11"
         },
 */
public class HourDataBean  extends DataSupport {
    public String mWind_Direction;
    public String mTemperature_Time;
    public String mWeather_Pic;
    public String mTemperature;
    public String mWind_Power;

    public String getmWind_Direction() {
        return mWind_Direction;
    }

    public void setmWind_Direction(String mWind_Direction) {
        this.mWind_Direction = mWind_Direction;
    }

    public String getmTemperature_Time() {
        return mTemperature_Time;
    }

    public void setmTemperature_Time(String mTemperature_Time) {
        this.mTemperature_Time = mTemperature_Time;
    }

    public String getmWeather_Pic() {
        return mWeather_Pic;
    }

    public void setmWeather_Pic(String mWeather_Pic) {
        this.mWeather_Pic = mWeather_Pic;
    }

    public String getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
    }

    public String getmWind_Power() {
        return mWind_Power;
    }

    public void setmWind_Power(String mWind_Power) {
        this.mWind_Power = mWind_Power;
    }

    @Override
    public String toString() {
        return "HourData{" +
                "mWind_Direction='" + mWind_Direction + '\'' +
                ", mTemperature_Time='" + mTemperature_Time + '\'' +
                ", mWeather_Pic='" + mWeather_Pic + '\'' +
                ", mTemperature='" + mTemperature + '\'' +
                ", mWind_Power='" + mWind_Power + '\'' +
                '}';
    }
}
