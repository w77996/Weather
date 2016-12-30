package weather.wu.com.utils;

import com.orhanobut.logger.Logger;
import com.show.api.ShowApiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import weather.wu.com.bean.AqiDetailBean;
import weather.wu.com.bean.CityInfoBean;
import weather.wu.com.bean.FutureWeatherBean;
import weather.wu.com.bean.HourDataBean;
import weather.wu.com.bean.IndexBean;
import weather.wu.com.bean.NowWeatherBean;
import weather.wu.com.bean.TodayWeatherBean;

/**
 * Created by Administrator on 2016/12/24.
 */
public class HttpUtil {
    public final static String appid = "28198";//要替换成自己的
    public final static String secret = "bd9ad7a172ee4a5a8c57618a248c63e9";//要替换成自己的
    static NowWeatherBean mNowWeatherBean = new NowWeatherBean();
    static List<FutureWeatherBean> mFutureWeatherBeanList = new ArrayList<FutureWeatherBean>();
    // public static String cityName ="广州";
   /* public static String address = "http://route.showapi.com/9-2?showapi_appid=" + appid + "&area=" + "广州" + "&showapi_sign=" + secret
            + "&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";*/

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    public static void requestWeather(String cityName,okhttp3.Callback callback) {
       String address = "http://route.showapi.com/9-2?showapi_appid=28198&area=" + cityName + "&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9"
                + "&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void getWeatherJsonData(final String cityName) {
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String res = new ShowApiRequest("http://route.showapi.com/9-2", appid, secret)
                        .addTextPara("areaid", "")
                        .addTextPara("area", cityName)
                        .addTextPara("needMoreDay", "1")
                        .addTextPara("needIndex", "1")
                        .addTextPara("needHourData", "1")
                        .addTextPara("need3HourForcast", "1")
                        .addTextPara("needAlarm", "1")
                        .post();
               // Logger.json(res);

                parseJsonData(res);
                //把返回内容通过handler对象更新到界面
            }
        }.start();

    }

    public static void parseJsonData(String jsonData) {
        try {
          //  Log.d("test", jsonData);
            JSONObject resultData = new JSONObject(jsonData);
            int showapi_res_code = resultData.getInt("showapi_res_code");
            JSONObject showapi_res_body = resultData.getJSONObject("showapi_res_body");

            JSONObject nowData = showapi_res_body.getJSONObject("now");
            JSONObject cityInfo = showapi_res_body.getJSONObject("cityInfo");
            JSONObject todayWeatherInfo = showapi_res_body.getJSONObject("f1");
            JSONArray hourDataList = showapi_res_body.getJSONArray("hourDataList");
          /*  parseNowJsonData(nowData);
            parseCityInfo(cityInfo);
            parseTodayWeatherInfo(todayWeatherInfo);*/
            //  parseHourDataList(hourDataList);
            JSONObject f1 = showapi_res_body.getJSONObject("f1");
            JSONObject f2 = showapi_res_body.getJSONObject("f2");
            JSONObject f3 = showapi_res_body.getJSONObject("f3");
            JSONObject f4 = showapi_res_body.getJSONObject("f4");
            JSONObject f5 = showapi_res_body.getJSONObject("f5");
            JSONObject f6 = showapi_res_body.getJSONObject("f6");


            String p[] = {f1.toString(), f2.toString(), f3.toString(), f4.toString(), f5.toString(), f6.toString()};
            parseFutureJson(p);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *  public String mDay_Weather;
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
     * @param p
     */
    private static void parseFutureJson(String[] p) {
        try {
            for (int i = 0; i < p.length; i++) {

                JSONObject futureWeather = new JSONObject(p[i]);
                FutureWeatherBean mFutureWeatherBean = new FutureWeatherBean();
                mFutureWeatherBean.mDay_Weather = futureWeather.getString("day_weather");
                mFutureWeatherBean.mDay_Weather_Code = futureWeather.getString("day_weather_code");
                mFutureWeatherBean.mNight_Weather =futureWeather.getString("night_weather");
                mFutureWeatherBean.mNight_Weather_Code = futureWeather.getString("night_weather_code");
                mFutureWeatherBean.mAir_Press = futureWeather.getString("air_press");
                mFutureWeatherBean.mJiangShui = futureWeather.getString("jiangshui");
                mFutureWeatherBean.mNight_Wind_Power = futureWeather.getString("night_wind_power");
                mFutureWeatherBean.mDay_Wind_Power = futureWeather.getString("day_wind_power");
                mFutureWeatherBean.mSun_Begin = futureWeather.getString("sun_begin_end");
                mFutureWeatherBean.mSun_End = futureWeather.getString("sun_begin_end");
                mFutureWeatherBean.mZiWaiXian = futureWeather.getString("ziwaixian");
                mFutureWeatherBean.mDay_Weather_Pic = futureWeather.getString("day_weather_pic");
                mFutureWeatherBean.mWeekDay = futureWeather.getString("weekday");
                mFutureWeatherBean.mNight_Air_Temperature = futureWeather.getString("night_air_temperature");
                mFutureWeatherBean.mDay_Air_Temperature = futureWeather.getString("day_air_temperature");
                mFutureWeatherBean.mDay_Wind_Direction = futureWeather.getString("day_wind_direction");
                mFutureWeatherBean.mNight_Wind_Direction = futureWeather.getString("night_wind_direction");
                mFutureWeatherBean.mDay = futureWeather.getString("day");
              //  Logger.d(mFutureWeatherBean.toString());
                mFutureWeatherBeanList.add(mFutureWeatherBean);
            }
            Logger.d(mFutureWeatherBeanList.size()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private static void parseHourDataList(JSONArray hourDataListArray) {
        try {
            List<HourDataBean> list = new ArrayList<HourDataBean>();
            for (int i = 0; i < hourDataListArray.length(); i++) {
                HourDataBean hourDataBean = new HourDataBean();

                JSONObject hourDataJson = hourDataListArray.getJSONObject(i);
                hourDataBean.mTemperature = hourDataJson.getString("temperature");
                hourDataBean.mTemperature_Time = hourDataJson.getString("temperature_time");
                hourDataBean.mWeather_Pic = hourDataJson.getString("weather_pic");
                hourDataBean.mWind_Direction = hourDataJson.getString("wind_direction");
                hourDataBean.mWind_Power = hourDataJson.getString("wind_power");

                list.add(hourDataBean);
            }
            Logger.d("hourDataListSize",list.size() + "");
            Logger.d(list.get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void parseTodayWeatherInfo(JSONObject todayWeatherInfo) {
        TodayWeatherBean todayWeatherBean = new TodayWeatherBean();
        IndexBean indexBean = new IndexBean();

        try {
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


            Logger.d("todayWeathrBean",todayWeatherBean.toString());

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void parseCityInfo(JSONObject cityInfo) {
        CityInfoBean cityInfoBean = new CityInfoBean();
        try {
            // JSONObject cityInfoJsonData = cityInfo.getJSONObject("cityInfo");
            cityInfoBean.mCityName_C5 = cityInfo.getString("c5");
            cityInfoBean.mPostCode_C12 = cityInfo.getString("c12");
            cityInfoBean.mAreaCode_C11 = cityInfo.getString("c11");
            cityInfoBean.mAltitude_C15 = cityInfo.getString("c15");
            cityInfoBean.mLatitude = cityInfo.getDouble("latitude") + "";
            cityInfoBean.mLongitude = cityInfo.getDouble("longitude") + "";
            cityInfoBean.mRadarCode_C16 = cityInfo.getString("c16") + "";
            Logger.d("citiInfo", cityInfoBean.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private static void parseNowJsonData(JSONObject nowData) {
        try {
            JSONObject now_apiDetail = nowData.getJSONObject("aqiDetail");
            //  parseApiDetailJsonData(now_apiDetail);
            mNowWeatherBean = new NowWeatherBean();
            mNowWeatherBean.mWeather_Code = nowData.getString("weather_code");
            mNowWeatherBean.mWind_Direction = nowData.getString("wind_direction");
            mNowWeatherBean.mTemperature_Time = nowData.getString("temperature_time");
            mNowWeatherBean.mWind_Power = nowData.getString("wind_power");
            mNowWeatherBean.mAqi = nowData.getInt("aqi") + "";
            mNowWeatherBean.mSd = nowData.getString("sd");
            mNowWeatherBean.mWeather_Pic = nowData.getString("weather_pic");
            mNowWeatherBean.mWeather = nowData.getString("weather");
            mNowWeatherBean.mTemperature = nowData.getString("temperature");
            //   mNowWeatherBean.save();
            // parseAqiDetailJsonData(now_apiDetail);
            Logger.d("nowweather bean json", mNowWeatherBean.toString());
            AqiDetailBean aqiDetailBean = new AqiDetailBean();
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

            Logger.d("nowweather bean json" ,aqiDetailBean.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void parseAqiDetailJsonData(JSONObject now_apiDetail) {
        AqiDetailBean aqiDetailBean = new AqiDetailBean();
        try {
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

            //   Log.d("aqiDetailBean.toString()",aqiDetailBean.toString());
            Logger.d("aqiDetailBean",aqiDetailBean.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
