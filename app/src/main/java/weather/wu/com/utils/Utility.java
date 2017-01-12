package weather.wu.com.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import weather.wu.com.bean.AqiDetailBean;
import weather.wu.com.bean.CityInfoBean;
import weather.wu.com.bean.FutureWeatherBean;
import weather.wu.com.bean.HourDataBean;
import weather.wu.com.bean.IndexBean;
import weather.wu.com.bean.NowWeatherBean;
import weather.wu.com.bean.TodayWeatherBean;
import weather.wu.com.bean.WeatherBean;

/**
 * Created by 吴海辉 on 2016/12/30.
 */
public class Utility {
    public static WeatherBean handleWeatherResponse(String response) {
        try {
            List<FutureWeatherBean> futureWeatherBeanList = new ArrayList<FutureWeatherBean>();
            NowWeatherBean nowWeatherBean = new NowWeatherBean();
            AqiDetailBean aqiDetailBean = new AqiDetailBean();
            CityInfoBean cityInfoBean = new CityInfoBean();
            TodayWeatherBean todayWeatherBean = new TodayWeatherBean();
            IndexBean indexBean = new IndexBean();
            List<HourDataBean> hourDatalist = new ArrayList<HourDataBean>();
            WeatherBean weatherBean = new WeatherBean();
            JSONObject resultData = new JSONObject(response);

            int showapi_res_code = resultData.getInt("showapi_res_code");
            weatherBean.setmShowapi_Res_Code(showapi_res_code + "");
            Logger.e(showapi_res_code + "");

            JSONObject showapi_res_body = resultData.getJSONObject("showapi_res_body");

            JSONObject nowData = showapi_res_body.getJSONObject("now");
            JSONObject cityInfo = showapi_res_body.getJSONObject("cityInfo");
            JSONObject todayWeatherInfo = showapi_res_body.getJSONObject("f1");
            JSONArray hourDataListArray = showapi_res_body.getJSONArray("hourDataList");

            /*  parseNowJsonData(nowData);
            parseCityInfo(cityInfo);
            parseTodayWeatherInfo(todayWeatherInfo);*/
            //  parseHourDataList(hourDataList);

            JSONObject now_apiDetail = nowData.getJSONObject("aqiDetail");
            //  parseApiDetailJsonData(now_apiDetail);

            nowWeatherBean.mWeather_Code = nowData.getString("weather_code");
            nowWeatherBean.mWind_Direction = nowData.getString("wind_direction");
            nowWeatherBean.mTemperature_Time = nowData.getString("temperature_time");
            nowWeatherBean.mWind_Power = nowData.getString("wind_power");
            nowWeatherBean.mAqi = nowData.getInt("aqi") + "";
            nowWeatherBean.mSd = nowData.getString("sd");
            nowWeatherBean.mWeather_Pic = nowData.getString("weather_pic");
            nowWeatherBean.mWeather = nowData.getString("weather");
            nowWeatherBean.mTemperature = nowData.getString("temperature");

            // parseAqiDetailJsonData(now_apiDetail);
            Logger.d("nowweather bean json", nowWeatherBean.toString());

            //空气质量解析
            aqiDetailBean.mCo = now_apiDetail.getDouble("co") + "";
            aqiDetailBean.mSo2 = now_apiDetail.getInt("so2") + "";
            aqiDetailBean.mArea = now_apiDetail.getString("area");
            aqiDetailBean.mO3 = now_apiDetail.getInt("o3") + "";
            aqiDetailBean.mNo2 = now_apiDetail.getInt("no2") + "";
            aqiDetailBean.mQuality = now_apiDetail.getString("quality");
            aqiDetailBean.mAqi = now_apiDetail.getInt("aqi") + "";
            aqiDetailBean.mPm10 = now_apiDetail.getInt("pm10") + "";
            aqiDetailBean.mPm2_5 = now_apiDetail.getInt("pm2_5") + "";
            aqiDetailBean.mPrimary_Pollutant = now_apiDetail.getString("primary_pollutant");

            Logger.d("nowweather bean json", aqiDetailBean.toString());


            //未来几天天气解析
            JSONObject f1 = showapi_res_body.getJSONObject("f1");
            JSONObject f2 = showapi_res_body.getJSONObject("f2");
            JSONObject f3 = showapi_res_body.getJSONObject("f3");
            JSONObject f4 = showapi_res_body.getJSONObject("f4");
            JSONObject f5 = showapi_res_body.getJSONObject("f5");
            JSONObject f6 = showapi_res_body.getJSONObject("f6");

            String futureJsonArray[] = {f1.toString(), f2.toString(), f3.toString(), f4.toString(), f5.toString(), f6.toString()};
            for (int i = 0; i < futureJsonArray.length; i++) {

                JSONObject futureWeather = new JSONObject(futureJsonArray[i]);
                FutureWeatherBean futureWeatherBean = new FutureWeatherBean();
                futureWeatherBean.mDay_Weather = futureWeather.getString("day_weather");
                futureWeatherBean.mDay_Weather_Code = futureWeather.getString("day_weather_code");
                futureWeatherBean.mNight_Weather = futureWeather.getString("night_weather");
                futureWeatherBean.mNight_Weather_Code = futureWeather.getString("night_weather_code");
                futureWeatherBean.mAir_Press = futureWeather.getString("air_press");
                futureWeatherBean.mJiangShui = futureWeather.getString("jiangshui");
                futureWeatherBean.mNight_Wind_Power = futureWeather.getString("night_wind_power");
                futureWeatherBean.mDay_Wind_Power = futureWeather.getString("day_wind_power");
                futureWeatherBean.mSun_Begin = futureWeather.getString("sun_begin_end");
                futureWeatherBean.mSun_End = futureWeather.getString("sun_begin_end");
                futureWeatherBean.mZiWaiXian = futureWeather.getString("ziwaixian");
                futureWeatherBean.mDay_Weather_Pic = futureWeather.getString("day_weather_pic");
                futureWeatherBean.mWeekDay = futureWeather.getString("weekday");
                futureWeatherBean.mNight_Air_Temperature = futureWeather.getString("night_air_temperature");
                futureWeatherBean.mDay_Air_Temperature = futureWeather.getString("day_air_temperature");
                futureWeatherBean.mDay_Wind_Direction = futureWeather.getString("day_wind_direction");
                futureWeatherBean.mNight_Wind_Direction = futureWeather.getString("night_wind_direction");
                futureWeatherBean.mDay = futureWeather.getString("day");
                //  Logger.d(mFutureWeatherBean.toString());
                futureWeatherBeanList.add(futureWeatherBean);

            }
           // weatherBean.mFutureWeatherBeen = futureWeatherBeanList;
            Logger.d(futureWeatherBeanList.size() + "");

            cityInfoBean.mCityName_C5 = cityInfo.getString("c5");
            cityInfoBean.mPostCode_C12 = cityInfo.getString("c12");
            cityInfoBean.mAreaCode_C11 = cityInfo.getString("c11");
            cityInfoBean.mAltitude_C15 = cityInfo.getString("c15");
            cityInfoBean.mLatitude = cityInfo.getDouble("latitude") + "";
            cityInfoBean.mLongitude = cityInfo.getDouble("longitude") + "";
            cityInfoBean.mRadarCode_C16 = cityInfo.getString("c16") + "";
            weatherBean.setmCityName(cityInfoBean.getmCityName_C5());

            Logger.d("citiInfo", cityInfoBean.toString());

            todayWeatherBean.mDay_Weather = todayWeatherInfo.getString("day_weather");
            todayWeatherBean.mNight_Weather = todayWeatherInfo.getString("night_weather");
            todayWeatherBean.mNight_Weather_Code = todayWeatherInfo.getString("night_weather_code");
            todayWeatherBean.mAir_Press = todayWeatherInfo.getString("air_press");
            todayWeatherBean.mJiangShui = todayWeatherInfo.getString("jiangshui");
            todayWeatherBean.mNight_Wind_Power = todayWeatherInfo.getString("night_wind_power");
            todayWeatherBean.mDay_Wind_Power = todayWeatherInfo.getString("day_wind_power");
            todayWeatherBean.mDay_Weather_Code = todayWeatherInfo.getString("day_weather_code");
            todayWeatherBean.mSun_Begin = todayWeatherInfo.getString("sun_begin_end");
            todayWeatherBean.mSun_End = todayWeatherInfo.getString("sun_begin_end");
            todayWeatherBean.mZiWaiXian = todayWeatherInfo.getString("ziwaixian");
            todayWeatherBean.mDay_Weather_Pic = todayWeatherInfo.getString("day_weather_pic");
            todayWeatherBean.mWeekDay = todayWeatherInfo.getString("weekday");
            todayWeatherBean.mNight_Air_Temperature = todayWeatherInfo.getString("night_air_temperature");
            todayWeatherBean.mDay_Air_Temperature = todayWeatherInfo.getString("day_air_temperature");
            todayWeatherBean.mDay_Wind_Direction = todayWeatherInfo.getString("day_wind_direction");
            todayWeatherBean.mDay = todayWeatherInfo.getString("day");
            todayWeatherBean.mNight_Weather_Pic = todayWeatherInfo.getString("night_weather_pic");
            todayWeatherBean.mNight_Wind_Direction = todayWeatherInfo.getString("night_wind_direction");

            Logger.d("todayWeathrBean", todayWeatherBean.toString());

            JSONObject indexJsonData = todayWeatherInfo.getJSONObject("index");
            indexBean.mYhTitle = indexJsonData.getJSONObject("yh").getString("title");
            indexBean.mYhDesc = indexJsonData.getJSONObject("yh").getString("desc");
            indexBean.mLsTitle = indexJsonData.getJSONObject("ls").getString("title");
            indexBean.mLsDesc = indexJsonData.getJSONObject("ls").getString("desc");
            indexBean.mClothesTitle = indexJsonData.getJSONObject("clothes").getString("title");
            indexBean.mClothesDesc = indexJsonData.getJSONObject("clothes").getString("desc");
            indexBean.mDyTitle = indexJsonData.getJSONObject("dy").getString("title");
            indexBean.mDyDesc = indexJsonData.getJSONObject("dy").getString("desc");
            indexBean.mSportsTitle = indexJsonData.getJSONObject("sports").getString("title");
            indexBean.mSportsDesc = indexJsonData.getJSONObject("sports").getString("desc");
            indexBean.mTravelTitle = indexJsonData.getJSONObject("travel").getString("title");
            indexBean.mTravelDesc = indexJsonData.getJSONObject("travel").getString("desc");
            indexBean.mBeautyTitle = indexJsonData.getJSONObject("beauty").getString("title");
            indexBean.mBeautyDesc = indexJsonData.getJSONObject("beauty").getString("desc");
            indexBean.mXqTitle = indexJsonData.getJSONObject("xq").getString("title");
            indexBean.mXqDesc = indexJsonData.getJSONObject("xq").getString("desc");
            indexBean.mHcTitle = indexJsonData.getJSONObject("hc").getString("title");
            indexBean.mHcDesc = indexJsonData.getJSONObject("hc").getString("desc");
            indexBean.mZsTitle = indexJsonData.getJSONObject("zs").getString("title");
            indexBean.mZsDesc = indexJsonData.getJSONObject("zs").getString("desc");
            indexBean.mColdTitle = indexJsonData.getJSONObject("cold").getString("title");
            indexBean.mColdDesc = indexJsonData.getJSONObject("cold").getString("desc");
            indexBean.mGjTitle = indexJsonData.getJSONObject("gj").getString("title");
            indexBean.mGjDesc = indexJsonData.getJSONObject("gj").getString("desc");
            indexBean.mUvTitle = indexJsonData.getJSONObject("uv").getString("title");
            indexBean.mUvDesc = indexJsonData.getJSONObject("uv").getString("desc");
            indexBean.mClTitle = indexJsonData.getJSONObject("cl").getString("title");
            indexBean.mClDesc = indexJsonData.getJSONObject("cl").getString("desc");
            indexBean.mGlassTitle = indexJsonData.getJSONObject("glass").getString("title");
            indexBean.mGlassDesc = indexJsonData.getJSONObject("glass").getString("desc");
            indexBean.mWash_CarTitle = indexJsonData.getJSONObject("wash_car").getString("title");
            indexBean.mWash_CarDesc = indexJsonData.getJSONObject("wash_car").getString("desc");
            indexBean.mAqiTitle = indexJsonData.getJSONObject("aqi").getString("title");
            indexBean.mAqiDesc = indexJsonData.getJSONObject("aqi").getString("desc");
            indexBean.mAcTitle = indexJsonData.getJSONObject("ac").getString("title");
            indexBean.mAcDesc = indexJsonData.getJSONObject("ac").getString("desc");

            indexBean.mMfTitle = indexJsonData.getJSONObject("mf").getString("title");
            indexBean.mMfDesc = indexJsonData.getJSONObject("mf").getString("desc");
            indexBean.mAgTitle = indexJsonData.getJSONObject("ag").getString("title");
            indexBean.mAgDesc = indexJsonData.getJSONObject("ag").getString("desc");
            indexBean.mPjTitle = indexJsonData.getJSONObject("pj").getString("title");
            indexBean.mPjDesc = indexJsonData.getJSONObject("pj").getString("desc");
            indexBean.mNlTitle = indexJsonData.getJSONObject("nl").getString("title");
            indexBean.mNlDesc = indexJsonData.getJSONObject("nl").getString("desc");
            indexBean.mPkTitle = indexJsonData.getJSONObject("pk").getString("title");
            indexBean.mPkDesc = indexJsonData.getJSONObject("pk").getString("desc");


            //  mWeatherBean.mTodayWeatherBean = todayWeatherBean;
            Logger.d("todayWeatherBean", indexBean.toString());


            for (int i = 0; i < hourDataListArray.length(); i++) {
                HourDataBean hourDataBean = new HourDataBean();

                JSONObject hourDataJson = hourDataListArray.getJSONObject(i);
                hourDataBean.mTemperature = hourDataJson.getString("temperature");
                hourDataBean.mTemperature_Time = hourDataJson.getString("temperature_time");
                hourDataBean.mWeather_Pic = hourDataJson.getString("weather_pic");
                hourDataBean.mWind_Direction = hourDataJson.getString("wind_direction");
                hourDataBean.mWind_Power = hourDataJson.getString("wind_power");

                hourDatalist.add(hourDataBean);

            }
            Logger.d("hourDataListSize", hourDatalist.size() + "");
            Logger.d(hourDatalist.get(0).toString());

            weatherBean.setmNowWeatherBean(nowWeatherBean);
            weatherBean.setmCityInfoBean(cityInfoBean);
            weatherBean.setmAqiDetailBean(aqiDetailBean);
            weatherBean.setmHourDataList(hourDatalist);
            weatherBean.setmFutureWeatherBeen(futureWeatherBeanList);
            weatherBean.setmTodayWeatherBean(todayWeatherBean);
            weatherBean.setmHourDataList(hourDatalist);
            return weatherBean;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String weakDayInfliter(String weekDay) {
        switch (weekDay) {
            case "1":
                return "星期一";
            case "2":
                return "星期二";
            case "3":
                return "星期三";
            case "4":
                return "星期四";
            case "5":
                return "星期五";
            case "6":
                return "星期六";
            case "7":
                return "星期日";
            default:
                break;
        }
        return null;
    }
    public  static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}