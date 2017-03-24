package weather.wu.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import net.youmi.android.AdManager;
import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

import java.lang.ref.WeakReference;

import weather.wu.com.weather.WeatherActivity;
import weather.wu.com.weather.R;

/**
 * Created by 吴海辉 on 2017/1/17.
 */
public class SplashActivity extends Activity {

    private SwitchHandler mHandler = new SwitchHandler(this);
  //  SplashViewSettings splashViewSettings = new SplashViewSettings();
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //activity切换的淡入淡出效果
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        TextView text = (TextView)findViewById(R.id.splash_tv);
       /* text.setGravity(Gravity.CENTER);*/
        text.setText("多知天气");
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(2000);
        animationSet.setFillAfter(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
        scaleAnimation.setDuration(2000);
        animationSet.addAnimation(scaleAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-200);
        translateAnimation.setDuration(2000);
        animationSet.addAnimation(translateAnimation);

        text.setAnimation(animationSet);
       /* LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(text);*/
      /*  linearLayout =(LinearLayout)findViewById(R.id.spalsh_lin);
        splashViewSettings.setAutoJumpToTargetWhenShowFailed(false);

        splashViewSettings.setTargetClass(WeatherActivity.class);
        // 使用默认布局参数
        splashViewSettings.setSplashViewContainer(linearLayout);*/
// 使用自定义布局参数
      /*  splashViewSettings.setSplashViewContainer(ViewGroup splashViewContainer,
                ViewGroup.LayoutParams splashViewLayoutParams);*/
        mHandler.sendEmptyMessageDelayed(1, 2000);
      /*  SpotManager.getInstance(SplashActivity.this).showSplash(SplashActivity.this,
                splashViewSettings, new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        Logger.d("成功");
                    }

                    @Override
                    public void onShowFailed(int i) {
                        Logger.e(i+"");
                    }

                    @Override
                    public void onSpotClosed() {
                        Logger.d("关闭");
                    }

                    @Override
                    public void onSpotClicked(boolean b) {

                    }
                });
*/
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 开屏展示界面的 onDestroy() 回调方法中调用
       // SpotManager.getInstance(SplashActivity.this).onDestroy();
    }

}
