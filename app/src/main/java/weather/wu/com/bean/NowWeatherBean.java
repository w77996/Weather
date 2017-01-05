package weather.wu.com.bean;


import org.litepal.crud.DataSupport;

/**
 * Created by 吴海辉 on 2016/12/1.
 *
 *  "now":{
 "aqiDetail":{
 "co":0.767,
 "so2":10,
 "area":"丽江",
 "o3":54,
 "no2":16,
 "area_code":"lijiang",
 "quality":"优",
 "aqi":43,
 "pm10":42,
 "pm2_5":20,
 "o3_8h":66,
 "primary_pollutant":""
 },
 "weather_code":"01",
 "wind_direction":"西南风",
 "temperature_time":"21:00",
 "wind_power":"2级",
 "aqi":43,
 "sd":"60%",
 "weather_pic":"http://appimg.showapi.com/images/weather/icon/night/01.png",
 "weather":"多云",
 "temperature":"8"
 },
 */
public class NowWeatherBean  extends DataSupport {

    public String mWeather_Code;
    public String mWind_Direction;
    public String mTemperature_Time;
    public String mWind_Power;
    public String mAqi;
    public String mSd;//空气湿度
    public String mWeather;
    public String mWeather_Pic;
    public String mTemperature;

    public String getmWeather_Code() {
        return mWeather_Code;
    }

    public void setmWeather_Code(String mWeather_Code) {
        this.mWeather_Code = mWeather_Code;
    }

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

    public String getmWind_Power() {
        return mWind_Power;
    }

    public void setmWind_Power(String mWind_Power) {
        this.mWind_Power = mWind_Power;
    }

    public String getmAqi() {
        return mAqi;
    }

    public void setmAqi(String mAqi) {
        this.mAqi = mAqi;
    }

    public String getmSd() {
        return mSd;
    }

    public void setmSd(String mSd) {
        this.mSd = mSd;
    }

    public String getmWeather() {
        return mWeather;
    }

    public void setmWeather(String mWeather) {
        this.mWeather = mWeather;
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

    @Override
    public String toString() {
        return "NowWeatherBean{" +
                "mWeather_Code='" + mWeather_Code + '\'' +
                ", mWind_Direction='" + mWind_Direction + '\'' +
                ", mTemperature_Time='" + mTemperature_Time + '\'' +
                ", mWind_Power='" + mWind_Power + '\'' +
                ", mAqi='" + mAqi + '\'' +
                ", mSd='" + mSd + '\'' +
                ", mWeather='" + mWeather + '\'' +
                ", mWeather_Pic='" + mWeather_Pic + '\'' +
                ", mTemperature='" + mTemperature + '\'' +
                '}';
    }
}
