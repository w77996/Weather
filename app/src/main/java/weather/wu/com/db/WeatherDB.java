package weather.wu.com.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/1/12.
 */
public class WeatherDB extends DataSupport {
    public int id;
    public String mCityName;
    public String mJsonData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmJsonData() {
        return mJsonData;
    }

    public void setmJsonData(String mJsonData) {
        this.mJsonData = mJsonData;
    }

    @Override
    public String toString() {
        return "WeatherDB{" +
                "id=" + id +
                ", mCityName='" + mCityName + '\'' +
                ", mJsonData='" + mJsonData + '\'' +
                '}';
    }
}
