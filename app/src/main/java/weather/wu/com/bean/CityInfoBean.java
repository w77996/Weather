package weather.wu.com.bean;


import org.litepal.crud.DataSupport;

/**
 * Created by 吴海辉 on 2016/12/1.
 *
 *      "cityInfo": {
         "c6": "guangdong",
         "c5": "广州",
         "c4": "guangzhou",
         "c3": "广州",
         "c9": "中国",
         "c8": "china",
         "c7": "广东",
         "c17": "+8",
         "c16": "AZ9200",
         "c1": "101280101",
         "c2": "guangzhou",
         "longitude": 113.265,
         "c11": "020",
         "c10": "1",
         "latitude": 23.108,
         "c12": "510000",
         "c15": "43"
 },
 */
public class CityInfoBean   extends DataSupport {
    public String mCityName_C5;//城市名
    public String mPostCode_C12;//邮编
    public String mLongitude;
    public String mLatitude;
    public String mAreaCode_C11;
    public String mRadarCode_C16;
    public String mAltitude_C15;

    public String getmCityName_C5() {
        return mCityName_C5;
    }

    public void setmCityName_C5(String mCityName_C5) {
        this.mCityName_C5 = mCityName_C5;
    }

    public String getmPostCode_C12() {
        return mPostCode_C12;
    }

    public void setmPostCode_C12(String mPostCode_C12) {
        this.mPostCode_C12 = mPostCode_C12;
    }

    public String getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(String mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getmAreaCode_C11() {
        return mAreaCode_C11;
    }

    public void setmAreaCode_C11(String mAreaCode_C11) {
        this.mAreaCode_C11 = mAreaCode_C11;
    }

    public String getmRadarCode_C16() {
        return mRadarCode_C16;
    }

    public void setmRadarCode_C16(String mRadarCode_C16) {
        this.mRadarCode_C16 = mRadarCode_C16;
    }

    public String getmAltitude_C15() {
        return mAltitude_C15;
    }

    public void setmAltitude_C15(String mAltitude_C15) {
        this.mAltitude_C15 = mAltitude_C15;
    }

    @Override
    public String toString() {
        return "CityInfoBean{" +
                "mCityName_C5='" + mCityName_C5 + '\'' +
                ", mPostCode_C12='" + mPostCode_C12 + '\'' +
                ", mLongitude='" + mLongitude + '\'' +
                ", mLatitude='" + mLatitude + '\'' +
                ", mAreaCode_C11='" + mAreaCode_C11 + '\'' +
                ", mRadarCode_C16='" + mRadarCode_C16 + '\'' +
                ", mAltitude_C15='" + mAltitude_C15 + '\'' +
                '}';
    }
}
