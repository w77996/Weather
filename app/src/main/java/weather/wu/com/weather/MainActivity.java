package weather.wu.com.weather;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.melnykov.fab.FloatingActionButton;
import com.orhanobut.logger.Logger;
import com.zaaach.citypicker.CityPickerActivity;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weather.wu.com.activity.AboutActivity;
import weather.wu.com.activity.CityEditActivity;
import weather.wu.com.adapter.CityLeftMenuListAdapter;
import weather.wu.com.adapter.HourDataListAdapter;
import weather.wu.com.bean.FutureWeatherBean;
import weather.wu.com.bean.HourDataBean;
import weather.wu.com.bean.WeatherBean;
import weather.wu.com.db.WeatherDB;
import weather.wu.com.utils.DoubleClickExit;
import weather.wu.com.utils.HttpUtil;
import weather.wu.com.utils.SharedPreferencesUtils;
import weather.wu.com.utils.SpUtils;
import weather.wu.com.utils.SystemUtils;
import weather.wu.com.utils.Utility;

/**
 *
 */
public class MainActivity extends SlidingActivity {

    /**主控件初始化**/
    //标题栏按钮
            @BindView(R.id.main_title)
            LinearLayout mTitleLayout ;
    @BindView(R.id.nav_button)
    Button mNavButton;
    @BindView(R.id.title_city)
    TextView mTextViewTileCity;
   /* @BindView(R.id.weather_scrollview_layout)
    ScrollView scrollView;*/
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
    //背景的图片
  /*  @BindView(R.id.back_pic_img)
    public ImageView mImageViewBack;*/
    @BindView(R.id.main_left_menu)
    public LinearLayout mLinearLayoutLeftMenu;
    /*@BindView(R.id.main_right_menu)
    public LinearLayout mLinearLayoutRightMenu;*/

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
    @BindView(R.id.left_add_city)
    LinearLayout mLinearLayoutAddCity;
    /**右侧菜单控件初始化**/
    //今日天气状态
   /* @BindView(R.id.right_today_weather)
    TextView mRightTodayWeatherText;
    //气压
    @BindView(R.id.right_air_press)
    TextView mRightAirPressText;
    //降水概率
    @BindView(R.id.right_rain)
    TextView mRightRainText;
    //天气图标
    @BindView(R.id.right_today_weather_img)
    ImageView mRightTodayWeatherImg;
    //白天天气
    @BindView(R.id.right_day_weather)
    TextView mRightDayWeatherText;
    //夜晚天气
    @BindView(R.id.right_night_weather)
    TextView mRightNightWeatherText;
    //白天气温
    @BindView(R.id.right_day_temp)
    TextView mRightDayTempText;
    //夜晚气温
    @BindView(R.id.right_night_temp)
    TextView mRightNightTempText;
    //日出时间
    @BindView(R.id.right_sunbegin)
    TextView mRightSunBeginText;
    //日落时间
    @BindView(R.id.right_sunend)
    TextView mRightSunEnd;
    //白天风力
    @BindView(R.id.right_day_wind)
    TextView mRightDayWind;
    //夜晚风力
    @BindView(R.id.right_night_wind)
    TextView mRightNightWind;
    //白天风向
    @BindView(R.id.right_day_wind_diretion)
    TextView mRightDayWindDiretion;
    //夜晚风向
    @BindView(R.id.right_night_wind_diretion)
    TextView mRightNightWindDiretion;*/


    /**空气质量控件初始化**/
    @BindView(R.id.air_weather_condition)
    TextView mAirWeatherCondition;
    @BindView(R.id.air_pm2_5_index)
    TextView mAirPm25Index;
    @BindView(R.id.air_co_index)
    TextView mAirCoIndex;
    @BindView(R.id.air_pm10_index)
    TextView mAirPm10Index;
    @BindView(R.id.air_so2_index)
    TextView mAirSo2Index;
    @BindView(R.id.air_o3_index)
    TextView mAirO3Index;
    @BindView(R.id.air_no2_index)
    TextView mAirNo2Index;
    @BindView(R.id.aqi_primary_pollutant)
    TextView mAqiPrimaryPollutiant;

    /**生活指数控件初始化**/
    @BindView(R.id.index_cloth_brief)
    TextView mIndexClothBrief;
    @BindView(R.id.index_cloth_txt)
    TextView mIndexClothTxt;
    @BindView(R.id.index_flu_brief)
    TextView mIndexFluBrief;
    @BindView(R.id.index_flu_txt)
    TextView mIndexFluTxt;
    @BindView(R.id.index_sport_brief)
    TextView mIndexSportBrief;
    @BindView(R.id.index_sport_txt)
    TextView mIndexSportTxt;
    @BindView(R.id.index_travel_brief)
    TextView mIndexTravelBrief;
    @BindView(R.id.index_travel_txt)
    TextView mIndexTravelTxt;

    @BindView(R.id.left_more)
    TextView mMoreTxt;

    //今日天气状态
    TextView mRightTodayWeatherText;
    //气压
    TextView mRightAirPressText;
    //降水概率
    TextView mRightRainText;
    TextView mRightUv;
    //天气图标
    ImageView mRightTodayWeatherImg;
    //白天天气
    TextView mRightDayWeatherText;
    //夜晚天气
    TextView mRightNightWeatherText;
    //白天气温
    TextView mRightDayTempText;
    //夜晚气温
    TextView mRightNightTempText;
    //日出时间
    TextView mRightSunBeginText;
    //日落时间
    TextView mRightSunEnd;
    //白天风力
    TextView mRightDayWind;
    //夜晚风力
    TextView mRightNightWind;
    //白天风向
    TextView mRightDayWindDiretion;
    //夜晚风向
    TextView mRightNightWindDiretion;

    TextView mRightCityNameText;
    TextView mRightAreaCodeText;
    TextView mRightAreaNumText;
    TextView mRightAltitudeText;
    TextView mRightLatitudeText;
    TextView mRightLongtitudeText;

    private int mNowWeatherHeight = -1;
    private int DisplayHeight;
    private int DisplayWideth;
    private Context mContext = MainActivity.this;
    private HourDataListAdapter mHourDataListAdapter;
    private List<String> datas;
    public static CityLeftMenuListAdapter mCityListAdapter;
    private static final int REQUEST_CODE_PICK_CITY = 0;
    private static final int REQUEST_CODE_EDIT_CITY =1;
    public SQLiteDatabase db;
    public DBThread mDBThread;
    private List<FutureWeatherBean> fff = new ArrayList<FutureWeatherBean>();
    private List<HourDataBean> hhh = new ArrayList<HourDataBean>();
    public static String mCurrentCity;
    //启动
    //  String json;
   // String a = "http://route.showapi.com/9-2?showapi_appid=28198&area=广州&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
    private List<String> listData = new ArrayList<>();
    private static List<String> mLeftCityListMenu  = new ArrayList<String>();
    SharedPreferencesUtils sharedPreferencesUtils;
    private DBThread mThread;
    private Handler mHandler =new Handler(){
        @Override
        //当有消息发送出来的时候就执行Handler的这个方法
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            int type = msg.what;
            switch (type){
                case 1:
                    mCityListAdapter.notifyDataSetChanged();
                    requestWeather(mCurrentCity);
                  /*  List<WeatherDB> weatherDBs= (List<WeatherDB>) msg.obj;
                    for(WeatherDB weatherDB:weatherDBs){
                        mListCity.add(weatherDB.getmCityName());
                    }*/
            }
            //只要执行到这里就关闭对话框
            //pd.dismiss();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);*/
      // initWindow();

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();
         db = Connector.getDatabase();
      //  initData();
    }

    private void initData() {
         mLeftCityListMenu.add("深圳");
    }
   @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_LAYOUT_FLAGS|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            getWindow().getDecorView().setFitsSystemWindows(true);
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //如果是第一次加载应用则直接打开城市选择，否者开启线程读取数据库，更新左侧菜单城市列表
        if(SpUtils.getBoolean(getApplicationContext(),SpUtils.FIRST_START,true)){
            startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                    REQUEST_CODE_PICK_CITY);
            //sharedPreferencesUtils.put("first_start",true);
        }else{
            mDBThread = new DBThread();
            mDBThread.start();
            mCityListAdapter = new CityLeftMenuListAdapter(MainActivity.this, mLeftCityListMenu);
            mListViewCity.setAdapter(mCityListAdapter);
             mCityListAdapter.notifyDataSetChanged();
           // requestWeather(mCurrentCity);
            //mCurrentCity = mListCity.get(mListCity.size()).toString();
        }
        //  = SystemUtils.getDisplayHeight(getActivity());
        // Logger.d("hello");
        //NowWeather主RelativeLayout
        //   mNowWeatherRelativeLayout = (RelativeLayout)findViewById(R.id.main_now_weather);
        // mScrollView = (ScrollView)findViewById(R.id.weather_scrollview_layout);

        //mTitleLayout.bringToFront();
        //SlidingMenu右侧滑出，遮盖110dp
        SlidingMenu mRightMenu = getSlidingMenu();
        setBehindContentView(R.layout.main_right_menu);
        mRightMenu.setMode(SlidingMenu.RIGHT);
        mRightMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        mRightMenu.setFadeDegree(0.35f);
        //右侧控件初始化
        mRightTodayWeatherImg = (ImageView)findViewById(R.id.right_today_weather_img);
        mRightTodayWeatherText = (TextView)findViewById(R.id.right_today_weather);
        mRightAirPressText =(TextView)findViewById(R.id.right_air_press) ;
        mRightRainText = (TextView)findViewById(R.id.right_rain) ;
        mRightDayWeatherText = (TextView)findViewById(R.id.right_day_weather) ;
        mRightNightWeatherText = (TextView)findViewById(R.id.right_night_weather) ;
        mRightSunBeginText = (TextView)findViewById(R.id.right_sunbegin) ;
        mRightSunEnd = (TextView)findViewById(R.id.right_sunend) ;
        mRightNightWind = (TextView)findViewById(R.id.right_night_wind) ;
        mRightDayWind = (TextView)findViewById(R.id.right_day_wind) ;
        mRightDayWindDiretion = (TextView)findViewById(R.id.right_day_wind_diretion) ;
        mRightNightWindDiretion = (TextView)findViewById(R.id.right_night_wind_diretion) ;
        mRightDayTempText = (TextView)findViewById(R.id.right_day_temp) ;
        mRightNightTempText = (TextView)findViewById(R.id.right_night_temp) ;
        mRightNightWindDiretion = (TextView)findViewById(R.id.right_night_wind_diretion) ;
        mRightUv = (TextView)findViewById(R.id.right_uv);

        mRightCityNameText = (TextView)findViewById(R.id.right_menu_cityname);
        mRightAreaCodeText = (TextView)findViewById(R.id.right_menu_areacode);
        mRightAltitudeText = (TextView)findViewById(R.id.right_menu_altitude);
        mRightLongtitudeText =(TextView)findViewById(R.id.right_menu_longtitude);
        mRightAreaNumText =(TextView)findViewById(R.id.right_menu_postcode);
        mRightLatitudeText =(TextView)findViewById(R.id.right_menu_latitude);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToScrollView(mScrollView);
        fab.setColorPressed(0xffb71c1c);*/
        mScrollView.smoothScrollTo(0, 0);
        //   mRecyclerView = (RecyclerView)findViewById(R.id.hourdata_recyclerview);
        //  mForecastLayout = (LinearLayout)findViewById(R.id.forecast_layout);

       // mSwipeRefresh.setColorSchemeResources(R.color.color_main);
        //下拉刷新控件
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(mCurrentCity);

            }
        });
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //控件拉动是放大放小，起始位置，结束位置
        mSwipeRefresh.setProgressViewOffset(true,100,200);
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
        Logger.d(DisplayHeight+"  "+DisplayWideth);
        //设置当前天气信息RelativeLayout的高度
        mNowWeatherRelativeLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, mNowWeatherHeight));
       // mImageViewBack.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,mNowWeatherHeight/2));
      //  mLinearLayoutLeftMenu.setLayoutParams(new DrawerLayout.LayoutParams(DisplayWideth/2, DrawerLayout.LayoutParams.MATCH_PARENT));

        mListViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mCurrentCity = mLeftCityListMenu.get(position);
                requestWeather(mCurrentCity);
            }
        });
    }
    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
    /**
     * 根据城市名请求城市天气信息。
     */
    public  void requestWeather(final String cityName) {
        String weatherUrl = "http://route.showapi.com/9-2?showapi_appid=28198&area=" + cityName + "&showapi_sign=bd9ad7a172ee4a5a8c57618a248c63e9"
                + "&needMoreDay=1&needIndex=1&needHourData=1&need3HourForcast=1&needAlarm=1";
        mScrollView.smoothScrollTo(0, 0);
       if(!mSwipeRefresh.isRefreshing()){
            mSwipeRefresh.setRefreshing(true);

           // mSwipeRefresh.setProgressViewOffset(false,100,300);
        }
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                final WeatherBean weather = Utility.handleWeatherResponse(responseText);
                 WeatherDB weatherDB  =new WeatherDB();
                WeatherDB weatherData= DataSupport.where("mCityName = ?", cityName).findFirst(WeatherDB.class);
           // if(Utility.isNetworkConnected(MainActivity.this)){
                //从数据库获取城市，如果数据库不为空并且网络获取的数据返回成功，更新数据库，如果数据库为空，则添加新城市
                if(weatherData!=null&&"0".equals(weather.mShowapi_Res_Code)){
                    weatherDB.setmJsonData(responseText);
                    weatherDB.update(weatherData.id);
                }else if(weatherData==null&&"0".equals(weather.mShowapi_Res_Code)){
                    weatherDB.mCityName = cityName;
                    weatherDB.mJsonData = responseText;
                    weatherDB.save();
                }
          //  }
                //主线程更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
               // WeatherDB weatherDB  =new WeatherDB();
                WeatherDB weatherData= DataSupport.where("mCityName = ?", cityName).findFirst(WeatherDB.class);
                if(weatherData!=null){
                    final WeatherBean weather = Utility.handleWeatherResponse(weatherData.getmJsonData());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeatherInfo(weather);
                            Toast.makeText(MainActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                            mSwipeRefresh.setRefreshing(false);
                        }
                    });
                }
            }
        });
        //  loadBingPic();
    }
    /**
     * 将数据显示到UI上
     * @param weather
     */
    private void showWeatherInfo(WeatherBean weather) {
        mTextViewTileCity.setText(weather.getmCityName());
        mLastUpateText.setText(weather.getmNowWeatherBean().getmTemperature_Time()+"更新");
        mNowWeatherAirQuality.setText("空气" + weather.getmAqiDetailBean().getmQuality());
        mNowWeatherAirIndex.setText("指数" + weather.getmAqiDetailBean().getmAqi());
        mNowWeatherHightTempture.setText(weather.getmTodayWeatherBean().getmDay_Air_Temperature()+"°");
        mNowWeatherLowTempture.setText(weather.getmTodayWeatherBean().getmNight_Air_Temperature()+"°");
        mNowWeatherTempeture.setText(weather.getmNowWeatherBean().getmTemperature());
        mNowWeatherCondition.setText(weather.getmNowWeatherBean().getmWeather());
        Glide.with(this).load(weather.getmNowWeatherBean().getmWeather_Pic()).diskCacheStrategy(DiskCacheStrategy.ALL).into(mNowWeather);

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
        //空气质量UI更新
        mAirWeatherCondition.setText(weather.getmAqiDetailBean().mQuality);
        mAirPm25Index.setText(weather.getmAqiDetailBean().getmPm2_5());
        mAirCoIndex.setText(weather.getmAqiDetailBean().getmCo());
        mAirNo2Index.setText(weather.getmAqiDetailBean().getmNo2());
        mAirO3Index.setText(weather.getmAqiDetailBean().getmO3());
        mAirPm10Index.setText(weather.getmAqiDetailBean().getmPm10());
      //  if(weather.getmAqiDetailBean().getmPrimary_Pollutant()!=null&&!"".equals(weather.getmAqiDetailBean().getmPrimary_Pollutant())){
            mAqiPrimaryPollutiant.setText("主要污染物：    "+weather.getmAqiDetailBean().getmPrimary_Pollutant());
       /* }else{
            mAqiPrimaryPollutiant.setVisibility(View.GONE);
        }*/

        mAirSo2Index.setText(weather.getmAqiDetailBean().getmSo2());
        //生活指数UI更新
        mIndexClothBrief.setText("穿衣指数："+weather.getIndexBean().getmClothesTitle());
        mIndexClothTxt.setText(weather.getIndexBean().getmClothesDesc());
        mIndexFluBrief.setText("感冒指数："+weather.getIndexBean().getmColdTitle());
        mIndexFluTxt.setText(weather.getIndexBean().getmColdDesc());
        mIndexSportBrief.setText("运动指数："+weather.getIndexBean().getmSportsTitle());
        mIndexSportTxt.setText(weather.getIndexBean().getmSportsDesc());
        mIndexTravelBrief.setText("旅游指数："+weather.getIndexBean().getmTravelTitle());
        mIndexTravelTxt.setText(weather.getIndexBean().getmTravelDesc());

        mRightTodayWeatherText.setText(weather.getmTodayWeatherBean().getmDay_Weather());
        mRightAirPressText.setText("气压"+weather.getmTodayWeatherBean().getmAir_Press());
        mRightRainText.setText("降水概率"+weather.getmTodayWeatherBean().getmJiangShui());
        Glide.with(this).load(weather.getmTodayWeatherBean().getmDay_Weather_Pic()).into(mRightTodayWeatherImg);
        mRightDayWeatherText.setText(weather.getmTodayWeatherBean().getmDay_Weather());
        mRightNightWeatherText.setText(weather.getmTodayWeatherBean().getmNight_Weather());
        mRightDayWind.setText(weather.getmTodayWeatherBean().getmDay_Wind_Power());
        mRightNightWind.setText(weather.getmTodayWeatherBean().getmNight_Wind_Power());
        mRightDayTempText.setText(weather.getmTodayWeatherBean().getmDay_Air_Temperature()+"°");
        mRightNightTempText.setText(weather.getmTodayWeatherBean().getmNight_Air_Temperature()+"°");
        mRightDayWindDiretion.setText(weather.getmTodayWeatherBean().getmDay_Wind_Direction());
        mRightNightWindDiretion.setText(weather.getmTodayWeatherBean().getmNight_Wind_Direction());
        mRightSunBeginText.setText(weather.getmTodayWeatherBean().getmSun_Begin());
        mRightSunEnd.setText(weather.getmTodayWeatherBean().getmSun_End());
        mRightUv.setText("紫外线"+weather.getmTodayWeatherBean().getmZiWaiXian());

        mRightCityNameText.setText("城市名："+weather.getmCityInfoBean().getmCityName_C5());
        mRightAreaNumText.setText("邮编："+weather.getmCityInfoBean().getmPostCode_C12());
        mRightLatitudeText.setText("经度："+weather.getmCityInfoBean().getmLatitude());
        mRightLongtitudeText.setText("纬度："+weather.getmCityInfoBean().getmLongitude());
        mRightAreaCodeText.setText("区号："+weather.getmCityInfoBean().getmAreaCode_C11());
        mRightAltitudeText.setText("海拔："+weather.getmCityInfoBean().getmAltitude_C15()+"米");
    }

    @OnClick(R.id.nav_button)
    public void onOpenDrawerLayout() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
    @OnClick(R.id.left_add_city)
    public void onSelectCity() {
        startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }
    @OnClick(R.id.left_edit_city)
    public void onEditCity(){
        mDrawerLayout.closeDrawer(GravityCompat.START);
        Intent intent = new Intent(MainActivity.this, CityEditActivity.class);
        /*intent.putStringArrayListExtra("city", (ArrayList<String>) mListCity);*/

        startActivityForResult(intent, REQUEST_CODE_EDIT_CITY);
    }
    @OnClick(R.id.left_more)
    public void onMore()
    {
        startActivity(new Intent(this, AboutActivity.class));
    }
    /**
     * 开启线程对数据库进行操作
     */
    private  class DBThread extends Thread {
        @Override
        public void run() {
            List<WeatherDB> weatherDB = DataSupport.findAll(WeatherDB.class);
            if(weatherDB!=null) {
                mLeftCityListMenu.clear();
                for (WeatherDB wb : weatherDB) {
                     mLeftCityListMenu.add(wb.getmCityName());
                    Logger.d(wb.getmCityName());
                }

                    mCurrentCity =  mLeftCityListMenu.get(0).toString();
                    Message message = Message.obtain();
                    message.obj = mCurrentCity;
                    message.what=1;
                    mHandler.sendMessage(message);


               // requestWeather(mCurrentCity);
               // mCityListAdapter.notifyDataSetChanged();
             /* Message message = Message.obtain();
                message.obj = weatherDB;
                message.what = 1;
                mHandler.sendMessage(message);
            }else {
                Message message = Message.obtain();
                message.what = 0;
                mHandler.sendMessage(message);
            }*/
            }
        }

    }
    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                if(SpUtils.getBoolean(getApplicationContext(),SpUtils.FIRST_START,true)&&Utility.isNetworkConnected(this)){

                }
                if(SpUtils.getBoolean(getApplicationContext(),SpUtils.FIRST_START,true)){
                    SpUtils.putBoolean(getApplicationContext(),SpUtils.FIRST_START,false);
                }
                Logger.d(SpUtils.getBoolean(getApplicationContext(),SpUtils.FIRST_START,true)+"");
                mCityListAdapter = new CityLeftMenuListAdapter(MainActivity.this, mLeftCityListMenu);
                mListViewCity.setAdapter(mCityListAdapter);
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                // resultTV.setText("当前选择：" + city);
                if(! mLeftCityListMenu.contains(city)){
                     mLeftCityListMenu.add(city);
                    mCityListAdapter.notifyDataSetChanged();
                    mCurrentCity  = city;
                    requestWeather(mCurrentCity);
                }
                Logger.d(city);
               // WeatherBean weatherBean = DataSupport.find(WeatherBean.class,);
            }
        }else if(requestCode == REQUEST_CODE_EDIT_CITY){
            Logger.d("CityEditActivity");
             mLeftCityListMenu.clear();
            DBThread dbThread = new DBThread();
            dbThread.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
       /* if(mDBThread.isAlive()){
            mDBThread.stop();
        }*/

    }
    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else if(!DoubleClickExit.check()){
            Toast.makeText(getApplicationContext(),"再按一次退出",Toast.LENGTH_SHORT).show();
        }else{
            finish();
        }
    }

}
