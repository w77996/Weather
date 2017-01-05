package weather.wu.com.bean;


import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class WeatherBean   extends DataSupport {
    public String mCityName;
    public String mShowapi_Res_Code;
    public AqiDetailBean mAqiDetailBean;
    public CityInfoBean mCityInfoBean;
    public NowWeatherBean mNowWeatherBean;
    public TodayWeatherBean mTodayWeatherBean;
    public IndexBean indexBean;
    public List<FutureWeatherBean> mFutureWeatherBeen;
    public List<HourDataBean> mHourDataList;

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmShowapi_Res_Code() {
        return mShowapi_Res_Code;
    }

    public void setmShowapi_Res_Code(String mShowapi_Res_Code) {
        this.mShowapi_Res_Code = mShowapi_Res_Code;
    }

    public AqiDetailBean getmAqiDetailBean() {
        return mAqiDetailBean;
    }

    public void setmAqiDetailBean(AqiDetailBean mAqiDetailBean) {
        this.mAqiDetailBean = mAqiDetailBean;
    }

    public CityInfoBean getmCityInfoBean() {
        return mCityInfoBean;
    }

    public void setmCityInfoBean(CityInfoBean mCityInfoBean) {
        this.mCityInfoBean = mCityInfoBean;
    }

    public NowWeatherBean getmNowWeatherBean() {
        return mNowWeatherBean;
    }

    public void setmNowWeatherBean(NowWeatherBean mNowWeatherBean) {
        this.mNowWeatherBean = mNowWeatherBean;
    }

    public TodayWeatherBean getmTodayWeatherBean() {
        return mTodayWeatherBean;
    }

    public void setmTodayWeatherBean(TodayWeatherBean mTodayWeatherBean) {
        this.mTodayWeatherBean = mTodayWeatherBean;
    }

    public IndexBean getIndexBean() {
        return indexBean;
    }

    public void setIndexBean(IndexBean indexBean) {
        this.indexBean = indexBean;
    }

    public List<FutureWeatherBean> getmFutureWeatherBeen() {
        return mFutureWeatherBeen;
    }

    public void setmFutureWeatherBeen(List<FutureWeatherBean> mFutureWeatherBeen) {
        this.mFutureWeatherBeen = mFutureWeatherBeen;
    }

    public List<HourDataBean> getmHourDataList() {
        return mHourDataList;
    }

    public void setmHourDataList(List<HourDataBean> mHourDataList) {
        this.mHourDataList = mHourDataList;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "mCityName='" + mCityName + '\'' +
                ", mShowapi_Res_Code='" + mShowapi_Res_Code + '\'' +
                ", mAqiDetailBean=" + mAqiDetailBean +
                ", mCityInfoBean=" + mCityInfoBean +
                ", mNowWeatherBean=" + mNowWeatherBean +
                ", mTodayWeatherBean=" + mTodayWeatherBean +
                ", indexBean=" + indexBean +
                ", mFutureWeatherBeen=" + mFutureWeatherBeen +
                ", mHourDataList=" + mHourDataList +
                '}';
    }
}
