package weather.wu.com.bean;


import org.litepal.crud.DataSupport;

/**
 * Created by 吴海辉 on 2016/12/1.
 *  "aqiDetail":{
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
 */
public class AqiDetailBean{
        public String mAqi;
        public String mCo;
        public String mSo2;
        public String mO3;
        public String mNo2;
        public String mArea;
        public String mQuality;
        public String mPm10;
        public String mPm2_5;
        public String mPrimary_Pollutant;

        public String getmAqi() {
                return mAqi;
        }

        public void setmAqi(String mAqi) {
                this.mAqi = mAqi;
        }

        public String getmCo() {
                return mCo;
        }

        public void setmCo(String mCo) {
                this.mCo = mCo;
        }

        public String getmSo2() {
                return mSo2;
        }

        public void setmSo2(String mSo2) {
                this.mSo2 = mSo2;
        }

        public String getmO3() {
                return mO3;
        }

        public void setmO3(String mO3) {
                this.mO3 = mO3;
        }

        public String getmNo2() {
                return mNo2;
        }

        public void setmNo2(String mNo2) {
                this.mNo2 = mNo2;
        }

        public String getmArea() {
                return mArea;
        }

        public void setmArea(String mArea) {
                this.mArea = mArea;
        }

        public String getmQuality() {
                return mQuality;
        }

        public void setmQuality(String mQuality) {
                this.mQuality = mQuality;
        }

        public String getmPm10() {
                return mPm10;
        }

        public void setmPm10(String mPm10) {
                this.mPm10 = mPm10;
        }

        public String getmPm2_5() {
                return mPm2_5;
        }

        public void setmPm2_5(String mPm2_5) {
                this.mPm2_5 = mPm2_5;
        }

        public String getmPrimary_Pollutant() {
                return mPrimary_Pollutant;
        }

        public void setmPrimary_Pollutant(String mPrimary_Pollutant) {
                this.mPrimary_Pollutant = mPrimary_Pollutant;
        }

        @Override
        public String toString() {
                return "AqiDetailBean{" +
                        "mAqi='" + mAqi + '\'' +
                        ", mCo='" + mCo + '\'' +
                        ", mSo2='" + mSo2 + '\'' +
                        ", mO3='" + mO3 + '\'' +
                        ", mNo2='" + mNo2 + '\'' +
                        ", mArea='" + mArea + '\'' +
                        ", mQuality='" + mQuality + '\'' +
                        ", mPm10='" + mPm10 + '\'' +
                        ", mPm2_5='" + mPm2_5 + '\'' +
                        ", mPrimary_Pollutant='" + mPrimary_Pollutant + '\'' +
                        '}';
        }
}
