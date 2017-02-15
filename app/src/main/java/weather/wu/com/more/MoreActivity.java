package weather.wu.com.more;

import android.app.Activity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.show.api.ShowApiRequest;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MoreActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Logger.d("ddd");
        getHistoryJsonData();
    }
    private void getHistoryJsonData(){
        long now = System.currentTimeMillis();
        String currentTime = SystemUtils.getCurrentTime();
        String currentDate =SystemUtils.getCurrentDate();
        String url = "http://route.showapi.com/119-42?data="+currentDate+"&showapi_appid=28198&showapi_timestam"+currentTime+"&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9";
      Logger.d(url);
        Logger.d(now+" "+currentTime+" "+currentDate);
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();

                Logger.d(json);
            }
        });

    }


}
