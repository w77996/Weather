package weather.wu.com.bean;


import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/12/2.
 *
 *
 *               "f1":{
                 "day_weather":"多云",
                 "night_weather":"多云",
                 "night_weather_code":"01",
                 "index":Object{...},
                 "air_press":"1004 hPa",
                 "jiangshui":"2%",
                 "night_wind_power":"微风 <5.4m/s",
                 "day_wind_power":"微风 <5.4m/s",
                 "day_weather_code":"01",
                 "3hourForcast":Array[8],
                 "sun_begin_end":"06:52|17:41",
                 "ziwaixian":"最弱",
                 "day_weather_pic":"http://app1.showapi.com/weather/icon/day/01.png",
                 "weekday":5,
                 "night_air_temperature":"14",
                 "day_air_temperature":"22",
                 "day_wind_direction":"无持续风向",
                 "day":"20161202",
                 "night_weather_pic":"http://app1.showapi.com/weather/icon/night/01.png",
                 "night_wind_direction":"无持续风向"
                 },
 */
public class TodayWeatherBean   extends DataSupport {
    public String mDay_Weather;
    public String mDay_Weather_Code;
    public String mDay_Weather_Pic;
    public String mNight_Weather;
    public String mNight_Weather_Code;
    public String mNight_Weather_Pic;
    public String mAir_Press;
    public String mJiangShui;
    public String mNight_Wind_Power;
    public String mDay_Wind_Power;
    public String mSun_Begin;
    public String mSun_End;
    public String mZiWaiXian;
    public String mWeekDay;
    public String mNight_Air_Temperature;
    public String mDay_Air_Temperature;
    public String mDay_Wind_Direction;
    public String mNight_Wind_Direction;
    public String mDay;

    /**
     *     public String mDay_Weather;
     public String mDay_Weather_Code;
     public String mNight_Weather;
     public String mNight_Weather_Code;
     public String mAir_Press;
     public String mJiangShui;
     public String mNight_Wind_Power;
     public String mDay_Wind_Power;
     public String mSun_Begin;
     public String mSun_End;
     public String mZiWaiXian;
     public String mDay_Weather_Pic;
     public String mWeekDay;
     public String mNight_Air_Temperature;
     public String mDay_Air_Temperature;
     public String mDay_Wind_Direction;
     public String mNight_Wind_Direction;
     public String mDay;
     * @return
     */
    public String getmDay_Weather() {
        return mDay_Weather;
    }

    public void setmDay_Weather(String mDay_Weather) {
        this.mDay_Weather = mDay_Weather;
    }

    public String getmDay_Weather_Code() {
        return mDay_Weather_Code;
    }

    public void setmDay_Weather_Code(String mDay_Weather_Code) {
        this.mDay_Weather_Code = mDay_Weather_Code;
    }

    public String getmDay_Weather_Pic() {
        return mDay_Weather_Pic;
    }

    public void setmDay_Weather_Pic(String mDay_Weather_Pic) {
        this.mDay_Weather_Pic = mDay_Weather_Pic;
    }

    public String getmNight_Weather() {
        return mNight_Weather;
    }

    public void setmNight_Weather(String mNight_Weather) {
        this.mNight_Weather = mNight_Weather;
    }

    public String getmNight_Weather_Code() {
        return mNight_Weather_Code;
    }

    public void setmNight_Weather_Code(String mNight_Weather_Code) {
        this.mNight_Weather_Code = mNight_Weather_Code;
    }

    public String getmNight_Weather_Pic() {
        return mNight_Weather_Pic;
    }

    public void setmNight_Weather_Pic(String mNight_Weather_Pic) {
        this.mNight_Weather_Pic = mNight_Weather_Pic;
    }

    public String getmAir_Press() {
        return mAir_Press;
    }

    public void setmAir_Press(String mAir_Press) {
        this.mAir_Press = mAir_Press;
    }

    public String getmJiangShui() {
        return mJiangShui;
    }

    public void setmJiangShui(String mJiangShui) {
        this.mJiangShui = mJiangShui;
    }

    public String getmNight_Wind_Power() {
        return mNight_Wind_Power;
    }

    public void setmNight_Wind_Power(String mNight_Wind_Power) {
        this.mNight_Wind_Power = mNight_Wind_Power;
    }

    public String getmDay_Wind_Power() {
        return mDay_Wind_Power;
    }

    public void setmDay_Wind_Power(String mDay_Wind_Power) {
        this.mDay_Wind_Power = mDay_Wind_Power;
    }

    public String getmSun_Begin() {
        return mSun_Begin;
    }

    public void setmSun_Begin(String mSun_Begin) {
        this.mSun_Begin = mSun_Begin;
    }

    public String getmSun_End() {
        return mSun_End;
    }

    public void setmSun_End(String mSun_End) {
        this.mSun_End = mSun_End;
    }

    public String getmZiWaiXian() {
        return mZiWaiXian;
    }

    public void setmZiWaiXian(String mZiWaiXian) {
        this.mZiWaiXian = mZiWaiXian;
    }

    public String getmWeekDay() {
        return mWeekDay;
    }

    public void setmWeekDay(String mWeekDay) {
        this.mWeekDay = mWeekDay;
    }

    public String getmNight_Air_Temperature() {
        return mNight_Air_Temperature;
    }

    public void setmNight_Air_Temperature(String mNight_Air_Temperature) {
        this.mNight_Air_Temperature = mNight_Air_Temperature;
    }

    public String getmDay_Air_Temperature() {
        return mDay_Air_Temperature;
    }

    public void setmDay_Air_Temperature(String mDay_Air_Temperature) {
        this.mDay_Air_Temperature = mDay_Air_Temperature;
    }

    public String getmDay_Wind_Direction() {
        return mDay_Wind_Direction;
    }

    public void setmDay_Wind_Direction(String mDay_Wind_Direction) {
        this.mDay_Wind_Direction = mDay_Wind_Direction;
    }

    public String getmNight_Wind_Direction() {
        return mNight_Wind_Direction;
    }

    public void setmNight_Wind_Direction(String mNight_Wind_Direction) {
        this.mNight_Wind_Direction = mNight_Wind_Direction;
    }

    public String getmDay() {
        return mDay;
    }

    public void setmDay(String mDay) {
        this.mDay = mDay;
    }

    @Override
    public String toString() {
        return "TodayWeatherBean{" +
                "mDay_Weather='" + mDay_Weather + '\'' +
                ", mDay_Weather_Code='" + mDay_Weather_Code + '\'' +
                ", mDay_Weather_Pic='" + mDay_Weather_Pic + '\'' +
                ", mNight_Weather='" + mNight_Weather + '\'' +
                ", mNight_Weather_Code='" + mNight_Weather_Code + '\'' +
                ", mNight_Weather_Pic='" + mNight_Weather_Pic + '\'' +
                ", mAir_Press='" + mAir_Press + '\'' +
                ", mJiangShui='" + mJiangShui + '\'' +
                ", mNight_Wind_Power='" + mNight_Wind_Power + '\'' +
                ", mDay_Wind_Power='" + mDay_Wind_Power + '\'' +
                ", mSun_Begin='" + mSun_Begin + '\'' +
                ", mSun_End='" + mSun_End + '\'' +
                ", mZiWaiXian='" + mZiWaiXian + '\'' +
                ", mWeekDay='" + mWeekDay + '\'' +
                ", mNight_Air_Temperature='" + mNight_Air_Temperature + '\'' +
                ", mDay_Air_Temperature='" + mDay_Air_Temperature + '\'' +
                ", mDay_Wind_Direction='" + mDay_Wind_Direction + '\'' +
                ", mNight_Wind_Direction='" + mNight_Wind_Direction + '\'' +
                ", mDay='" + mDay + '\'' +
                '}';
    }
}
