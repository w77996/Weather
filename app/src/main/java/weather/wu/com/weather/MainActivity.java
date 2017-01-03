package weather.wu.com.weather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.zaaach.citypicker.CityPickerActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.adapter.HourDataListAdapter;
import weather.wu.com.bean.FutureWeatherBean;
import weather.wu.com.bean.WeatherBean;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.utils.Utility;

/**
 *
 */
public class MainActivity extends Activity {

    /**主控件初始化**/
    //标题栏按钮
    @BindView(R.id.nav_button)
    Button mNavButton;
    @BindView(R.id.title_city)
    TextView mTextViewTileCity;
    //下拉刷新控件
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    //侧滑控件
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    //当前天气的RelativeLayout
    @BindView(R.id.main_now_weather)
    public RelativeLayout mNowWeatherRelativeLayout;
    //整体布局的ScrollView
    @BindView(R.id.weather_scrollview_layout)
    public ScrollView mScrollView;
    //半小时更新的RecyclerView
    @BindView(R.id.hourdata_recyclerview)
    public RecyclerView mRecyclerView;
    //未来天气的Linearlayout
    @BindView(R.id.forecast_layout)
    public LinearLayout mForecastLayout;

    /**NowWeather 控件初始化**/
    //最后一次更新时间
    @BindView(R.id.last_upate_text)
    TextView mLastUpateText;
    //空气质量
    @BindView(R.id.now_weather_air_quality)
    TextView mNowWeatherAirQuality;
    //空气指数
    @BindView(R.id.now_weather_air_index)
    TextView mNowWeatherAirIndex;
    //当天最高气温
    @BindView(R.id.now_weather_hight_tempture)
    TextView mNowWeatherHightTempture;
    //当天最低气温
    @BindView(R.id.now_weather_low_tempture)
    TextView mNowWeatherLowTempture;
    //当前气温
    @BindView(R.id.now_weather_tempeture)
    TextView mNowWeatherTempeture;
    //当天天气状态
    @BindView(R.id.now_weather_condition_tv)
    TextView mNowWeatherCondition;
    //当前天气图标
    @BindView(R.id.now_weather_img)
    ImageView mNowWeather;

    /**右侧菜单控件初始化**/

    /**左侧菜单控件初始化**/
    @BindView(R.id.left_edit_city)
    LinearLayout mLinearLayoutEditCity;
    @BindView(R.id.left_list_city_select)
    ListView mListViewCity;
    /**空气质量控件初始化**/
    @BindView(R.id.air_weather_condition)
    TextView mAirWeatherCondition;





    private int mNowWeatherHeight = -1;
    private int DisplayHeight;
    private int DisplayWideth;
    private Context mContext = MainActivity.this;
    private HourDataListAdapter mHourDataListAdapter;
    private List<String> datas;

    private static final int REQUEST_CODE_PICK_CITY = 0;
    //启动
    //  String json;
    String a = "http://route.showapi.com/9-2?showapi_appid=28198&area=广州&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
    private List<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // initData();
        initView();

    }

    private void initData() {
        datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("ccc" + i);
        }
    }

    private void initView() {
        //  = SystemUtils.getDisplayHeight(getActivity());
        // Logger.d("hello");
        //NowWeather主RelativeLayout
        //   mNowWeatherRelativeLayout = (RelativeLayout)findViewById(R.id.main_now_weather);
        // mScrollView = (ScrollView)findViewById(R.id.weather_scrollview_layout);
        mScrollView.smoothScrollTo(0, 0);
        //   mRecyclerView = (RecyclerView)findViewById(R.id.hourdata_recyclerview);
        //  mForecastLayout = (LinearLayout)findViewById(R.id.forecast_layout);
        mSwipeRefresh.setColorSchemeResources(R.color.color_main);

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
        mNowWeatherHeight = SystemUtils.getDisplayHeight(mContext) - SystemUtils.getActionBarSize(mContext) - SystemUtils.getStatusBarHeight(mContext);
        DisplayHeight = SystemUtils.getDisplayHeight(mContext);
        DisplayWideth = SystemUtils.getDisplayWidth(mContext);
        //设置当前天气信息RelativeLayout的高度
        mNowWeatherRelativeLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mNowWeatherHeight));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //HttpUtil.requestWeather("广州");
                requestWeather("深圳");
            }
        });

    }

    /**
     * 根据城市名请求城市天气信息。
     */
    public void requestWeather(String cityName) {
        String weatherUrl = "http://route.showapi.com/9-2?showapi_appid=28198&area=" + cityName + "&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9"
                + "&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                final WeatherBean weather = Utility.handleWeatherResponse(responseText);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!mSwipeRefresh.isRefreshing()){
                            mSwipeRefresh.setRefreshing(true);
                        }
                        if (weather != null && "0".equals(weather.mShowapi_Res_Code)) {
                        /*   *//* SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();*//**/

                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(MainActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        //  loadBingPic();
    }

    private void showWeatherInfo(WeatherBean weather) {
        mTextViewTileCity.setText(weather.getmCityName());
        mLastUpateText.setText(weather.getmNowWeatherBean().getmTemperature_Time());
        mNowWeatherAirQuality.setText("空气" + weather.getmAqiDetailBean().getmQuality());
        mNowWeatherAirIndex.setText("指数" + weather.getmAqiDetailBean().getmAqi());
        mNowWeatherHightTempture.setText(weather.getmTodayWeatherBean().getmDay_Air_Temperature()+"°");
        mNowWeatherLowTempture.setText(weather.getmTodayWeatherBean().getmNight_Air_Temperature()+"°");
        mNowWeatherTempeture.setText(weather.getmNowWeatherBean().getmTemperature());
        mNowWeatherCondition.setText(weather.getmNowWeatherBean().getmWeather());
        Glide.with(this).load(weather.getmNowWeatherBean().getmWeather_Pic()).into(mNowWeather);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mRecyclerView.setAdapter(new HourDataListAdapter(mContext, weather.getmHourDataList()));
        mRecyclerView.scrollToPosition(weather.getmHourDataList().size() - 1);
        mForecastLayout.removeAllViews();
        for (FutureWeatherBean futureWeatherBean : weather.getmFutureWeatherBeen()) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, mForecastLayout, false);
            view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayHeight / 12));
            TextView dataText = (TextView)view.findViewById(R.id.forecast_week_tv);
            ImageView weatherImg = (ImageView) view.findViewById(R.id.forecast_icon);
            TextView lowTempText = (TextView)view.findViewById(R.id.forecast_low_temp_tv);
            TextView hightTempText = (TextView)view.findViewById(R.id.forecast_high_temp_tv);
            dataText.setText(Utility.weakDayInfliter(futureWeatherBean.getmWeekDay()));
            Glide.with(this).load(futureWeatherBean.getmDay_Weather_Pic()).into(weatherImg);
            lowTempText.setText(futureWeatherBean.getmDay_Air_Temperature()+"°");
            hightTempText.setText(futureWeatherBean.getmNight_Air_Temperature()+"°");
            mForecastLayout.addView(view);
        }
        mForecastLayout.setLayoutParams((new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayHeight / 2)));
    }

    @OnClick(R.id.nav_button)
    public void onOpenDrawerLayout() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
    @OnClick(R.id.title_city)
    public void onSelectCity() {

        startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }

    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                // resultTV.setText("当前选择：" + city);
                Logger.d(city);
                requestWeather(city);

            }
        }
    }
}
