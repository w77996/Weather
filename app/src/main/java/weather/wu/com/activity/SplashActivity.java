package weather.wu.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import weather.wu.com.weather.WeatherActivity;
import weather.wu.com.weather.R;

/**
 * Created by Administrator on 2017/1/17.
 */
public class SplashActivity extends Activity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private SwitchHandler mHandler = new SwitchHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //activity切换的淡入淡出效果
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.sendEmptyMessageDelayed(1, 2000);

    }

    private static class SwitchHandler extends Handler {
        private WeakReference<SplashActivity> mWeakReference;

        SwitchHandler(SplashActivity activity) {
            mWeakReference = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mWeakReference.get();
            if (activity != null) {
                WeatherActivity.launch(activity);
                activity.finish();
            }
        }
    }

}
