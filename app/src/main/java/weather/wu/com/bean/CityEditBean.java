package weather.wu.com.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/1/13.
 */
public class CityEditBean implements Parcelable {


    public CityEditBean() {

    }

    public String mCityName;
    public String mWeather_Pic;
    public String mTempture;

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmWeather_Pic() {
        return mWeather_Pic;
    }

    public void setmWeather_Pic(String mWeather_Pic) {
        this.mWeather_Pic = mWeather_Pic;
    }

    public String getmTempture() {
        return mTempture;
    }

    public void setmTempture(String mTempture) {
        this.mTempture = mTempture;
    }

    public CityEditBean(Parcel in) {
        mCityName = in.readString();
        mWeather_Pic = in.readString();
        mTempture = in.readString();
    }

    public static final Creator<CityEditBean> CREATOR = new Creator<CityEditBean>() {
        @Override
        public CityEditBean createFromParcel(Parcel in) {
            return new CityEditBean(in);
        }

        @Override
        public CityEditBean[] newArray(int size) {
            return new CityEditBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCityName);
        dest.writeString(mWeather_Pic);
        dest.writeString(mTempture);
    }
}
