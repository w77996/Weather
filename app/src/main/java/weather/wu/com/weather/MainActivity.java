package weather.wu.com.weather;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.adapter.HourDataListAdapter;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SystemUtils;


public class MainActivity extends AppCompatActivity {
    private int mNowWeatherHeight = -1;
    private RelativeLayout mNowWeatherRelativeLayout;
    private ScrollView mScrollView;
    private RecyclerView mRecyclerView;
    private Context mContext = MainActivity.this;
  //  String json;
    String a = "http://route.showapi.com/9-2?showapi_appid=28198&area=广州&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
    private List<String> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
       /*  Logger.d(HttpUtil.address);
       // HttpUtil.getWeatherJsonData("北京");
       HttpUtil.sendOkHttpRequest(a, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String responseText = response.body().string();
                Logger.d(responseText);

            }
        });*/
      /*  HttpUtil.sendOkHttpRequest(a, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.json(response.toString());

              //  Log.d("json",response.toString());
                System.out.print(response.toString());
            }
        });*/
    }

    private void initView() {
       //  = SystemUtils.getDisplayHeight(getActivity());
       // Logger.d("hello");
        //NowWeather主RelativeLayout
        mNowWeatherRelativeLayout = (RelativeLayout)findViewById(R.id.main_now_weather);
        mScrollView = (ScrollView)findViewById(R.id.weather_scrollview_layout);
        mScrollView.smoothScrollTo(0,0);
        mRecyclerView = (RecyclerView)findViewById(R.id.hourdata_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        mRecyclerView.setAdapter(new HourDataListAdapter());
        //NowWeather主RelativeLayout中的RecycleView
       // mRecyclerView = (RecyclerView)findViewById(R.id.now_weather_recyclerview);
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
