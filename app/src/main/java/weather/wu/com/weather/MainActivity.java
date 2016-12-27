package weather.wu.com.weather;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import weather.wu.com.utils.SystemUtils;


public class MainActivity extends AppCompatActivity {
    private int mNowWeatherHeight = -1;
    private RelativeLayout mNowWeatherRelativeLayout;
    private RecyclerView mRecyclerView;
    private Context mContext = MainActivity.this;

    private List<String> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
       //  = SystemUtils.getDisplayHeight(getActivity());
       // Logger.d("hello");
        //NowWeather主RelativeLayout
        mNowWeatherRelativeLayout = (RelativeLayout)findViewById(R.id.main_now_weather);
        //NowWeather主RelativeLayout中的RecycleView
        mRecyclerView = (RecyclerView)findViewById(R.id.now_weather_recyclerview);
        //获取屏幕高度
       /* int displayHeight = SystemUtils.getDisplayHeight(MainActivity.this);
        Log.e("Log displayHeight",displayHeight+"");

        TypedValue typedValue = new TypedValue();
         MainActivity.this.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
         int[] attribute = new int[] { android.R.attr.textSize };
       TypedArray array =   MainActivity.this.obtainStyledAttributes(typedValue.resourceId, attribute);
        Log.e("Log array",array+"");
        int textSize = array.getDimensionPixelSize(0 *//* index *//*, -1 *//* default size *//*);
        array.recycle();
        Log.e("Log typedValue",textSize+"");
        int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, MainActivity.this.getResources().getDisplayMetrics());
        Log.e("Log actionBarHeight",actionBarHeight+"");
       Log.e("Log System.getActionBarHeight",);*/
        // mNowWeatherHeight高度=屏幕高度-标题栏高度-状态栏高度
        mNowWeatherHeight = SystemUtils.getDisplayHeight(mContext)- SystemUtils.getActionBarSize(mContext)- SystemUtils.getStatusBarHeight(mContext);

        //设置当前天气信息RelativeLayout的高度
        mNowWeatherRelativeLayout.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, mNowWeatherHeight));
    }
}
